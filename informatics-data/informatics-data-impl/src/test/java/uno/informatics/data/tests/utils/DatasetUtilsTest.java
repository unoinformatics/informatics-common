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

package uno.informatics.data.tests.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import uno.informatics.common.io.FileProperties;
import uno.informatics.common.io.FileType;
import uno.informatics.data.DataType;
import uno.informatics.data.DataTypeConstants;
import uno.informatics.data.Method;
import uno.informatics.data.Ontology;
import uno.informatics.data.OntologyTerm;
import uno.informatics.data.Scale;
import uno.informatics.data.ScaleType;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.feature.ColumnFeature;
import uno.informatics.data.feature.ColumnFeaturePojo;
import uno.informatics.data.pojo.ScalePojo;
import uno.informatics.data.utils.DatasetUtils;

/**
 * @author Guy Davenport
 *
 */
public class DatasetUtilsTest {

    /**
     * Test method for
     * {@link uno.informatics.data.utils.DatasetUtils#generateDatasetFeatures(uno.informatics.common.io.FileProperties, boolean, java.lang.String, int)}
     * .
     */
    @Test
    public void testGenerateDatasetFeatures() {
        try {

            List<ColumnFeature> expected = new ArrayList<ColumnFeature>();

            expected.add(new ColumnFeaturePojo("test1", DataTypeConstants.NUMBER_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("test2", DataTypeConstants.REAL_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("test3", DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("test4", DataTypeConstants.BOOLEAN_ID | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("test5", DataTypeConstants.DATE_ID | DataTypeConstants.STRING_ID));

            assertFeaturesEquals(expected, DatasetUtils.generateDatasetFeatures(
                    Paths.get(this.getClass().getResource("/feature/object_table.txt").getFile()), FileType.TXT, "test", 3));

            expected = new ArrayList<ColumnFeature>();

            expected.add(new ColumnFeaturePojo("1", DataTypeConstants.NUMBER_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("1.1", DataTypeConstants.REAL_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("R1C3", DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("TRUE", DataTypeConstants.BOOLEAN_ID | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("12/12/2012", DataTypeConstants.DATE_ID | DataTypeConstants.STRING_ID));

            assertFeaturesEquals(expected, DatasetUtils.generateDatasetFeatures(
                    Paths.get(this.getClass().getResource("/feature/object_table.txt").getFile()), FileType.TXT, null, 3));

            expected = new ArrayList<ColumnFeature>();

            expected.add(new ColumnFeaturePojo("1", DataTypeConstants.NUMBER_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("1.1", DataTypeConstants.REAL_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("R1C3", DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("TRUE", DataTypeConstants.BOOLEAN_ID | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("12/12/2012", DataTypeConstants.DATE_ID | DataTypeConstants.STRING_ID));

            assertFeaturesEquals(expected, DatasetUtils.generateDatasetFeatures(
                    Paths.get(this.getClass().getResource("/feature/object_table.txt").getFile()), FileType.TXT, null, 3));
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test method for
     * {@link uno.informatics.data.utils.DatasetUtils#getConversionType(uno.informatics.common.model.DataType)}
     * .
     */
    @Test
    public void testGetConversionTypeDataType() {
        assertEquals(DataTypeConstants.BOOLEAN_ID, DataTypeConstants.getConversionType(DataType.BOOLEAN));
        assertEquals(DataTypeConstants.SHORT_ID, DataTypeConstants.getConversionType(DataType.SHORT));
        assertEquals(DataTypeConstants.INT_ID, DataTypeConstants.getConversionType(DataType.INTEGER));
        assertEquals(DataTypeConstants.LONG_ID, DataTypeConstants.getConversionType(DataType.LONG));
        assertEquals(DataTypeConstants.FLOAT_ID, DataTypeConstants.getConversionType(DataType.FLOAT));
        assertEquals(DataTypeConstants.DOUBLE_ID, DataTypeConstants.getConversionType(DataType.DOUBLE));
        assertEquals(DataTypeConstants.BIG_INTEGER_ID, DataTypeConstants.getConversionType(DataType.BIG_INTEGER));
        assertEquals(DataTypeConstants.BIG_DECIMAL_ID, DataTypeConstants.getConversionType(DataType.BIG_DECIMAL));
        assertEquals(DataTypeConstants.DATE_ID, DataTypeConstants.getConversionType(DataType.DATE));
        assertEquals(DataTypeConstants.STRING_ID, DataTypeConstants.getConversionType(DataType.STRING));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.utils.DatasetUtils#getConversionType(uno.informatics.common.model.Scale)}
     * .
     */
    @Test
    public void testGetConversionTypeScale() {
        assertEquals(DataTypeConstants.STRING_ID, DatasetUtils.getConversionType(new ScalePojo("test")));
        assertEquals(DataTypeConstants.BOOLEAN_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.BOOLEAN, ScaleType.NOMINAL)));
        assertEquals(DataTypeConstants.SHORT_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.SHORT, ScaleType.NOMINAL)));
        assertEquals(DataTypeConstants.INT_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.INTEGER, ScaleType.NOMINAL)));
        assertEquals(DataTypeConstants.LONG_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.LONG, ScaleType.NOMINAL)));
        assertEquals(DataTypeConstants.FLOAT_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.FLOAT, ScaleType.NOMINAL)));
        assertEquals(DataTypeConstants.DOUBLE_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.DOUBLE, ScaleType.NOMINAL)));
        assertEquals(DataTypeConstants.BIG_INTEGER_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.BIG_INTEGER, ScaleType.NOMINAL)));
        assertEquals(DataTypeConstants.BIG_DECIMAL_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.BIG_DECIMAL, ScaleType.NOMINAL)));
        assertEquals(DataTypeConstants.DATE_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.DATE, ScaleType.NOMINAL)));
        assertEquals(DataTypeConstants.STRING_ID,
                DatasetUtils.getConversionType(new ScalePojo("test", DataType.STRING, ScaleType.NOMINAL)));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.utils.DatasetUtils#getConversionTypes(uno.informatics.common.model.DataType[])}
     * .
     */
    @Test
    public void testGetConversionTypesDataTypeArray() {
        assertEquals(DataTypeConstants.BOOLEAN_ID, DatasetUtils.getConversionTypes(DataType.BOOLEAN));
        assertEquals(DataTypeConstants.SHORT_ID, DatasetUtils.getConversionTypes(DataType.SHORT));
        assertEquals(DataTypeConstants.INT_ID, DatasetUtils.getConversionTypes(DataType.INTEGER));
        assertEquals(DataTypeConstants.LONG_ID, DatasetUtils.getConversionTypes(DataType.LONG));
        assertEquals(DataTypeConstants.FLOAT_ID, DatasetUtils.getConversionTypes(DataType.FLOAT));
        assertEquals(DataTypeConstants.DOUBLE_ID, DatasetUtils.getConversionTypes(DataType.DOUBLE));
        assertEquals(DataTypeConstants.BIG_INTEGER_ID, DatasetUtils.getConversionTypes(DataType.BIG_INTEGER));
        assertEquals(DataTypeConstants.BIG_DECIMAL_ID, DatasetUtils.getConversionTypes(DataType.BIG_DECIMAL));
        assertEquals(DataTypeConstants.DATE_ID, DatasetUtils.getConversionTypes(DataType.DATE));
        assertEquals(DataTypeConstants.STRING_ID, DatasetUtils.getConversionTypes(DataType.STRING));

        assertEquals(DataTypeConstants.NUMBER_IDS, DatasetUtils.getConversionTypes(DataType.SHORT, DataType.INTEGER,
                DataType.LONG, DataType.FLOAT, DataType.DOUBLE, DataType.BIG_INTEGER, DataType.BIG_DECIMAL));
        assertEquals(DataTypeConstants.INTEGER_IDS,
                DatasetUtils.getConversionTypes(DataType.SHORT, DataType.INTEGER, DataType.LONG, DataType.BIG_INTEGER));
        assertEquals(DataTypeConstants.REAL_IDS,
                DatasetUtils.getConversionTypes(DataType.FLOAT, DataType.DOUBLE, DataType.BIG_DECIMAL));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.utils.DatasetUtils#getConversionTypes(java.util.List)}
     * .
     */
    @Test
    public void testGetConversionTypesListOfFeature() {

    }

    /**
     * Test method for
     * {@link uno.informatics.data.utils.DatasetUtils#getConversionTypes(uno.informatics.common.model.Feature, java.util.List)}
     * .
     */
    @Test
    public void testGetConversionTypesFeatureListOfFeature() {

    }

    /**
     * Test method for
     * {@link uno.informatics.data.utils.DatasetUtils#getDataTypes(int)}.
     */
    @Test
    public void testGetDataTypes() {

    }

    /**
     * Test method for
     * {@link uno.informatics.data.utils.DatasetUtils#getScaleTypes(uno.informatics.common.model.DataType)}
     * .
     */
    @Test
    public void testGetScaleTypes() {

    }

    /**
     * Test method for
     * {@link uno.informatics.data.utils.DatasetUtils#copyFeature(uno.informatics.common.model.Feature)}
     * .
     */
    @Test
    public void testCreateFeature() {

    }

    /**
     * @param expected
     * @param generateDatasetFeatures
     */
    private void assertFeaturesEquals(List<ColumnFeature> expected, List<ColumnFeature> actual) {
        assertEquals(expected.size(), actual.size());

        Iterator<ColumnFeature> iterator1 = expected.iterator();
        Iterator<ColumnFeature> iterator2 = actual.iterator();

        while (iterator1.hasNext() && iterator2.hasNext())
            assertColumnFeatureEquals(iterator1.next(), iterator2.next());

    }

    /**
     * @param next
     * @param next2
     */
    private void assertColumnFeatureEquals(ColumnFeature expected, ColumnFeature actual) {
        assertEquals(expected.getUniqueIdentifier(), actual.getUniqueIdentifier());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getAbbreviation(), actual.getAbbreviation());
        assertEquals(expected.getDataType(), actual.getDataType());
        assertEquals(expected.getScaleType(), actual.getScaleType());

        assertEquals(expected.getPossibleDataTypes(), actual.getPossibleDataTypes());

        assertMethodEquals(expected.getMethod(), actual.getMethod());
        assertOntologyTermEquals(expected.getType(), actual.getType());
    }

    private void assertOntologyTermEquals(OntologyTerm expected, OntologyTerm actual) {
        if (expected != null && actual != null) {
            assertEquals(expected.getUniqueIdentifier(), actual.getUniqueIdentifier());
            assertEquals(expected.getName(), actual.getName());
            assertEquals(expected.getDescription(), actual.getDescription());
            assertEquals(expected.getAbbreviation(), actual.getAbbreviation());

            assertOntologyEquals(expected.getOntology(), actual.getOntology());
        } else {
            assertSame(expected, actual);
        }
    }

    private void assertOntologyEquals(Ontology expected, Ontology actual) {
        assertEquals(expected.getUniqueIdentifier(), actual.getUniqueIdentifier());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getAbbreviation(), actual.getAbbreviation());
    }

    private void assertMethodEquals(Method expected, Method actual) {
        assertEquals(expected.getUniqueIdentifier(), actual.getUniqueIdentifier());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getAbbreviation(), actual.getAbbreviation());

        assertScaleEquals(expected.getScale(), actual.getScale());
    }

    private void assertScaleEquals(Scale expected, Scale actual) {
        assertEquals(expected.getUniqueIdentifier(), actual.getUniqueIdentifier());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getAbbreviation(), actual.getAbbreviation());

        assertEquals(expected.getDataType(), actual.getDataType());
        assertEquals(expected.getScaleType(), actual.getScaleType());

        assertOntologyTermEquals(expected.getType(), actual.getType());
    }
}
