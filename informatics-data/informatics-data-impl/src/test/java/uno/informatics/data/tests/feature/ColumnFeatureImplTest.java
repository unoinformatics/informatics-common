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

package uno.informatics.data.tests.feature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uno.informatics.data.DataType;
import uno.informatics.data.DataTypeConstants;
import uno.informatics.data.ScaleType;
import uno.informatics.data.feature.ColumnFeature;
import uno.informatics.data.feature.ColumnFeaturePojo;

/**
 * @author Guy Davenport
 *
 */
public class ColumnFeatureImplTest {
    protected final static String UID = "uid";
    protected final static String NAME = "name";
    protected final static String DESCRIPTION = "description";

    /**
     * Test method for
     * {@link uno.informatics.data.feature.ColumnFeaturePojo#ColumnFeatureImpl(java.lang.String, int)}
     * .
     */
    @Test
    public void testColumnFeatureImplStringInt() {
        ColumnFeature feature = new ColumnFeaturePojo(NAME, DataTypeConstants.STRING_ID);

        assertEquals(null, feature.getUniqueIdentifier());
        assertEquals(NAME, feature.getName());
        assertEquals(null, feature.getDescription());
        assertEquals(DataTypeConstants.STRING_ID, feature.getPossibleDataTypes());

        assertEquals(DataType.STRING, feature.getDataType());
        assertEquals(ScaleType.NOMINAL, feature.getScaleType());

        feature = new ColumnFeaturePojo(NAME, DataTypeConstants.DATE_ID | DataTypeConstants.DOUBLE_ID);

        assertEquals(null, feature.getUniqueIdentifier());
        assertEquals(NAME, feature.getName());
        assertEquals(null, feature.getDescription());
        assertEquals(DataTypeConstants.DATE_ID | DataTypeConstants.DOUBLE_ID, feature.getPossibleDataTypes());

        assertEquals(DataType.DOUBLE, feature.getDataType());
        assertEquals(ScaleType.NOMINAL, feature.getScaleType());
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.ColumnFeaturePojo#ColumnFeatureImpl(java.lang.String, java.lang.String, int)}
     * .
     */
    @Test
    public void testColumnFeatureImplStringStringInt() {
        /*
         * ColumnFeature feature = new ColumnFeaturePojo(UID, NAME,
         * DataTypeConstants.STRING) ;
         * 
         * assertEquals(UID, feature.getUniqueIdentifier()) ; assertEquals(NAME,
         * feature.getName()) ; assertEquals(null, feature.getDescription()) ;
         * assertEquals(DataTypeConstants.STRING,
         * feature.getPossibleDataTypes()) ;
         * 
         * assertEquals(DataType.STRING, feature.getDataType()) ;
         * assertEquals(ScaleType.NOMINAL, feature.getScaleType()) ;
         * 
         * feature = new ColumnFeaturePojo(UID, NAME, DataTypeConstants.STRING |
         * DataTypeConstants.DOUBLE) ;
         * 
         * assertEquals(UID, feature.getUniqueIdentifier()) ; assertEquals(NAME,
         * feature.getName()) ; assertEquals(null, feature.getDescription()) ;
         * assertEquals(DataTypeConstants.STRING | DataTypeConstants.DOUBLE,
         * feature.getPossibleDataTypes()) ;
         * 
         * assertEquals(DataType.STRING, feature.getDataType()) ;
         * assertEquals(ScaleType.NOMINAL, feature.getScaleType()) ;
         */
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.ColumnFeaturePojo#ColumnFeatureImpl(java.lang.String, java.lang.String, java.lang.String, int)}
     * .
     */
    @Test
    public void testColumnFeatureImplStringStringStringInt() {
        /*
         * ColumnFeature feature = new ColumnFeaturePojo(UID, NAME, DESCRIPTION,
         * DataTypeConstants.STRING) ;
         * 
         * assertEquals(UID, feature.getUniqueIdentifier()) ; assertEquals(NAME,
         * feature.getName()) ; assertEquals(DESCRIPTION,
         * feature.getDescription()) ; assertEquals(DataTypeConstants.STRING,
         * feature.getPossibleDataTypes()) ;
         * 
         * assertEquals(DataType.STRING, feature.getDataType()) ;
         * assertEquals(ScaleType.NOMINAL, feature.getScaleType()) ;
         * 
         * feature = new ColumnFeaturePojo(UID, NAME, DESCRIPTION,
         * DataTypeConstants.DATE | DataTypeConstants.DOUBLE) ;
         * 
         * assertEquals(UID, feature.getUniqueIdentifier()) ; assertEquals(NAME,
         * feature.getName()) ; assertEquals(DESCRIPTION,
         * feature.getDescription()) ; assertEquals(DataTypeConstants.DATE |
         * DataTypeConstants.DOUBLE, feature.getPossibleDataTypes()) ;
         * 
         * assertEquals(DataType.DOUBLE, feature.getDataType()) ;
         * assertEquals(ScaleType.NOMINAL, feature.getScaleType()) ;
         */
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.ColumnFeaturePojo#ColumnFeatureImpl(java.lang.String, uno.informatics.data.dataset.DataType, uno.informatics.data.dataset.ScaleType, int)}
     * .
     */
    @Test
    public void testColumnFeatureImplStringDataTypeScaleTypeInt() {
        ColumnFeature feature = new ColumnFeaturePojo(NAME, DataType.STRING, ScaleType.NOMINAL,
                DataTypeConstants.STRING_ID);

        assertEquals(null, feature.getUniqueIdentifier());
        assertEquals(NAME, feature.getName());
        assertEquals(null, feature.getDescription());
        assertEquals(DataTypeConstants.STRING_ID, feature.getPossibleDataTypes());

        assertEquals(DataType.STRING, feature.getDataType());
        assertEquals(ScaleType.NOMINAL, feature.getScaleType());

        feature = new ColumnFeaturePojo(NAME, DataTypeConstants.DATE_ID | DataTypeConstants.DOUBLE_ID);

        assertEquals(null, feature.getUniqueIdentifier());
        assertEquals(NAME, feature.getName());
        assertEquals(null, feature.getDescription());
        assertEquals(DataTypeConstants.DATE_ID | DataTypeConstants.DOUBLE_ID, feature.getPossibleDataTypes());

        assertEquals(DataType.DOUBLE, feature.getDataType());
        assertEquals(ScaleType.NOMINAL, feature.getScaleType());
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.ColumnFeaturePojo#ColumnFeatureImpl(java.lang.String, java.lang.String, uno.informatics.data.dataset.DataType, uno.informatics.data.dataset.ScaleType, int)}
     * .
     */
    @Test
    public void testColumnFeatureImplStringStringDataTypeScaleTypeInt() {
        /*
         * ColumnFeature feature = new ColumnFeaturePojo(UID, NAME,
         * DataType.STRING, ScaleType.NOMINAL, DataTypeConstants.STRING) ;
         * 
         * assertEquals(UID, feature.getUniqueIdentifier()) ; assertEquals(NAME,
         * feature.getName()) ; assertEquals(DataTypeConstants.STRING,
         * feature.getPossibleDataTypes()) ;
         * 
         * assertEquals(DataType.STRING, feature.getDataType()) ;
         * assertEquals(ScaleType.NOMINAL, feature.getScaleType()) ;
         * 
         * feature = new ColumnFeaturePojo(UID, NAME, DataTypeConstants.DATE |
         * DataTypeConstants.DOUBLE) ;
         * 
         * assertEquals(UID, feature.getUniqueIdentifier()) ; assertEquals(NAME,
         * feature.getName()) ; assertEquals(null, feature.getDescription()) ;
         * assertEquals(DataTypeConstants.DATE | DataTypeConstants.DOUBLE,
         * feature.getPossibleDataTypes()) ;
         * 
         * assertEquals(DataType.DOUBLE, feature.getDataType()) ;
         * assertEquals(ScaleType.NOMINAL, feature.getScaleType()) ;
         */
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.ColumnFeaturePojo#ColumnFeatureImpl(java.lang.String, java.lang.String, java.lang.String, uno.informatics.data.dataset.DataType, uno.informatics.data.dataset.ScaleType, int)}
     * .
     */
    @Test
    public void testColumnFeatureImplStringStringStringDataTypeScaleTypeInt() {
        /*
         * ColumnFeature feature = new ColumnFeaturePojo(UID, NAME, DESCRIPTION,
         * DataType.STRING, ScaleType.NOMINAL, DataTypeConstants.STRING) ;
         * 
         * assertEquals(UID, feature.getUniqueIdentifier()) ; assertEquals(NAME,
         * feature.getName()) ; assertEquals(DESCRIPTION,
         * feature.getDescription()) ; assertEquals(DataTypeConstants.STRING,
         * feature.getPossibleDataTypes()) ;
         * 
         * assertEquals(DataType.STRING, feature.getDataType()) ;
         * assertEquals(ScaleType.NOMINAL, feature.getScaleType()) ;
         * 
         * feature = new ColumnFeaturePojo(UID, NAME, DESCRIPTION,
         * DataTypeConstants.DATE | DataTypeConstants.DOUBLE) ;
         * 
         * assertEquals(UID, feature.getUniqueIdentifier()) ; assertEquals(NAME,
         * feature.getName()) ; assertEquals(DESCRIPTION,
         * feature.getDescription()) ; assertEquals(DataTypeConstants.DATE |
         * DataTypeConstants.DOUBLE, feature.getPossibleDataTypes()) ;
         * 
         * assertEquals(DataType.DOUBLE, feature.getDataType()) ;
         * assertEquals(ScaleType.NOMINAL, feature.getScaleType()) ;
         */
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.ColumnFeaturePojo#getPossibleDataTypes()}
     * .
     */
    @Test
    public void testGetPossibleDataTypes() {
        ColumnFeature feature = new ColumnFeaturePojo(NAME, DataTypeConstants.STRING_ID);

        assertEquals(NAME, feature.getName());
        assertEquals(DataTypeConstants.STRING_ID, feature.getPossibleDataTypes());
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.ColumnFeaturePojo#setPossibleDataTypes(int)}
     * .
     */
    @Test
    public void testSetPossibleDataTypes() {
        ColumnFeature feature = new ColumnFeaturePojo(NAME, DataTypeConstants.STRING_ID);

        assertEquals(DataTypeConstants.STRING_ID, feature.getPossibleDataTypes());

        feature.setPossibleDataTypes(DataTypeConstants.BOOLEAN_ID);

        assertEquals(DataTypeConstants.BOOLEAN_ID, feature.getPossibleDataTypes());
    }
}
