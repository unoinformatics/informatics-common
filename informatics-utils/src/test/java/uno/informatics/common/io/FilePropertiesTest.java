/*******************************************************************************
 * Copyright 2014 Guy Davenport
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

package uno.informatics.common.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uno.informatics.common.Constants.UNKNOWN_INDEX;

import java.io.File;

import org.junit.Test;

import uno.informatics.data.io.FileType;

/**
 * @author Guy Davenport
 *
 */
public class FilePropertiesTest {
    private static final String FILE = "/path/to/file";
    private static final String FILE2 = "/another/path/to/file";

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String)}.
     */
    @Test
    public void testFilePropertiesString() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(UNKNOWN_INDEX, fileProperties.getColumnHeaderPosition());
        assertFalse(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(0, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String, uno.informatics.data.io.FileType)}.
     */
    @Test
    public void testFilePropertiesStringFileType() {
        FileProperties fileProperties = new FileProperties(FILE, FileType.CSV);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(UNKNOWN_INDEX, fileProperties.getColumnHeaderPosition());
        assertFalse(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(0, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File)}.
     */
    @Test
    public void testFilePropertiesFile() {
        FileProperties fileProperties = new FileProperties(new File(FILE));

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(UNKNOWN_INDEX, fileProperties.getColumnHeaderPosition());
        assertFalse(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(0, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File, uno.informatics.data.io.FileType)}.
     */
    @Test
    public void testFilePropertiesFileFileType() {
        FileProperties fileProperties = new FileProperties(new File(FILE), FileType.CSV);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(UNKNOWN_INDEX, fileProperties.getColumnHeaderPosition());
        assertFalse(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(0, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String, boolean)}.
     */
    @Test
    public void testFilePropertiesStringBoolean() {
        FileProperties fileProperties = new FileProperties(FILE, true);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(0, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(1, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String, uno.informatics.data.io.FileType, boolean)}.
     */
    @Test
    public void testFilePropertiesStringFileTypeBoolean() {
        FileProperties fileProperties = new FileProperties(FILE, FileType.CSV, true);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(0, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(1, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File, boolean)}.
     */
    @Test
    public void testFilePropertiesFileBoolean() {
        FileProperties fileProperties = new FileProperties(new File(FILE), true);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(0, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(1, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File, uno.informatics.data.io.FileType, boolean)}.
     */
    @Test
    public void testFilePropertiesFileFileTypeBoolean() {
        FileProperties fileProperties = new FileProperties(new File(FILE), FileType.CSV, true);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(0, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(1, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String, boolean, boolean)}.
     */
    @Test
    public void testFilePropertiesStringBooleanBoolean() {
        FileProperties fileProperties = new FileProperties(FILE, true, true);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(0, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(0, fileProperties.getRowHeaderPosition());
        assertTrue(fileProperties.hasRowHeader());
        assertEquals(1, fileProperties.getDataRowPosition());
        assertEquals(1, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String, uno.informatics.data.io.FileType, boolean, boolean)}.
     */
    @Test
    public void testFilePropertiesStringFileTypeBooleanBoolean() {
        FileProperties fileProperties = new FileProperties(FILE, FileType.CSV, true, true);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(0, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(0, fileProperties.getRowHeaderPosition());
        assertTrue(fileProperties.hasRowHeader());
        assertEquals(1, fileProperties.getDataRowPosition());
        assertEquals(1, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File, boolean, boolean)}.
     */
    @Test
    public void testFilePropertiesFileBooleanBoolean() {
        FileProperties fileProperties = new FileProperties(new File(FILE), true, true);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(0, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(0, fileProperties.getRowHeaderPosition());
        assertTrue(fileProperties.hasRowHeader());
        assertEquals(1, fileProperties.getDataRowPosition());
        assertEquals(1, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File, uno.informatics.data.io.FileType, boolean, boolean)}.
     */
    @Test
    public void testFilePropertiesFileFileTypeBooleanBoolean() {
        FileProperties fileProperties = new FileProperties(new File(FILE), FileType.CSV, true, true);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(0, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(0, fileProperties.getRowHeaderPosition());
        assertTrue(fileProperties.hasRowHeader());
        assertEquals(1, fileProperties.getDataRowPosition());
        assertEquals(1, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String, int, int)}.
     */
    @Test
    public void testFilePropertiesStringIntInt() {
        FileProperties fileProperties = new FileProperties(FILE, 1, 2);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(1, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(2, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String, uno.informatics.data.io.FileType, int, int)}.
     */
    @Test
    public void testFilePropertiesStringFileTypeIntInt() {
        FileProperties fileProperties = new FileProperties(FILE, FileType.CSV, 1, 2);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(1, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(2, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File, int, int)}.
     */
    @Test
    public void testFilePropertiesFileIntInt() {
        FileProperties fileProperties = new FileProperties(new File(FILE), 1, 2);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(1, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(2, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File, uno.informatics.data.io.FileType, int, int)}.
     */
    @Test
    public void testFilePropertiesFileFileTypeIntInt() {
        FileProperties fileProperties = new FileProperties(new File(FILE), FileType.CSV, 1, 2);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(1, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());
        assertFalse(fileProperties.hasRowHeader());
        assertEquals(2, fileProperties.getDataRowPosition());
        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String, int, int, int)}.
     */
    @Test
    public void testFilePropertiesStringIntIntIntIntInt() {
        FileProperties fileProperties = new FileProperties(FILE, 1, 2, 1, 1);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(1, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(1, fileProperties.getRowHeaderPosition());
        assertTrue(fileProperties.hasRowHeader());
        assertEquals(2, fileProperties.getDataRowPosition());
        assertEquals(1, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.lang.String, uno.informatics.data.io.FileType, int, int, int)}.
     */
    @Test
    public void testFilePropertiesStringFileTypeIntIntIntInt() {
        FileProperties fileProperties = new FileProperties(FILE, FileType.CSV, 1, 2, 1, 1);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(1, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(1, fileProperties.getRowHeaderPosition());
        assertTrue(fileProperties.hasRowHeader());
        assertEquals(2, fileProperties.getDataRowPosition());
        assertEquals(1, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File, int, int, int)}.
     */
    @Test
    public void testFilePropertiesFileIntIntIntInt() {
        FileProperties fileProperties = new FileProperties(new File(FILE), 1, 2, 1, 1);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.TXT, fileProperties.getFileType());
        assertEquals(1, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(1, fileProperties.getRowHeaderPosition());
        assertTrue(fileProperties.hasRowHeader());
        assertEquals(2, fileProperties.getDataRowPosition());
        assertEquals(1, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#FileProperties(java.io.File, uno.informatics.data.io.FileType, int, int, int)}.
     */
    @Test
    public void testFilePropertiesFileFileTypeIntIntIntIntInt() {
        FileProperties fileProperties = new FileProperties(new File(FILE), FileType.CSV, 1, 2, 1, 1);

        assertEquals(new File(FILE), fileProperties.getFile());
        assertEquals(FileType.CSV, fileProperties.getFileType());
        assertEquals(1, fileProperties.getColumnHeaderPosition());
        assertTrue(fileProperties.hasColumnHeader());
        assertEquals(1, fileProperties.getRowHeaderPosition());
        assertTrue(fileProperties.hasRowHeader());
        assertEquals(2, fileProperties.getDataRowPosition());
        assertEquals(1, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#getFile()}.
     */
    @Test
    public void testGetFile() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(new File(FILE), fileProperties.getFile());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#setFile(java.io.File)}.
     */
    @Test
    public void testSetFile() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(new File(FILE), fileProperties.getFile());

        fileProperties.setFile(new File(FILE2));

        assertEquals(new File(FILE2), fileProperties.getFile());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#getFileType()}.
     */
    @Test
    public void testGetFileType() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(FileType.TXT, fileProperties.getFileType());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#setFileType(uno.informatics.data.io.FileType)}.
     */
    @Test
    public void testSetFileType() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(FileType.TXT, fileProperties.getFileType());

        fileProperties.setFileType(FileType.CSV);

        assertEquals(FileType.CSV, fileProperties.getFileType());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#hasRowHeader()}.
     */
    @Test
    public void testHasRowHeader() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertFalse(fileProperties.hasRowHeader());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#getRowHeaderPosition()}.
     */
    @Test
    public void testGetRowHeaderPosition() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());

        assertFalse(fileProperties.hasRowHeader());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#setRowHeaderPosition(int)}.
     */
    @Test
    public void testSetRowHeaderPosition() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(UNKNOWN_INDEX, fileProperties.getRowHeaderPosition());

        fileProperties.setRowHeaderPosition(0);

        assertEquals(0, fileProperties.getRowHeaderPosition());

        assertTrue(fileProperties.hasRowHeader());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#hasColumnHeader()}.
     */
    @Test
    public void testHasColumnHeader() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertFalse(fileProperties.hasColumnHeader());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#getColumnHeaderPosition()}.
     */
    @Test
    public void testGetColumnHeaderPosition() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(UNKNOWN_INDEX, fileProperties.getColumnHeaderPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#setColumnHeaderPosition(int)}.
     */
    @Test
    public void testSetColumnHeaderPosition() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(UNKNOWN_INDEX, fileProperties.getColumnHeaderPosition());

        fileProperties.setColumnHeaderPosition(0);

        assertEquals(0, fileProperties.getColumnHeaderPosition());

        assertTrue(fileProperties.hasColumnHeader());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#getDataRowPosition()}.
     */
    @Test
    public void testgetDataRowPosition() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(0, fileProperties.getDataRowPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#setDataRowPosition(int)}.
     */
    @Test
    public void testSetDataRowPosition() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(0, fileProperties.getDataRowPosition());

        fileProperties.setDataRowPosition(1);

        assertEquals(1, fileProperties.getDataRowPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#getDataColumnPosition()}.
     */
    @Test
    public void testGetDataColumnPosition() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(0, fileProperties.getDataColumnPosition());
    }

    /**
     * Test method for
     * {@link uno.informatics.common.io.FileProperties#setDataColumnPosition(int)}.
     */
    @Test
    public void testSetDataColumnPosition() {
        FileProperties fileProperties = new FileProperties(FILE);

        assertEquals(0, fileProperties.getDataColumnPosition());

        fileProperties.setDataColumnPosition(1);

        assertEquals(1, fileProperties.getDataColumnPosition());
    }
}
