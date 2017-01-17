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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uno.informatics.common.ConversionException;
import uno.informatics.common.ConversionUtilities;
import uno.informatics.common.io.RowReader;
import uno.informatics.data.DataTypeConstants;

public class TextFileRowReader extends AbstractTextFileHandler implements RowReader {

    /**
     * Sets no options, all options are set to false
     */
    public static final int NO_OPTIONS = 0;

    /**
     * Sets if two more more delimiters are encountered together if these should
     * be treated as one delimiters
     */

    /**
     * Sets if the reader should parse empty strings.
     */
    public static final int PARSE_EMPTY_STRINGS = 1;

    /**
     * Sets if the reader should attempt to convert values.
     */
    public static final int CONVERT_VALUES = 2;

    /**
     * Sets if two more more delimiters are encountered together if these should
     * be treated as one delimiters
     */
    public static final int IGNORE_MULTIPLE_DELIMITERS = 4;

    /**
     * Sets if rows are adjusted to be all the same size 
     * If current row exceeds the row size, row is truncated, whereas if
     * is less than the row size. In the case when the size was not 
     * predefined using the {@link #setFixedRowSize(int)} method,
     * the size is fixed to the size of the first row.
     */
    public static final int ROWS_SAME_SIZE = 8;

    /**
     * Sets if the reader remove any prefix or suffix white space for Strings
     */
    public static final int REMOVE_WHITE_SPACE = 16;

    /**
     * Sets if the reader removes any single or double quotes. If the quotes do
     * not match. Quotes are matched only if they are first and last characters
     * in the token, after any space is trimmed. If {@link #REMOVE_WHITE_SPACE}
     * is used, any white spaces aere removed first. Any delimiters found between quotes
     * are ignored
     */
    public static final int REMOVE_QUOTES = 32;

    private int options = NO_OPTIONS;

    private Map<Integer, Integer> conversionTypesMap;

    private int conversionTypesCount;

    private int[] conversionTypesArray;

    private int defaultConversionTypes;

    private Pattern pattern;

    private BufferedReader bufferedReader;

    private String[] line;
    private String[] nextLine;

    private int defaultInt;

    private double defaultDouble;

    private boolean defaultBoolean;

    private static final String BUFFERREADER_NULL = "Buffer reader is undefined";

    private TextFileRowReader() {
        conversionTypesArray = new int[0];
    }

    /**
     * Constructs an initialised reader using a string reference to a text file.
     * 
     * @param reference
     *            a text file name or URL
     * @throws FileNotFoundException
     *             if the file to read is not found
     * @throws IOException
     *             if an I/O error occurs
     */
    public TextFileRowReader(String reference) throws IOException, FileNotFoundException {
        this();

        if (reference == null)
            throw new FileNotFoundException("File undefined");

        setFileReference(reference);

        initialise();
    }

    /**
     * Constructs an initialised reader using a file.
     * 
     * @param path
     *            the Path to file to be read.
     * @throws FileNotFoundException
     *             if the file to read is not found
     * @throws IOException
     *             if an I/O error occurs
     */
    public TextFileRowReader(Path path) throws IOException, FileNotFoundException {
        this();

        if (path == null)
            throw new FileNotFoundException("Path undefined");

        setPath(path);

        initialise();
    }

    public TextFileRowReader(BufferedReader bufferedReader) throws IOException {
        this();

        if (bufferedReader != null)
            this.bufferedReader = bufferedReader;
        else
            throw new IOException("Buffered reader undefined");

        initialise();
    }

    /**
     * Check to see if the reader is ready to be used and if additional cells
     * can still be read
     *
     * @return <code>true</code> if the reader is ready to be used and if
     *         additional cells can still be read, <code>false</code> otherwise
     */
    public final boolean ready() {
        try {
            if (bufferedReader != null) {
                return bufferedReader.ready();
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Close the reader, disposing of any internal resources
     */
    public final void close() {
        try {
            if (bufferedReader != null)
                bufferedReader.close();
        } catch (IOException e) {

        }

        bufferedReader = null;
    }

    /**
     * Gets an int representing a bit array of options
     * 
     * @return an int representing a bit array of options
     */
    public final int getOptions() {
        return options;
    }

    /**
     * Sets an int representing a bit array of options
     * 
     * @param options
     *            an int representing a bit array of options
     */
    public final void setOptions(int options) throws IOException {
        if (options != this.options) {
            if (isInUse())
                throw new IOException("Options can not be changed while reader is in use");

            this.options = options;

            updatePattern();
        }
    }

    /**
     * Sets the delimiter string.
     * 
     * @param delimiter
     *            the delimiter string
     * @exception IOException
     *                if the reader is already is use
     */
    public final synchronized void setDelimiterString(String delimiter) throws IOException {
        super.setDelimiterString(delimiter);

        updatePattern();
    }

    @Override
    public final Object[][] readCellsAsArray() throws IOException {
        List<Object[]> rows = new LinkedList<Object[]>();

        while (nextRow()) {
            rows.add(getRowCellsAsArray());
        }

        Object[][] cells = new Object[rows.size()][];

        Iterator<Object[]> iterator = rows.iterator();

        int i = 0;

        while (iterator.hasNext()) {
            cells[i] = iterator.next();
            ++i;
        }

        return cells;
    }

    @Override
    public final List<List<Object>> readCells() throws IOException {
        List<List<Object>> cells = new LinkedList<List<Object>>();

        while (nextRow()) {
            cells.add(getRowCells());
        }

        return cells;
    }

    @Override
    public final boolean hasNextRow() {
        if (nextLine != null) {
            return true;
        } else {
            if (ready() && this.getRowPosition() < 0) {
                try {
                    readNextLine();

                    return true;
                } catch (IOException e) {
                    return false;
                }

            } else {
                return false;
            }
        }
    }

    @Override
    public final boolean nextRow() throws IOException {
        if (hasNextRow()) {
            readNextLine();
            incrementRowIndex();
            updateRowSize(line != null ? line.length : 0);

            return true;
        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.io.TableReader#hasNextColumn()
     */
    @Override
    public boolean hasNextColumn() {
        return getColumnIndex() + 1 < getCurrentRowSize();
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.io.TableReader#nextColumn()
     */
    @Override
    public boolean nextColumn() throws IOException {
        if (hasNextColumn()) {
            incrementColumnIndex();

            return true;
        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.io.TableReader#getCell()
     */
    @Override
    public Object getCell() throws IOException {
        return parseCell(getColumnIndex());
    }

    @Override
    public String getCellAsString() throws IOException {
        return parseCellAsString(getColumnIndex());
    }

    @Override
    public double getCellAsDouble() throws IOException {
        return parseCellAsDouble(getColumnIndex());
    }

    @Override
    public int getCellAsInt() throws IOException {
        return parseCellAsInt(getColumnIndex());
    }

    @Override
    public boolean getCellAsBoolean() throws IOException {
        return parseCellAsBoolean(getColumnIndex());
    }

    @Override
    public final List<Object> getRowCells() throws IOException {
        if (this.getRowIndex() < 0)
            throw new IOException("Reader before first row!");

        return parseRowCells(getColumnIndex(), getCurrentRowSize());
    }

    @Override
    public final List<String> getRowCellsAsString() throws IOException {
        if (this.getRowIndex() < 0)
            throw new IOException("Reader before first row!");

        return parseRowCellsAsString(getColumnIndex(), getCurrentRowSize());
    }

    @Override
    public final List<Integer> getRowCellsAsInt() throws IOException {
        if (this.getRowIndex() < 0)
            throw new IOException("Reader before first row!");

        return parseRowCellsAsInteger(getColumnIndex(), getCurrentRowSize());
    }

    @Override
    public final List<Double> getRowCellsAsDouble() throws IOException {
        if (this.getRowIndex() < 0)
            throw new IOException("Reader before first row!");

        return parseRowCellsAsDouble(getColumnIndex(), getCurrentRowSize());
    }

    @Override
    public final List<Boolean> getRowCellsAsBoolean() throws IOException {
        if (this.getRowIndex() < 0)
            throw new IOException("Reader before first row!");

        return parseRowCellsAsBoolean(getColumnIndex(), getCurrentRowSize());
    }

    @Override
    public final Object[] getRowCellsAsArray() throws IOException {
        return parseRowCellsAsArray(getColumnIndex(), getCurrentRowSize());
    }

    @Override
    public final String[] getRowCellsAsStringArray() throws IOException {
        return parseRowCellsAsStringArray(getColumnIndex(), getCurrentRowSize());
    }

    @Override
    public final int[] getRowCellsAsIntArray() throws IOException {
        return parseRowCellsAsIntArray(getColumnIndex(), getCurrentRowSize());
    }

    @Override
    public final double[] getRowCellsAsDoubleArray() throws IOException {
        return parseRowCellsAsDoubleArray(getColumnIndex(), getCurrentRowSize());
    }

    @Override
    public final boolean[] getRowCellsAsBooleanArray() throws IOException {
        return parseRowCellsAsBooleanArray(getColumnIndex(), getCurrentRowSize());
    }

    private Object parseValue(String text, int rowIndex, int columnIndex) throws IOException {
        try {
            return convertValue(text);
        } catch (ClassCastException e) {
            throw new IOException("Can not parse cell position " + rowIndex + "," + columnIndex + " due to "
                + e.getLocalizedMessage(), e);
        }
    }

    private Object parseValue(String text, int rowIndex, int columnIndex, int conversionTypes)
        throws IOException {
        try {
            return convertValue(text, conversionTypes);
        } catch (Exception e) {
            throw new IOException("Can not parse cell position " + rowIndex + "," + columnIndex + " due to "
                + e.getLocalizedMessage(), e);
        }
    }

    private Object convertValue(String text) {
        return ConversionUtilities.convertToObject(text);
    }

    private Object convertValue(String text, int dataTypes) throws ConversionException {
        return ConversionUtilities.convertToObject(text, dataTypes);
    }

    /**
     * Initialises the reader.
     * 
     * @throws FileNotFoundException
     *             if the file to read is not found
     * @throws IOException
     *             if an I/O error occurs
     */
    protected final void initialise() throws FileNotFoundException, IOException {
        super.initialise();

        defaultConversionTypes = DataTypeConstants.DEFAULT_TYPE_IDS;

        conversionTypesMap = new TreeMap<Integer, Integer>();

        conversionTypesCount = 0;

        conversionTypesArray = null;

        updatePattern();

        if (getPathReference() != null)
            initialiseBufferedReader(getBufferReader(getPathReference()));
        else if (getPath() != null)
            initialiseBufferedReader(getBufferReader(getPath()));
        else if (bufferedReader != null)
            initialiseBufferedReader(bufferedReader);
        else
            throw new IOException("Unable to initialise reader");
    }
    
    @Override
    protected final void updateRowSize(int rowSize) {
        if (hasOption(ROWS_SAME_SIZE)) {
            if (getCurrentRowSize() < 0) {
                super.updateRowSize(rowSize) ; // only set if not set before
            }
        } else {
            super.updateRowSize(rowSize) ;
        }        
    }

    /**
     * Reads the next valid line if possible into memory
     * 
     * @throws IOException
     */
    private void readNextLine() throws IOException {
        line = nextLine;
        nextLine = null;
        setColumnIndex(-1);

        while (bufferedReader.ready() && nextLine == null) {
            nextLine = readLine();

            incrementRowPosition();
        }
    }

    /**
     * @return a tokenised version of the next line, or <code>null if the next
     *         line is a comment or an empty line when not in strict mode
     * 
     * @throws IOException
     */
    private String[] readLine() throws IOException {
        String line = bufferedReader.readLine();

        if (line != null) {
            // ignore any commented record or empty lines if not in strict mode
            if (((line.trim().length() == 0 && isInStrictMode())
                || (getCommentString() != null && line.trim().startsWith(getCommentString())))) {
                return null;
            } else {
                return parseLine(line.trim());
            }
        } else {
            return null ;
        }
    }

    private String[] parseLine(String line) {

        // makes sure it parses the last token, not sure if there is better option
        //line = line + getDelimiterString() ; 
        
        Matcher matcher = pattern.matcher(line);

        List<String> tokens = new LinkedList<String>();
        
        if (line.startsWith(getDelimiterString())) {
            tokens.add("");
        }
        
        if (hasOption(REMOVE_QUOTES)) { // 3 groups
            if (hasOption(IGNORE_MULTIPLE_DELIMITERS)) {
                while (matcher.find()) {
                    if (matcher.group(1) != null) {
                        // Add double-quoted string without the quotes
                        tokens.add(matcher.group(1));
                    } else if (matcher.group(2) != null) {
                        // Add single-quoted string without the quotes
                        tokens.add(matcher.group(2));
                    } else if (matcher.group(3) != null) {
                        // Add unquoted token
                        tokens.add(matcher.group(3));
                    }
                } 
            } else {
                while (matcher.find()) { // 4 groups
                    if (matcher.group(1) != null) {
                        // Add double-quoted string without the quotes
                        tokens.add(matcher.group(1));
                    } else if (matcher.group(2) != null) {
                        // Add single-quoted string without the quotes
                        tokens.add(matcher.group(2));
                    } else if (matcher.group(3) != null) {
                        // Add unquoted token
                        tokens.add(matcher.group(3));
                    } else if (matcher.group(4) != null) {
                        // 2 or more delimiters together, add space for each 'gap' 
                        for (int i = 1 ; i < matcher.group(4).length() ; ++i) {
                            tokens.add("");
                        }
                    } 
                } 
            }
        } else {
            if (hasOption(IGNORE_MULTIPLE_DELIMITERS)) { // 1 group
                while (matcher.find()) {
                    if (matcher.group(1) != null) {
                        // Add unquoted token
                        tokens.add(matcher.group(1));
                    }
                } 
            } else {
                while (matcher.find()) {
                    if (matcher.group(1) != null) { // 2 groups
                        // Add unquoted token
                        tokens.add(matcher.group(1));
                    } else if (matcher.group(2) != null) {
                        // 2 or more delimiters together, add space for each 'gap' 
                        for (int i = 1 ; i < matcher.group(2).length() ; ++i) {
                            tokens.add("");
                        }
                    } 
                } 
            }
        }
        
        if (matcher.hitEnd() && line.endsWith(getDelimiterString())) {
            tokens.add("");
        }

        return tokens.toArray(new String[tokens.size()]);
    }

    /**
     * Initialises the reader using a bufferedReader directly.
     * 
     * @param bufferedReader
     *            a buffered reader
     */
    private final void initialiseBufferedReader(BufferedReader bufferedReader) {
        if (bufferedReader == null)
            throw new NullPointerException(BUFFERREADER_NULL);

        this.bufferedReader = bufferedReader;
    }

    private final void updatePattern() {
        // TODO need to check for special characters
        String regex;

        String delimiter = getDelimiterString();

        if (hasOption(REMOVE_QUOTES)) {
            if (hasOption(IGNORE_MULTIPLE_DELIMITERS)) {
                regex = "[ ]*\"([^\"]*)\"[ ]*["+ delimiter +"]?|[ ]*'([^']*)'[ ]*["+ delimiter +"]?|([^"+ delimiter +"\"']+)" ;
            } else {
                regex = "[ ]*\"([^\"]*)\"[ ]*["+ delimiter +"]?|[ ]*'([^']*)'[ ]*["+ delimiter +"]?|([^"+ delimiter +"\"']+)|(["+ delimiter +"]{2,})" ;
            }
        } else {
            if (hasOption(IGNORE_MULTIPLE_DELIMITERS)) {
                regex = "([^" + delimiter + "]+)|(["+ delimiter +"])";
            } else {
                regex = "([^" + delimiter + "]+)|(["+ delimiter +"]{2,})";
            }
        }

        pattern = Pattern.compile(regex, Pattern.DOTALL);
    }

    protected String convertToken(String string) {
        if (hasOption(REMOVE_WHITE_SPACE)) {
            return convertTokenWithTrim(string);
        } else {
            return convertTokenWithoutTrim(string);
        }
    }

    private String convertTokenWithTrim(String string) {

        if (string != null) {
            String token = convertTokenWithoutTrim(string.trim());
            
            if (token != null) {
                return token.trim();
            }
        }
        
        return null;
    }

    private String convertTokenWithoutTrim(String string) {

        if (hasOption(PARSE_EMPTY_STRINGS)) {
            return string;
        } else {
            if (string != null) {
                if ("".equals(string.trim())) {
                    return null;
                } else {
                    return string;
                }
            } else {
                return null;
            }
        }
    }

    public int getDefaultConversionTypes() {
        return defaultConversionTypes;
    }

    public final void setDefaultConversionTypes(int defaultConversionTypes) {
        this.defaultConversionTypes = defaultConversionTypes;
    }

    public final int getConversionTypes(int index) {
        if (index >= 0 && conversionTypesMap.containsKey(index))
            return conversionTypesMap.get(index);
        else
            return defaultConversionTypes;
    }

    public final int[] getAllConversionTypes() {
        if (conversionTypesArray == null) {
            conversionTypesArray = new int[conversionTypesCount];

            Iterator<Entry<Integer, Integer>> iterator = conversionTypesMap.entrySet().iterator();

            Entry<Integer, Integer> entry = null;

            while (iterator.hasNext()) {
                entry = iterator.next();

                conversionTypesArray[entry.getKey()] = entry.getValue();
            }
        }

        return conversionTypesArray;
    }

    public final void setAllConversionTypes(int[] conversionTypes) {
        conversionTypesArray = null;
        conversionTypesCount = conversionTypes.length;
        for (int i = 0; i < conversionTypes.length; ++i)
            conversionTypesMap.put(i, conversionTypes[i]);
    }

    public final void setConversionTypes(int conversionTypes, int index) {
        if (index >= 0) {
            conversionTypesMap.put(index, conversionTypes);

            if (index >= conversionTypesCount) {
                conversionTypesCount = index + 1;
                conversionTypesArray = null;
            } else {
                conversionTypesArray[index] = conversionTypes;
            }
        }
    }

    private Object parseCell(int index) throws IOException {
        if (line != null && index < line.length) {

            if (conversionTypesCount > 0) {
                return parseValue(convertToken(line[index]), getRowIndex(), index, getConversionTypes(index));
            } else {
                return parseValue(convertToken(line[index]), getRowIndex(), index);
            }
        } else {
            return null;
        }
    }

    private String parseCellAsString(int index) throws IOException {
        if (line != null) {
            try {
                return convertToken(line[index]);
            } catch (Exception e) {
                throw new IOException("Can not parse cell position " + getRowIndex() + "," + index
                    + " due to " + e.getLocalizedMessage(), e);
            }
        } else {
            throw new IOException("Can not convert to string");
        }
    }

    private int parseCellAsInt(int index) throws IOException {
        if (line != null) {
            try {
                Integer value = ConversionUtilities.convertToInteger(convertTokenWithoutTrim(line[index]));
                if (value != null)
                    return (int) value;
                else
                    return defaultInt;
            } catch (Exception e) {
                throw new IOException("Can not parse cell position " + getRowIndex() + "," + index
                    + " due to " + e.getLocalizedMessage(), e);
            }
        } else {
            throw new IOException("Can not convert to int");
        }
    }

    private Integer parseCellAsIntegerObject(int index) throws IOException {
        if (line != null) {
            try {
                return ConversionUtilities.convertToInteger(convertTokenWithoutTrim(line[index]));
            } catch (Exception e) {
                throw new IOException("Can not parse cell position " + getRowIndex() + "," + index
                    + " due to " + e.getLocalizedMessage(), e);
            }
        } else {
            throw new IOException("Can not convert to int");
        }
    }

    private double parseCellAsDouble(int index) throws IOException {
        if (line != null) {
            try {
                Double value = ConversionUtilities.convertToDouble(convertTokenWithoutTrim(line[index]));
                if (value != null)
                    return (double) value;
                else
                    return defaultDouble;
            } catch (Exception e) {
                throw new IOException("Can not parse cell position " + getRowIndex() + "," + index
                    + " due to " + e.getLocalizedMessage(), e);
            }
        } else {
            throw new IOException("Can not convert to int");
        }
    }

    private Double parseCellAsDoubleObject(int index) throws IOException {
        if (line != null) {
            try {
                return ConversionUtilities.convertToDouble(convertTokenWithoutTrim(line[index]));
            } catch (Exception e) {
                throw new IOException("Can not parse cell position " + getRowIndex() + "," + index
                    + " due to " + e.getLocalizedMessage(), e);
            }
        } else {
            throw new IOException("Can not convert to int");
        }
    }

    private boolean parseCellAsBoolean(int index) throws IOException {
        if (line != null) {
            try {
                Boolean value = ConversionUtilities.convertToBoolean(convertTokenWithoutTrim(line[index]));
                if (value != null)
                    return (boolean) value;
                else
                    return defaultBoolean;
            } catch (Exception e) {
                throw new IOException("Can not parse cell position " + getRowIndex() + "," + index
                    + " due to " + e.getLocalizedMessage(), e);
            }
        } else {
            throw new IOException("Can not convert to int");
        }
    }

    private Boolean parseCellAsBooleanObject(int index) throws IOException {
        if (line != null) {
            try {
                return ConversionUtilities.convertToBoolean(convertTokenWithoutTrim(line[index]));
            } catch (Exception e) {
                throw new IOException("Can not parse cell position " + getRowIndex() + "," + index
                    + " due to " + e.getLocalizedMessage(), e);
            }
        } else {
            throw new IOException("Can not convert to int");
        }
    }

    private List<Object> parseRowCells(int firstIndex, int requestedSize) throws IOException {
        ArrayList<Object> row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                row = new ArrayList<Object>(requestedSize - startIndex);

                if (conversionTypesCount > 0) {
                    if (hasOption(REMOVE_WHITE_SPACE)) {
                        for (int i = startIndex; i < size; ++i)
                            row.add(parseValue(convertTokenWithTrim(line[i]), getRowIndex(), i,
                                getConversionTypes(i)));
                    } else {
                        for (int i = startIndex; i < size; ++i)
                            row.add(parseValue(convertTokenWithoutTrim(line[i]), getRowIndex(), i));
                    }
                } else {
                    if (hasOption(REMOVE_WHITE_SPACE)) {
                        for (int i = startIndex; i < size; ++i)
                            row.add(parseValue(convertTokenWithTrim(line[i]), getRowIndex(), i,
                                getConversionTypes(i)));
                    } else {
                        for (int i = startIndex; i < size; ++i)
                            row.add(parseValue(convertTokenWithoutTrim(line[i]), getRowIndex(), i));
                    }
                }

                for (int i = size; i < requestedSize; ++i)
                    row.add(null);
            } else {
                row = new ArrayList<Object>(size - startIndex);

                if (conversionTypesCount > 0) {
                    if (hasOption(REMOVE_WHITE_SPACE)) {
                        for (int i = startIndex; i < size; ++i)
                            row.add(parseValue(convertTokenWithTrim(line[i]), getRowIndex(), i,
                                getConversionTypes(i)));
                    } else {
                        for (int i = startIndex; i < size; ++i)
                            row.add(parseValue(convertTokenWithoutTrim(line[i]), getRowIndex(), i));
                    }
                } else {
                    if (hasOption(REMOVE_WHITE_SPACE)) {
                        for (int i = startIndex; i < size; ++i)
                            row.add(parseValue(convertTokenWithTrim(line[i]), getRowIndex(), i,
                                getConversionTypes(i)));
                    } else {
                        for (int i = startIndex; i < size; ++i)
                            row.add(parseValue(convertTokenWithoutTrim(line[i]), getRowIndex(), i));
                    }
                }
            }
        } else {
            row = new ArrayList<Object>();
        }

        return row;
    }

    private List<String> parseRowCellsAsString(int firstIndex, int requestedSize) throws IOException {
        ArrayList<String> row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                if (hasOption(ROWS_SAME_SIZE)) {
                    row = new ArrayList<String>(requestedSize - startIndex);

                    if (hasOption(REMOVE_WHITE_SPACE))
                        for (int i = startIndex; i < size; ++i)
                            row.add(convertTokenWithTrim(line[i]));
                    else
                        for (int i = startIndex; i < size; ++i)
                            row.add(convertTokenWithoutTrim(line[i]));

                    for (int i = size; i < requestedSize; ++i)
                        row.add(null);
                } else {
                    row = new ArrayList<String>(size - startIndex);

                    if (hasOption(REMOVE_WHITE_SPACE))
                        for (int i = startIndex; i < size; ++i)
                            row.add(convertTokenWithTrim(line[i]));
                    else
                        for (int i = startIndex; i < size; ++i)
                            row.add(convertTokenWithoutTrim(line[i]));
                }
            } else {
                if (hasOption(ROWS_SAME_SIZE)) {
                    row = new ArrayList<String>(requestedSize - startIndex);

                    if (hasOption(REMOVE_WHITE_SPACE))
                        for (int i = startIndex; i < size; ++i)
                            row.add(convertTokenWithTrim(line[i]));
                    else
                        for (int i = startIndex; i < size; ++i)
                            row.add(convertTokenWithoutTrim(line[i]));

                    for (int i = size; i < requestedSize; ++i)
                        row.add(null);
                } else {
                    row = new ArrayList<String>(size - startIndex);

                    if (hasOption(REMOVE_WHITE_SPACE))
                        for (int i = startIndex; i < size; ++i)
                            row.add(convertTokenWithTrim(line[i]));
                    else
                        for (int i = startIndex; i < size; ++i)
                            row.add(convertTokenWithoutTrim(line[i]));
                }
            }

        } else {
            row = new ArrayList<String>();
        }

        return row;
    }

    private List<Integer> parseRowCellsAsInteger(int firstIndex, int requestedSize) throws IOException {
        ArrayList<Integer> row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                row = new ArrayList<Integer>(requestedSize - startIndex);

                for (int i = startIndex; i < size; ++i)
                    row.add(parseCellAsIntegerObject(i));

                for (int i = size; i < requestedSize; ++i)
                    row.add(null);
            } else {
                row = new ArrayList<Integer>(size - startIndex);

                for (int i = startIndex; i < size; ++i)
                    row.add(parseCellAsIntegerObject(i));
            }

        } else {
            row = new ArrayList<Integer>();
        }

        return row;
    }

    private List<Double> parseRowCellsAsDouble(int firstIndex, int requestedSize) throws IOException {
        ArrayList<Double> row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                row = new ArrayList<Double>(requestedSize - startIndex);

                for (int i = startIndex; i < size; ++i)
                    row.add(parseCellAsDoubleObject(i));

                for (int i = size; i < requestedSize; ++i)
                    row.add(null);
            } else {
                row = new ArrayList<Double>(size - startIndex);

                for (int i = startIndex; i < size; ++i)
                    row.add(parseCellAsDoubleObject(i));
            }
        } else {
            row = new ArrayList<Double>();
        }

        return row;
    }

    private List<Boolean> parseRowCellsAsBoolean(int firstIndex, int requestedSize) throws IOException {
        ArrayList<Boolean> row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                row = new ArrayList<Boolean>(requestedSize - startIndex);

                for (int i = startIndex; i < size; ++i)
                    row.add(parseCellAsBooleanObject(i));

                for (int i = size; i < requestedSize; ++i)
                    row.add(null);
            } else {
                row = new ArrayList<Boolean>(size - startIndex);

                for (int i = startIndex; i < size; ++i)
                    row.add(parseCellAsBooleanObject(i));
            }

        } else {
            row = new ArrayList<Boolean>();
        }

        return row;
    }

    private Object[] parseRowCellsAsArray(int firstIndex, int requestedSize) throws IOException {
        Object[] row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                row = new Object[requestedSize - startIndex];
            } else {
                row = new Object[size - startIndex];
            }

            if (conversionTypesCount > 0) {
                if (hasOption(REMOVE_WHITE_SPACE)) {
                    for (int i = startIndex; i < size; ++i)
                        row[i - startIndex] = parseValue(convertTokenWithTrim(line[i]), getRowIndex(), i,
                            getConversionTypes(i));
                } else {
                    for (int i = startIndex; i < size; ++i)
                        row[i - startIndex] = parseValue(convertTokenWithoutTrim(line[i]), getRowIndex(), i);
                }
            } else {
                if (hasOption(REMOVE_WHITE_SPACE)) {
                    for (int i = startIndex; i < size; ++i)
                        row[i - startIndex] = parseValue(convertTokenWithTrim(line[i]), getRowIndex(), i,
                            getConversionTypes(i));
                } else {
                    for (int i = startIndex; i < size; ++i)
                        row[i - startIndex] = parseValue(convertTokenWithoutTrim(line[i]), getRowIndex(), i);
                }
            }
        } else {
            row = new Object[0];
        }

        return row;
    }

    private String[] parseRowCellsAsStringArray(int firstIndex, int requestedSize) throws IOException {
        String[] row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                row = new String[requestedSize - startIndex];
            } else {
                row = new String[size - startIndex];
            }

            if (this.hasOption(REMOVE_WHITE_SPACE)) {
                for (int i = startIndex; i < size; ++i) {
                    row[i - startIndex] = convertTokenWithTrim(line[i]);
                }
            } else {
                for (int i = startIndex; i < size; ++i) {
                    row[i - startIndex] = convertTokenWithoutTrim(line[i]);
                }
            }
        } else {
            row = new String[0];
        }

        return row;
    }

    private int[] parseRowCellsAsIntArray(int firstIndex, int requestedSize) throws IOException {
        int[] row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                row = new int[requestedSize - startIndex];
            } else {
                row = new int[size - startIndex];
            }

            for (int i = startIndex; i < size; ++i)
                row[i - startIndex] = parseCellAsInt(i);
        } else {
            row = new int[0];
        }

        return row;
    }

    private double[] parseRowCellsAsDoubleArray(int firstIndex, int requestedSize) throws IOException {
        double[] row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                row = new double[requestedSize - startIndex];
            } else {
                row = new double[size - startIndex];
            }

            for (int i = startIndex; i < size; ++i)
                row[i - startIndex] = parseCellAsDouble(i);
        } else {
            row = new double[0];
        }

        return row;
    }

    private boolean[] parseRowCellsAsBooleanArray(int firstIndex, int requestedSize) throws IOException {
        boolean[] row;

        if (line != null) {
            int size = requestedSize < line.length ? requestedSize : line.length;
            int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex;

            if (hasOption(ROWS_SAME_SIZE)) {
                row = new boolean[requestedSize - startIndex];
            } else {
                row = new boolean[size - startIndex];
            }

            for (int i = startIndex; i < size; ++i)
                row[i - startIndex] = parseCellAsBoolean(i);
        } else {
            row = new boolean[0];
        }

        return row;
    }

    private boolean hasOption(int option) {
        return (options & option) > 0;
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
     *                if the reader can not open an input stream to the file
     */
    private static final BufferedReader getBufferReader(Path path) throws FileNotFoundException, IOException {
        // TODO support for other Charsets
        if (path != null)
            return Files.newBufferedReader(path);
        else
            throw new FileNotFoundException("File object is null");
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
     *                if the reader can not open an input stream to the file
     */
    private static final BufferedReader getBufferReader(String fileReference)
        throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = null;

        try {
            URL refURL = new java.net.URL(fileReference);
            bufferedReader = new BufferedReader(new InputStreamReader(refURL.openStream()));
        } catch (MalformedURLException malformedURLException) {
            bufferedReader = new BufferedReader(new FileReader(fileReference));
        }

        return bufferedReader;
    }
}
