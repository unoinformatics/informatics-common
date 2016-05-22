package uno.informatics.data.tests.feature.array;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import uno.informatics.data.DataTypeConstants;
import uno.informatics.data.Method;
import uno.informatics.data.Ontology;
import uno.informatics.data.OntologyTerm;
import uno.informatics.data.Scale;
import uno.informatics.data.feature.AbstractFeatureData;
import uno.informatics.data.feature.ColumnFeature;
import uno.informatics.data.feature.ColumnFeaturePojo;
import uno.informatics.data.io.FileType;

public class AbstractFeartureDataTest {
    
    @Test
    public void testGenerateDatasetFeaturesIDsOnly() {
        try {

            List<ColumnFeature> expected = new ArrayList<ColumnFeature>();

            expected.add(new ColumnFeaturePojo("col1", DataTypeConstants.NUMBER_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col2", DataTypeConstants.REAL_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col3", DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col4", DataTypeConstants.BOOLEAN_ID | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col5", DataTypeConstants.DATE_ID | DataTypeConstants.STRING_ID));

            assertFeaturesEquals(expected, AbstractFeatureData.generateDatasetFeatures(
                    Paths.get(this.getClass().getResource("/feature/ids_only.csv").getFile()), FileType.CSV, null, 3));

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void testGenerateDatasetFeatureIdsNamesOnCols() {
        try {

            List<ColumnFeature> expected = new ArrayList<ColumnFeature>();

            expected.add(new ColumnFeaturePojo("col1", "Col 1", DataTypeConstants.NUMBER_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col2", "Col 2", DataTypeConstants.REAL_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col3", "Col 3", DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col4", "Col 4", DataTypeConstants.BOOLEAN_ID | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col5", "Col 5", DataTypeConstants.DATE_ID | DataTypeConstants.STRING_ID));

            assertFeaturesEquals(expected, AbstractFeatureData.generateDatasetFeatures(
                    Paths.get(this.getClass().getResource("/feature/col_names.csv").getFile()), FileType.CSV, "test", 3));

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void testGenerateDatasetFeaturesIdsNamesOnRows() {
        try {

            List<ColumnFeature> expected = new ArrayList<ColumnFeature>();

            expected.add(new ColumnFeaturePojo("col1", DataTypeConstants.NUMBER_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col2", DataTypeConstants.REAL_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col3", DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col4", DataTypeConstants.BOOLEAN_ID | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col5", DataTypeConstants.DATE_ID | DataTypeConstants.STRING_ID));

            assertFeaturesEquals(expected, AbstractFeatureData.generateDatasetFeatures(
                    Paths.get(this.getClass().getResource("/feature/row_names.csv").getFile()), FileType.CSV, null, 3));

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void testGenerateDatasetFeaturesIdsNamesOnBoth() {
        try {

            List<ColumnFeature> expected = new ArrayList<ColumnFeature>();

            expected.add(new ColumnFeaturePojo("col1", "Col 1", DataTypeConstants.NUMBER_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col2", "Col 2", DataTypeConstants.REAL_IDS | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col3", "Col 3", DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col4", "Col 4", DataTypeConstants.BOOLEAN_ID | DataTypeConstants.STRING_ID));
            expected.add(new ColumnFeaturePojo("col5", "Col 5", DataTypeConstants.DATE_ID | DataTypeConstants.STRING_ID));

            assertFeaturesEquals(expected, AbstractFeatureData.generateDatasetFeatures(
                    Paths.get(this.getClass().getResource("/feature/row_names_col_names.csv").getFile()), FileType.CSV, null, 3));

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
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
