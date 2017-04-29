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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

import uno.informatics.common.Constants;
import uno.informatics.common.ConversionUtilities;
import uno.informatics.common.io.RowWriter;

public class TextFileRowWriter extends AbstractTextFileHandler implements RowWriter {
    private BufferedWriter bufferedWriter;

    private static final String BUFFERWRITER_NULL = "Buffer writer is undefined";
    
    /**
     * Sets if the writer finds any single quotes in a token, these tokens are
     * enclosed with the opposite type of quotes. If both are found an error is given
     */
    public static final int ADD_SINGLE_QUOTES = 1;
    
    /**
     * Sets if the writer finds any double quotes in a token, these tokens are
     * enclosed with the opposite type of quotes. If both are found an error is given
     */
    public static final int ADD_DOUBLE_QUOTES = 2;
    
    /**
     * Sets if the writer finds any single quotes in a token, these tokens are
     * prefixed with an escape character
     */
    //TODO
    //public static final int ESCAPE_SINGLE_QUOTES = 4;
    
    /**
     * Sets if the writer finds any double quotes in a token, these tokens are
     * prefixed with an escape character
     */
    //TODO
    //public static final int ESCAPE_DOUBLE_QUOTES = 8;

    private static final int VALID_OPTIONS = ADD_SINGLE_QUOTES | ADD_DOUBLE_QUOTES ;
    
    /**
     * Constructs an initialised reader using a string reference to a text file.
     * 
     * @param reference
     *            a text file name or URL
     * @throws FileNotFoundException
     *             if the file to write is not found
     * @throws IOException
     *             if an I/O error occurs
     */
    public TextFileRowWriter(String reference) throws IOException, FileNotFoundException {
        super(reference);

        initialise();
    }

    /**
     * Constructs an initialised reader using a file.
     * 
     * @param path
     *            the Path to file to be written.
     * @throws FileNotFoundException
     *             if the file to write is not found
     * @throws IOException
     *             if an I/O error occurs
     */
    public TextFileRowWriter(Path path) throws IOException, FileNotFoundException {
        super(path);

        initialise();
    }

    public TextFileRowWriter(BufferedWriter bufferedWriter) throws IOException {
        super();

        if (bufferedWriter != null)
            this.bufferedWriter = bufferedWriter;
        else
            throw new IOException("Buffered writer undefined");

        initialise();
    }

    @Override
    public final void close() {
        try {
            if (bufferedWriter != null)
                bufferedWriter.close();
        } catch (IOException e) {

        }

        bufferedWriter = null;
    }

    public final void flush() {
        try {
            if (bufferedWriter != null)
                bufferedWriter.flush();
        } catch (IOException e) {

        }
    }

    @Override
    public final void writeCellsAsArray(Object[][] cells) throws IOException {
        if (cells != null) {
            writeRowCellsAsArray(cells[0]);

            for (int i = 1; i < cells.length; ++i) {
                newRow();

                writeRowCellsAsArray(cells[i]);
            }
        }
    }

    @Override
    public final void writeCells(List<List<Object>> cells) throws IOException {
        if (cells != null) {
            Iterator<List<Object>> iterator = cells.iterator();

            if (iterator.hasNext()) {
                writeRowCells(iterator.next());

                while (iterator.hasNext()) {
                    newRow();

                    writeRowCells(iterator.next());
                }
            }

            bufferedWriter.flush();
        }
    }

    @Override
    public final void writeRowCellsAsArray(Object[] cells) throws IOException {
        if (cells != null) {
            writeCell(cells[0]);

            for (int i = 1; i < cells.length; ++i) {
                newColumn();

                writeCell(cells[i]);
            }

            bufferedWriter.flush();
        }
    }

    @Override
    public final void writeRowCells(List<Object> cells) throws IOException {
        if (cells != null) {
            Iterator<Object> iterator = cells.iterator();

            if (iterator.hasNext()) {
                writeCell(iterator.next());

                while (iterator.hasNext()) {
                    newColumn();

                    writeCell(iterator.next());
                }
            }

            bufferedWriter.flush();
        }
    }

    @Override
    public final void writeCell(Object cell) throws IOException {
            bufferedWriter.write(convertValue(cell));
    }

    protected String convertValue(Object value) throws IOException {
        if (value != null) {
            if (hasOption(ADD_SINGLE_QUOTES)) {
                String string = ConversionUtilities.convertToString(value) ;
                
                if (string.contains("\"") && !string.contains("'")) {
                    return "'" + string + "'" ;
                } else {
                    if (string.contains("'")) {
                        throw new IOException(String.format("Token : %s contains both single and double quotes!", string)) ;
                    }
                }
            } 
            
            if (hasOption(ADD_DOUBLE_QUOTES)) {
                String string = ConversionUtilities.convertToString(value) ;
                
                if (string.contains("'") && !string.contains("\"")) {
                    return "\"" + string + "\"" ;
                } else {
                    if (string.contains("\"")) {
                        throw new IOException(String.format("Token : %s contains both double and single quotes!", string)) ;
                    }
                }
            } 

            return ConversionUtilities.convertToString(value);
 
        } else {
            return "";
        }

    }

    @Override
    public final boolean newRow() throws IOException {
        bufferedWriter.newLine();

        incrementRowIndex();
        resetCellIndex();

        return true;
    }

    @Override
    public final boolean newColumn() throws IOException {
        bufferedWriter.write(getDelimiterString());

        incrementColumnIndex();

        return true;
    }

    /**
     * Initialises the writer.
     * 
     * @throws FileNotFoundException
     *             if the file to write is not found
     * @throws IOException
     *             if an I/O error occurs
     */
    protected final void initialise() throws FileNotFoundException, IOException {
        super.initialise();

        if (getPathReference() != null)
            initialiseBufferedWriter(getBufferedWriter(getPathReference()));
        else if (getPath() != null)
            initialiseBufferedWriter(getBufferedWriter(getPath()));
        else if (bufferedWriter != null)
            initialiseBufferedWriter(bufferedWriter);
        else
            throw new IOException("Unable to initialise reader");
    }

    @Override
    protected int getValidOptions() {

        return VALID_OPTIONS ;
    }
    
    /**
     * Initialises the reader using a bufferedReader directly.
     * 
     * @param bufferedReader
     *            a buffered reader
     */
    private final void initialiseBufferedWriter(BufferedWriter bufferedWriter) {
        if (bufferedWriter == null)
            throw new NullPointerException(BUFFERWRITER_NULL);

        this.bufferedWriter = bufferedWriter;

        setRowPosition(0);
        setCurrentRowSize(Constants.UNKNOWN_COUNT);
        setRowIndex(Constants.UNKNOWN_INDEX);
    }

    /**
     * Creates a BufferedReader using the string reference to a text file.
     * 
     * @param textFileReference
     *            a text file name or URL
     * @return a bufferedReader
     * 
     * @exception FileNotFoundException
     *                if the file referenced can not be found
     * @exception IOException
     *                if the reader can not open an inputstream to the file
     */
    private static final BufferedWriter getBufferedWriter(Path filePath)
        throws FileNotFoundException, IOException {
        if (filePath != null)
            return Files.newBufferedWriter(filePath);
        else
            throw new FileNotFoundException("File object is null");
    }

    /**
     * Creates a BufferedWriter using the string reference to a text file.
     * 
     * @param textFileReference
     *            a text file name or URL
     * @return a bufferedWriter
     * 
     * @exception FileNotFoundException
     *                if the file referenced can not be found
     * @exception IOException
     *                if the reader can not open an outputstream to the file
     */
    private static final BufferedWriter getBufferedWriter(String fileReference)
        throws FileNotFoundException, IOException {
        BufferedWriter bufferedWriter = null;

        /*
         * try { URL refURL = new java.net.URL(fileReference); bufferedWriter =
         * new BufferedWriter(new OutputStreamWriter(refURL. .openStream())); }
         * catch (MalformedURLException malformedURLException) {
         */
        bufferedWriter = new BufferedWriter(new FileWriter(fileReference));
        // }

        return bufferedWriter;
    }
}
