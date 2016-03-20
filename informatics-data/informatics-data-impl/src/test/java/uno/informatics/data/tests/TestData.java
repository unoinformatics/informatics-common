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

package uno.informatics.data.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uno.informatics.common.ConversionException;
import uno.informatics.common.ConversionUtilities;
import uno.informatics.data.DataType;
import uno.informatics.data.Feature;
import uno.informatics.data.Method;
import uno.informatics.data.Scale;
import uno.informatics.data.ScaleType;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.dataset.FeatureDataRow;
import uno.informatics.data.feature.array.ArrayFeatureDatasetRow;
import uno.informatics.data.pojo.FeaturePojo;
import uno.informatics.data.pojo.MethodPojo;
import uno.informatics.data.pojo.ScalePojo;
import uno.informatics.data.pojo.SimpleEntityPojo;

/**
 * @author Guy Davenport
 * 
 */
public class TestData {
    protected final static String UID = "uid";
    protected final static String NAME = "name";
    protected final static String DESCRIPTION = "description";

    protected final static SimpleEntity ROW_HEADER = new SimpleEntityPojo(UID, NAME);

    protected final static String ROW1_NAME = "row1";
    protected final static String ROW2_NAME = "row2";
    protected final static String ROW3_NAME = "row3";

    protected final static String[] ROW_NAMES = new String[] { ROW1_NAME, ROW2_NAME, ROW3_NAME };
    protected final static String[] BLANK_NAMES = new String[] { null, null, null };

    protected final static SimpleEntity ROW1_HEADER = new SimpleEntityPojo(ROW1_NAME);
    protected final static SimpleEntity ROW2_HEADER = new SimpleEntityPojo(ROW2_NAME);
    protected final static SimpleEntity ROW3_HEADER = new SimpleEntityPojo(ROW3_NAME);

    protected final static SimpleEntity[] ROW_HEADERS = new SimpleEntity[] { ROW1_HEADER, ROW2_HEADER, ROW3_HEADER };

    protected final static List<SimpleEntity> ROW_HEADERS_AS_LIST = new ArrayList<SimpleEntity>();

    static {
        ROW_HEADERS_AS_LIST.add(ROW1_HEADER);
        ROW_HEADERS_AS_LIST.add(ROW2_HEADER);
        ROW_HEADERS_AS_LIST.add(ROW3_HEADER);
    }

    protected final static SimpleEntity ROW1_HEADER_WITH_ID = new SimpleEntityPojo("r1", ROW1_NAME);
    protected final static SimpleEntity ROW2_HEADER_WITH_ID = new SimpleEntityPojo("r2", ROW2_NAME);
    protected final static SimpleEntity ROW3_HEADER_WITH_ID = new SimpleEntityPojo("r3", ROW3_NAME);

    protected final static SimpleEntity[] ROW_HEADERS_WITH_ID = new SimpleEntity[] { ROW1_HEADER_WITH_ID,
            ROW2_HEADER_WITH_ID, ROW3_HEADER_WITH_ID };

    protected final static List<SimpleEntity> ROW_HEADERS_WITH_IDS_AS_LIST = new ArrayList<SimpleEntity>();

    static {
        ROW_HEADERS_WITH_IDS_AS_LIST.add(ROW1_HEADER_WITH_ID);
        ROW_HEADERS_WITH_IDS_AS_LIST.add(ROW2_HEADER_WITH_ID);
        ROW_HEADERS_WITH_IDS_AS_LIST.add(ROW3_HEADER_WITH_ID);
    }

    protected final static List<String> ROW_NAMES_AS_LIST = new ArrayList<String>();

    static {
        ROW_NAMES_AS_LIST.add(ROW1_NAME);
        ROW_NAMES_AS_LIST.add(ROW2_NAME);
        ROW_NAMES_AS_LIST.add(ROW3_NAME);
    }

    protected final static SimpleEntity[] BLANK_HEADERS = new SimpleEntity[] { null, null, null };

    protected final static Object[] OBJECT_ROW1 = new Object[] { 1, 1.1, "R1C3", true, createDate("12/12/2012") };
    protected final static Object[] OBJECT_ROW2 = new Object[] { 2, 2.2, "R2C3", false, createDate("13/12/2012") };
    protected final static Object[] OBJECT_ROW3 = new Object[] { 3, 3.3, "R3C3", true, createDate("14/12/2012") };

    protected final static Object[][] OBJECT_TABLE_AS_ARRAY = new Object[][] { OBJECT_ROW1, OBJECT_ROW2, OBJECT_ROW3 };

    protected final static Object[] OBJECT_ROW1_WITH_HEADER = new Object[] { ROW1_HEADER, 1, 1.1, "R1C3", true,
            createDate("12/12/2012") };
    protected final static Object[] OBJECT_ROW2_WITH_HEADER = new Object[] { ROW2_HEADER, 2, 2.2, "R2C3", false,
            createDate("13/12/2012") };
    protected final static Object[] OBJECT_ROW3_WITH_HEADER = new Object[] { ROW3_HEADER, 3, 3.3, "R3C3", true,
            createDate("14/12/2012") };

    protected final static Object[][] OBJECT_TABLE_AS_ARRAY_WITH_HEADER = new Object[][] { OBJECT_ROW1_WITH_HEADER,
            OBJECT_ROW2_WITH_HEADER, OBJECT_ROW3_WITH_HEADER };

    protected static final List<List<Object>> OBJECT_TABLE_AS_LIST = new ArrayList<List<Object>>();

    static {
        OBJECT_TABLE_AS_LIST.add(new ArrayList<Object>(OBJECT_ROW1.length));

        OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[0]);
        OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[1]);
        OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[2]);
        OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[3]);
        OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[4]);

        OBJECT_TABLE_AS_LIST.add(new ArrayList<Object>(OBJECT_ROW2.length));

        OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[0]);
        OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[1]);
        OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[2]);
        OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[3]);
        OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[4]);

        OBJECT_TABLE_AS_LIST.add(new ArrayList<Object>(OBJECT_ROW2.length));

        OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[0]);
        OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[1]);
        OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[2]);
        OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[3]);
        OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[4]);
    }

    protected final static Object[] OBJECT_COL1 = new Object[] { OBJECT_ROW1[0], OBJECT_ROW2[0], OBJECT_ROW3[0] };

    protected final static Object[] OBJECT_COL2 = new Object[] { OBJECT_ROW1[1], OBJECT_ROW2[1], OBJECT_ROW3[1] };

    protected final static Object[] OBJECT_COL3 = new Object[] { OBJECT_ROW1[2], OBJECT_ROW2[2], OBJECT_ROW3[2] };

    protected final static Object[] OBJECT_COL4 = new Object[] { OBJECT_ROW1[3], OBJECT_ROW2[3], OBJECT_ROW3[3] };

    protected final static Object[] OBJECT_COL5 = new Object[] { OBJECT_ROW1[4], OBJECT_ROW2[4], OBJECT_ROW3[4] };

    protected static final List<List<Object>> OBJECT_TABLE_AS_LIST_WITH_HEADER = new ArrayList<List<Object>>();

    static {
        OBJECT_TABLE_AS_LIST_WITH_HEADER.add(new ArrayList<Object>(OBJECT_ROW1_WITH_HEADER.length));

        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(0).add(OBJECT_ROW1_WITH_HEADER[0]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(0).add(OBJECT_ROW1_WITH_HEADER[1]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(0).add(OBJECT_ROW1_WITH_HEADER[2]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(0).add(OBJECT_ROW1_WITH_HEADER[3]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(0).add(OBJECT_ROW1_WITH_HEADER[4]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(0).add(OBJECT_ROW1_WITH_HEADER[5]);

        OBJECT_TABLE_AS_LIST_WITH_HEADER.add(new ArrayList<Object>(OBJECT_ROW2_WITH_HEADER.length));

        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(1).add(OBJECT_ROW2_WITH_HEADER[0]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(1).add(OBJECT_ROW2_WITH_HEADER[1]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(1).add(OBJECT_ROW2_WITH_HEADER[2]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(1).add(OBJECT_ROW2_WITH_HEADER[3]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(1).add(OBJECT_ROW2_WITH_HEADER[4]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(1).add(OBJECT_ROW2_WITH_HEADER[5]);

        OBJECT_TABLE_AS_LIST_WITH_HEADER.add(new ArrayList<Object>(OBJECT_ROW3_WITH_HEADER.length));

        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(2).add(OBJECT_ROW3_WITH_HEADER[0]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(2).add(OBJECT_ROW3_WITH_HEADER[1]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(2).add(OBJECT_ROW3_WITH_HEADER[2]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(2).add(OBJECT_ROW3_WITH_HEADER[3]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(2).add(OBJECT_ROW3_WITH_HEADER[4]);
        OBJECT_TABLE_AS_LIST_WITH_HEADER.get(2).add(OBJECT_ROW3_WITH_HEADER[5]);
    }

    protected final static Object[] STRING_ROW1 = new Object[] { "1", "1.1", "R1C3", "TRUE", "12/12/2012" };
    protected final static Object[] STRING_ROW2 = new Object[] { "2", "2.2", "R2C3", "FALSE", "13/12/2012" };
    protected final static Object[] STRING_ROW3 = new Object[] { "3", "3.3", "R3C3", "TRUE", "14/12/2012" };

    protected final static Object[] STRING_COL1 = new Object[] { "1", "2", "3" };

    protected final static Object[] STRING_COL2 = new Object[] { "1.1", "2.2", "3.3" };

    protected final static Object[] STRING_COL3 = new Object[] { "R1C3", "R2C3", "R3C3" };

    protected final static Object[] STRING_COL4 = new Object[] { "TRUE", "FALSE" };

    protected final static Object[] STRING_COL5 = new Object[] { "12/12/2012", "13/12/2012", "14/12/2012" };

    protected final static Object[][] STRING_TABLE_AS_ARRAY = new Object[][] { STRING_ROW1, STRING_ROW2, STRING_ROW3 };

    protected final static Object[] STRING_ROW1_WITH_HEADER = new Object[] { ROW1_HEADER, "1", "1.1", "R1C3", "TRUE",
            "12/12/2012" };
    protected final static Object[] STRING_ROW2_WITH_HEADER = new Object[] { ROW2_HEADER, "2", "2.2", "R2C3", "FALSE",
            "13/12/2012" };
    protected final static Object[] STRING_ROW3_WITH_HEADER = new Object[] { ROW3_HEADER, "3", "3.3", "R3C3", "TRUE",
            "14/12/2012" };

    protected final static Object[][] STRING_TABLE_AS_ARRAY_WITH_HEADER = new Object[][] { STRING_ROW1_WITH_HEADER,
            STRING_ROW2_WITH_HEADER, STRING_ROW3_WITH_HEADER };

    protected static final List<List<Object>> STRING_TABLE_AS_LIST = new ArrayList<List<Object>>();

    static {
        STRING_TABLE_AS_LIST.add(new ArrayList<Object>(STRING_ROW1.length));

        STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[0]);
        STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[1]);
        STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[2]);
        STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[3]);
        STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[4]);

        STRING_TABLE_AS_LIST.add(new ArrayList<Object>(STRING_ROW2.length));

        STRING_TABLE_AS_LIST.get(1).add(STRING_ROW2[0]);
        STRING_TABLE_AS_LIST.get(1).add(STRING_ROW2[1]);
        STRING_TABLE_AS_LIST.get(1).add(STRING_ROW2[2]);
        STRING_TABLE_AS_LIST.get(1).add(STRING_ROW2[3]);
        STRING_TABLE_AS_LIST.get(1).add(STRING_ROW2[4]);

        STRING_TABLE_AS_LIST.add(new ArrayList<Object>(STRING_ROW2.length));

        STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[0]);
        STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[1]);
        STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[2]);
        STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[3]);
        STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[4]);
    }

    protected static final List<List<Object>> STRING_TABLE_AS_LIST_WITH_HEADER = new ArrayList<List<Object>>();

    static {
        STRING_TABLE_AS_LIST_WITH_HEADER.add(new ArrayList<Object>(STRING_ROW1_WITH_HEADER.length));

        STRING_TABLE_AS_LIST_WITH_HEADER.get(0).add(STRING_ROW1_WITH_HEADER[0]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(0).add(STRING_ROW1_WITH_HEADER[1]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(0).add(STRING_ROW1_WITH_HEADER[2]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(0).add(STRING_ROW1_WITH_HEADER[3]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(0).add(STRING_ROW1_WITH_HEADER[4]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(0).add(STRING_ROW1_WITH_HEADER[5]);

        STRING_TABLE_AS_LIST_WITH_HEADER.add(new ArrayList<Object>(STRING_ROW2_WITH_HEADER.length));

        STRING_TABLE_AS_LIST_WITH_HEADER.get(1).add(STRING_ROW2_WITH_HEADER[0]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(1).add(STRING_ROW2_WITH_HEADER[1]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(1).add(STRING_ROW2_WITH_HEADER[2]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(1).add(STRING_ROW2_WITH_HEADER[3]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(1).add(STRING_ROW2_WITH_HEADER[4]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(1).add(STRING_ROW2_WITH_HEADER[5]);

        STRING_TABLE_AS_LIST_WITH_HEADER.add(new ArrayList<Object>(STRING_ROW3_WITH_HEADER.length));

        STRING_TABLE_AS_LIST_WITH_HEADER.get(2).add(STRING_ROW3_WITH_HEADER[0]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(2).add(STRING_ROW3_WITH_HEADER[1]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(2).add(STRING_ROW3_WITH_HEADER[2]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(2).add(STRING_ROW3_WITH_HEADER[3]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(2).add(STRING_ROW3_WITH_HEADER[4]);
        STRING_TABLE_AS_LIST_WITH_HEADER.get(2).add(STRING_ROW3_WITH_HEADER[5]);
    }

    protected final static FeatureDataRow ROW_WITH_NAME1 = new ArrayFeatureDatasetRow(
            new SimpleEntityPojo(ROW1_NAME), OBJECT_ROW1);
    protected final static FeatureDataRow ROW_WITH_NAME2 = new ArrayFeatureDatasetRow(
            new SimpleEntityPojo(ROW2_NAME), OBJECT_ROW2);
    protected final static FeatureDataRow ROW_WITH_NAME3 = new ArrayFeatureDatasetRow(
            new SimpleEntityPojo(ROW3_NAME), OBJECT_ROW3);

    protected final static FeatureDataRow ROW1 = new ArrayFeatureDatasetRow(OBJECT_ROW1);
    protected final static FeatureDataRow ROW2 = new ArrayFeatureDatasetRow(OBJECT_ROW2);
    protected final static FeatureDataRow ROW3 = new ArrayFeatureDatasetRow(OBJECT_ROW3);

    protected final static FeatureDataRow[] ROWS_AS_ARRAY = new FeatureDataRow[] { ROW1, ROW2, ROW3 };

    protected static final List<FeatureDataRow> ROWS_AS_LIST = new ArrayList<FeatureDataRow>();

    static {
        ROWS_AS_LIST.add(ROW1);
        ROWS_AS_LIST.add(ROW2);
        ROWS_AS_LIST.add(ROW3);
    }

    protected static final List<Feature> OBJECT_FEATURES = new ArrayList<Feature>();

    static {
        OBJECT_FEATURES.add(new FeaturePojo("col1", new MethodPojo("col1",
                new ScalePojo("col1", DataType.INTEGER, ScaleType.INTERVAL, 1, 3, OBJECT_COL1))));
        OBJECT_FEATURES.add(new FeaturePojo("col2",
                new MethodPojo("col2", new ScalePojo("col2", DataType.DOUBLE, ScaleType.RATIO, 1.1, 3.3))));
        OBJECT_FEATURES.add(new FeaturePojo("col3",
                new MethodPojo("col3", new ScalePojo("col3", DataType.STRING, ScaleType.NOMINAL, OBJECT_COL3))));
        OBJECT_FEATURES.add(new FeaturePojo("col4",
                new MethodPojo("col4", new ScalePojo("col4", DataType.BOOLEAN, ScaleType.NOMINAL, OBJECT_COL4))));
        OBJECT_FEATURES.add(new FeaturePojo("col5",
                new MethodPojo("col5", new ScalePojo("col5", DataType.DATE, ScaleType.NOMINAL, OBJECT_COL5))));
    }

    protected static final List<Feature> OBJECT_FEATURES_MIN = new ArrayList<Feature>();

    static {
        OBJECT_FEATURES_MIN.add(new FeaturePojo("col1", new MethodPojo("col1",
                new ScalePojo("col1", DataType.INTEGER, ScaleType.INTERVAL, 0, 3, OBJECT_COL1))));
        OBJECT_FEATURES_MIN.add(new FeaturePojo("col2",
                new MethodPojo("col2", new ScalePojo("col2", DataType.DOUBLE, ScaleType.RATIO, 0.0, 3.3))));
        OBJECT_FEATURES_MIN.add(new FeaturePojo("col3",
                new MethodPojo("col3", new ScalePojo("col3", DataType.STRING, ScaleType.NOMINAL, OBJECT_COL3))));
        OBJECT_FEATURES_MIN.add(new FeaturePojo("col4",
                new MethodPojo("col4", new ScalePojo("col4", DataType.BOOLEAN, ScaleType.NOMINAL, OBJECT_COL4))));
        OBJECT_FEATURES_MIN.add(new FeaturePojo("col5",
                new MethodPojo("col5", new ScalePojo("col5", DataType.DATE, ScaleType.NOMINAL, OBJECT_COL5))));
    }

    protected static final List<Feature> OBJECT_FEATURES_MIN_MAX = new ArrayList<Feature>();

    static {
        OBJECT_FEATURES_MIN_MAX.add(new FeaturePojo("col1", new MethodPojo("col1",
                new ScalePojo("col1", DataType.INTEGER, ScaleType.INTERVAL, 0, 4, OBJECT_COL1))));
        OBJECT_FEATURES_MIN_MAX.add(new FeaturePojo("col2",
                new MethodPojo("col2", new ScalePojo("col2", DataType.DOUBLE, ScaleType.RATIO, 0.0, 4.0))));
        OBJECT_FEATURES_MIN_MAX.add(new FeaturePojo("col3",
                new MethodPojo("col3", new ScalePojo("col3", DataType.STRING, ScaleType.NOMINAL, OBJECT_COL3))));
        OBJECT_FEATURES_MIN_MAX.add(new FeaturePojo("col4",
                new MethodPojo("col4", new ScalePojo("col4", DataType.BOOLEAN, ScaleType.NOMINAL, OBJECT_COL4))));
        OBJECT_FEATURES_MIN_MAX.add(new FeaturePojo("col5",
                new MethodPojo("col5", new ScalePojo("col5", DataType.DATE, ScaleType.NOMINAL, OBJECT_COL5))));
    }

    protected static final List<Feature> STRING_FEATURES = new ArrayList<Feature>();

    static {
        STRING_FEATURES.add(new FeaturePojo("col1",
                new MethodPojo("col1", new ScalePojo("col1", DataType.STRING, ScaleType.NOMINAL, STRING_COL1))));
        STRING_FEATURES.add(new FeaturePojo("col2",
                new MethodPojo("col2", new ScalePojo("col2", DataType.STRING, ScaleType.NOMINAL, STRING_COL2))));
        STRING_FEATURES.add(new FeaturePojo("col3",
                new MethodPojo("col3", new ScalePojo("col3", DataType.STRING, ScaleType.NOMINAL, STRING_COL3))));
        STRING_FEATURES.add(new FeaturePojo("col4",
                new MethodPojo("col4", new ScalePojo("col4", DataType.STRING, ScaleType.NOMINAL, STRING_COL4))));
        STRING_FEATURES.add(new FeaturePojo("col5",
                new MethodPojo("col5", new ScalePojo("col5", DataType.STRING, ScaleType.NOMINAL, STRING_COL5))));
    }

    /**
     * @param string
     * @return
     */
    private static Object createDate(String value) {
        try {
            return ConversionUtilities.convertToDate(value);
        } catch (ConversionException e) {
            return null;
        }
    }

    protected void checkCompleteDataset(String uid, String name, String description, List<Feature> features,
            FeatureData dataset, SimpleEntity[] rowHeaders, boolean useStrings) {
        assertEquals("uid not correct", uid, dataset.getUniqueIdentifier());
        assertEquals("name not correct", name, dataset.getName());

        // TODO
        // assertEquals("Data type not correct", type,dataset.getType()) ;

        assertEquals("Number of rows incorrect", OBJECT_TABLE_AS_ARRAY_WITH_HEADER.length, dataset.getRowCount());

        FeatureDataRow[] rows = dataset.getRowsAsArray();

        for (int i = 0; i < rows.length; ++i) {
            assertSimpleEntityEquals("Row header " + i + " not correct", rowHeaders[i], rows[i].getHeader());
            assertEquals("Row size " + i + " not correct",
                    useStrings ? STRING_TABLE_AS_LIST.get(i).size() : OBJECT_TABLE_AS_LIST.get(i).size(),
                    rows[i].getColumnCount());
            assertEquals("Row values (list) " + i + " not correct",
                    useStrings ? STRING_TABLE_AS_LIST.get(i) : OBJECT_TABLE_AS_LIST.get(i), rows[i].getValues());
            assertArrayEquals("Row values (array) " + i + " not correct",
                    useStrings ? STRING_TABLE_AS_ARRAY[i] : OBJECT_TABLE_AS_ARRAY[i], rows[i].getValuesAsArray());
        }

        Iterator<FeatureDataRow> iterator = dataset.getRows().iterator();

        int i = 0;

        FeatureDataRow row;

        while (iterator.hasNext()) {
            row = iterator.next();
            assertSimpleEntityEquals("Row header " + i + " not correct", rowHeaders[i], row.getHeader());
            assertEquals("Row size " + i + " not correct",
                    useStrings ? STRING_TABLE_AS_LIST.get(i).size() : OBJECT_TABLE_AS_LIST.get(i).size(),
                    row.getColumnCount());
            assertEquals("Row values (list) " + i + " not correct",
                    useStrings ? STRING_TABLE_AS_LIST.get(i) : OBJECT_TABLE_AS_LIST.get(i), row.getValues());
            assertArrayEquals("Row values (array) " + i + " not correct",
                    useStrings ? STRING_TABLE_AS_ARRAY[i] : OBJECT_TABLE_AS_ARRAY[i], row.getValuesAsArray());
            ++i;
        }

        for (i = 0; i < dataset.getRowCount(); ++i) {
            assertSimpleEntityEquals("Row header " + i + " not correct", rowHeaders[i], dataset.getRow(i).getHeader());
            assertEquals("Row size " + i + " not correct",
                    useStrings ? STRING_TABLE_AS_LIST.get(i).size() : OBJECT_TABLE_AS_LIST.get(i).size(),
                    dataset.getRow(i).getColumnCount());
            assertArrayEquals("Row values (list) " + i + " not correct",
                    useStrings ? STRING_TABLE_AS_ARRAY[i] : OBJECT_TABLE_AS_ARRAY[i],
                    dataset.getRow(i).getValuesAsArray());
            assertEquals("Row values (array) " + i + " not correct",
                    useStrings ? STRING_TABLE_AS_LIST.get(i) : OBJECT_TABLE_AS_LIST.get(i),
                    dataset.getRow(i).getValues());
        }

        assertEquals("Values not correct", useStrings ? STRING_TABLE_AS_LIST : OBJECT_TABLE_AS_LIST,
                dataset.getValues());
        assertArrayEquals("Values as array not correct", useStrings ? STRING_TABLE_AS_ARRAY : OBJECT_TABLE_AS_ARRAY,
                dataset.getValuesAsArray());

        List<Feature> datasetFeatures = dataset.getFeatures();

        assertEquals("Number of featues not correct", features.size(), datasetFeatures.size());

        Iterator<Feature> iterator1 = features.iterator();
        Iterator<Feature> iterator2 = datasetFeatures.iterator();

        int j = 0;

        while (iterator1.hasNext() && iterator2.hasNext()) {
            assertFeatureEquals("Column header " + j, iterator1.next(), iterator2.next());

            ++j;
        }
    }

    /**
     * @param next
     * @param next2
     */
    private void assertFeatureEquals(String label, Feature feaure1, Feature feature2) {
        if (feaure1 != null) {
            assertNotNull(label + " feature is null", feature2);
            assertEquals(label + " feature uid not correct", feaure1.getUniqueIdentifier(),
                    feature2.getUniqueIdentifier());
            assertEquals(label + " feature name not correct", feaure1.getName(), feature2.getName());
            assertEquals(label + " feature description not correct", feaure1.getDescription(),
                    feature2.getDescription());

            assertMethodEquals(label + " feature method not correct", feaure1.getMethod(), feature2.getMethod());
        } else {
            assertNull("Feature is not null", feature2);
        }
    }

    private void assertMethodEquals(String label, Method method1, Method method2) {
        if (method1 != null) {
            assertNotNull(label + " method is null", method1);
            assertEquals(label + " method uid not correct", method1.getUniqueIdentifier(),
                    method2.getUniqueIdentifier());
            assertEquals(label + " method name not correct", method1.getName(), method2.getName());
            assertEquals(label + " method description not correct", method1.getDescription(), method2.getDescription());

            assertScaleEquals(label + " feature method not correct", method1.getScale(), method2.getScale());
        } else {
            assertNull("method is not null", method2);
        }
    }

    private void assertScaleEquals(String label, Scale scale1, Scale scale2) {
        if (scale1 != null) {
            assertNotNull(label + " scale is null", scale1);
            assertEquals(label + " scale uid not correct", scale1.getUniqueIdentifier(), scale2.getUniqueIdentifier());
            assertEquals(label + " scale name not correct", scale1.getName(), scale2.getName());
            assertEquals(label + " scale description not correct", scale1.getDescription(), scale2.getDescription());

            assertEquals(label + " scale data type not correct", scale1.getDataType(), scale2.getDataType());
            assertEquals(label + " scale scale type not correct", scale1.getScaleType(), scale2.getScaleType());

            assertEquals(label + " scale minimum is not correct", scale1.getMinimumValue(), scale2.getMinimumValue());
            assertEquals(label + " scale maximum is not correct", scale1.getMaximumValue(), scale2.getMaximumValue());

            assertEquals(label + " scale values are not correct", scale1.getValues(), scale2.getValues());
        } else {
            assertNull("scale is not null", scale2);
        }
    }

    protected void assertSimpleEntityEquals(String label, SimpleEntity simpleEntity1, SimpleEntity simpleEntity2) {
        if (simpleEntity1 != null) {
            assertNotNull(label + " entity is null", simpleEntity2);
            assertEquals(label + " entity uid not correct", simpleEntity1.getUniqueIdentifier(),
                    simpleEntity2.getUniqueIdentifier());
            assertEquals(label + " entity name not correct", simpleEntity1.getName(), simpleEntity2.getName());
        } else {
            assertNull("scale is not null", simpleEntity2);
        }
    }
}
