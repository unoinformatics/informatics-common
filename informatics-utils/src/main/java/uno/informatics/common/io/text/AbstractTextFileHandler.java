/*******************************************************************************
 * Copyright 2016 Guy Davenport
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package uno.informatics.common.io.text;

import static uno.informatics.common.Constants.UNKNOWN_COUNT;
import static uno.informatics.common.Constants.UNKNOWN_INDEX;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import uno.informatics.common.Constants;
import uno.informatics.common.io.TextFileHandler;

public abstract class AbstractTextFileHandler implements TextFileHandler {
    private String pathReference;

    private Path path;

    private boolean isInStrictMode;

    private String comment;

    private int currentRowSize = UNKNOWN_COUNT;

    // the actual position (including comments, empty lines etc) of the
    // read/write
    private int rowPosition = -1;

    // the index of the current row being written or read, -1 if not on any row
    // yet
    private int rowIndex = UNKNOWN_INDEX;

    // the index of the current column being written or read, -1 if not on any
    // cell yet
    private int columnIndex = UNKNOWN_INDEX;

    private boolean rowSizeSetExternally = false;

    private String delimiter;

    protected AbstractTextFileHandler() {

    }

    /**
     * Constructs an initialised reader using a string reference to a text file.
     * 
     * @param reference
     *            a text file name or URL
     * @throws FileNotFoundException
     *             if the file to read/write is not found
     * @throws IOException
     *             if an I/O error occurs
     */
    public AbstractTextFileHandler(String reference) throws IOException, FileNotFoundException {
        if (reference == null)
            throw new FileNotFoundException("File undefined");

        setFileReference(reference);
    }

    /**
     * Constructs an initialised reader using a file.
     * 
     * @param path
     *            a Path object.
     * @throws FileNotFoundException
     *             if the file to read/write is not found
     * @throws IOException
     *             if an I/O error occurs
     */
    public AbstractTextFileHandler(Path path) throws IOException, FileNotFoundException {
        if (path == null)
            throw new FileNotFoundException("Path undefined");

        setPath(path);
    }

    /**
     * @return <code>true</code>
     */
    public boolean ready() {
        return true;
    }

    public final int getRowCount() {
        if (!ready())
            return Constants.UNKNOWN_COUNT;
        else
            return getRowIndex();
    }

    public final int getColumnCount() {
        if (getCurrentRowSize() > -1)
            return getCurrentRowSize();
        else
            return Constants.UNKNOWN_COUNT;
    }

    /**
     * Gets the string which indicates at comment line that should be ignored by
     * the reader.
     * 
     * @return the comment string
     */
    public final String getCommentString() {
        return comment;
    }

    public final String getPathReference() {
        return pathReference;
    }

    public final void setFileReference(String pathReference) throws IOException {
        if (this.pathReference != pathReference) {
            if (isInUse())
                throw new IOException("Path can not be changed while reader/writer is in use");

            this.pathReference = pathReference;
            path = null;
        }
    }

    public final Path getPath() {
        return path;
    }

    public final void setPath(Path path) throws IOException {
        if (this.path != path) {
            if (isInUse())
                throw new IOException("Path can not be changed while reader/writer is in use");

            pathReference = null;
            this.path = path;
        }
    }

    public final boolean isInStrictMode() {
        return isInStrictMode;
    }

    public final void setInStrictMode(boolean isInStrictMode) throws IOException {
        if (this.isInStrictMode != isInStrictMode) {
            if (isInUse())
                throw new IOException("Mode can not be changed while reader/writer is in use");

            this.isInStrictMode = isInStrictMode;
        }
    }

    /**
     * Sets the string which indicates a comment line that should be ignored by
     * the reader. Set to <code>null</code> if no comments are allowed
     * 
     * @param comment
     *            the comment string
     * @throws IOException
     *             if the reader/writer is in use
     */
    public final synchronized void setCommentString(String comment) throws IOException {
        if (comment == null || comment.equals(""))
            comment = Constants.DEFAULT_COMMENT;

        if (!comment.equals(this.comment)) {
            if (isInUse())
                throw new IOException("Comment string can not be set while reader/writer is in use");

            this.comment = comment;
        }
    }

    /**
     * Gets the string which indicates a new field in a record.
     * 
     * @return the delimiter string
     */
    public final String getDelimiterString() {
        return delimiter;
    }

    /**
     * Sets the string which indicates a new field in a record.
     * 
     * @param delimiter
     *            the delimiter string
     * @throws IOException
     *             if the reader is currently in use
     */
    public synchronized void setDelimiterString(String delimiter) throws IOException {
        if (delimiter == null || delimiter.equals(""))
            delimiter = Constants.DEFAULT_DELIMITER;

        if (!delimiter.equals(this.delimiter)) {
            if (isInUse())
                throw new IOException("Delimiter string can not be set while reader/writer is in use");

            this.delimiter = delimiter;
        }
    }

    /**
     * Initialises the reader.
     * 
     * @throws FileNotFoundException
     *             if the file to read/write is not found
     * @throws IOException
     *             if an I/O error occurs
     */
    protected void initialise() throws FileNotFoundException, IOException {
        isInStrictMode = false;

        rowPosition = Constants.UNKNOWN_INDEX;
        rowIndex = Constants.UNKNOWN_INDEX;
        columnIndex = Constants.UNKNOWN_INDEX;

        comment = Constants.DEFAULT_COMMENT;

        if (delimiter == null || delimiter.equals(""))
            delimiter = Constants.DEFAULT_DELIMITER;
    }

    /**
     * Add addition cells to row to ensure it is the same size as other rows
     * 
     * @param row
     *            row to complete
     * @throws IOException
     *             if the row can not be completed
     */
    protected final void updateRowFromSize(List<Object> row) throws IOException {
        if (row != null) {
            if (row.size() < getCurrentRowSize())
                for (int i = 0; i < row.size() - getCurrentRowSize(); ++i)
                    row.add(null);
        }
    }

    /**
     * Gets the current row size, which is either the row size that was set externally
     * using the {@link #setFixedRowSize(int)} or calculated from the data being read or
     * written depending on the implementing class. 
     * 
     * @return the size of the current 
     */
    public final int getCurrentRowSize() {
        return currentRowSize;
    }

    /**
     * Set a fixed row size. Rows less than this size are padded out to right size
     * or rows longer than this size are truncated.
     * 
     * @param rowSize the fixed row size
     * @throws IOException if the size is being set after read/write has started.
     */
    public final void setFixedRowSize(int rowSize) throws IOException {
        if (this.currentRowSize != rowSize) {
            if (isInUse()) {
                throw new IOException("Row size can not be set while reader/writer is in use");
            } else {
                setCurrentRowSize(rowSize);
            }
        }

        rowSizeSetExternally = rowSize >= 0;
    }

    protected final void setCurrentRowSize(int rowSize) {
        this.currentRowSize = rowSize;
    }

    protected void updateRowSize(int rowSize) {
        if (!rowSizeSetExternally) {
            setCurrentRowSize(rowSize) ;
        }
    }

    protected final int getRowIndex() {
        return rowIndex;
    }

    protected final void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    protected final void incrementRowIndex() {
        ++rowIndex;
    }

    protected final int getRowPosition() {
        return rowPosition;
    }

    protected final void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    protected final void incrementRowPosition() {
        ++rowPosition;
    }

    protected final boolean isInUse() {
        return rowIndex > -1;
    }

    protected final int getColumnIndex() {
        return columnIndex;
    }

    protected final void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    protected final void incrementColumnIndex() {
        ++columnIndex;
    }

    protected final void resetCellIndex() {
        columnIndex = Constants.UNKNOWN_INDEX;
    }

    protected final boolean isRowSizeSetExternally() {
        return rowSizeSetExternally;
    }
}
