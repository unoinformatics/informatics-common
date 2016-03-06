/*******************************************************************************
 * Copyright 2016 Guy Davenport Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *******************************************************************************/

package uno.informatics.data;

/**
 * @author Guy Davenport
 *
 */
public class DataTypeConstants {

    public static final int UNKNOWN_ID = 0;
    public static final int BOOLEAN_ID = 1 << 0;
    public static final int SHORT_ID = 1 << 1;
    public static final int INT_ID = 1 << 2;
    public static final int LONG_ID = 1 << 3;
    public static final int FLOAT_ID = 1 << 4;
    public static final int DOUBLE_ID = 1 << 5;
    public static final int BIG_INTEGER_ID = 1 << 6;
    public static final int BIG_DECIMAL_ID = 1 << 7;
    public static final int DATE_ID = 1 << 8;
    public static final int STRING_ID = 1 << 9;
    public static final int DEFAULT_TYPE_IDS = BOOLEAN_ID | INT_ID | DOUBLE_ID | STRING_ID | DATE_ID;
    public static final int NUMBER_IDS = SHORT_ID | INT_ID | LONG_ID | FLOAT_ID | DOUBLE_ID | BIG_INTEGER_ID
            | BIG_DECIMAL_ID;
    public static final int INTEGER_IDS = SHORT_ID | INT_ID | LONG_ID | BIG_INTEGER_ID;
    public static final int REAL_IDS = FLOAT_ID | DOUBLE_ID | BIG_DECIMAL_ID;
    public static final int BIG_NUMBER_IDS = BIG_INTEGER_ID | BIG_DECIMAL_ID;
    public static final int SIMPLE_ENTITY_ID = 1 << 10;

    /**
     * Gets the conversion type for data type
     * 
     * @param dataType
     *            data type
     * @return conversion type for data type
     */
    public static final int getConversionType(DataType dataType) {
        switch (dataType) {
            case BIG_DECIMAL:
                return DataTypeConstants.BIG_DECIMAL_ID;
            case BIG_INTEGER:
                return DataTypeConstants.BIG_INTEGER_ID;
            case BOOLEAN:
                return DataTypeConstants.BOOLEAN_ID;
            case DATE:
                return DataTypeConstants.DATE_ID;
            case DOUBLE:
                return DataTypeConstants.DOUBLE_ID;
            case FLOAT:
                return DataTypeConstants.FLOAT_ID;
            case INTEGER:
                return DataTypeConstants.INT_ID;
            case LONG:
                return DataTypeConstants.LONG_ID;
            case SHORT:
                return DataTypeConstants.SHORT_ID;
            case STRING:
            default:
                return DataTypeConstants.STRING_ID;
        }
    }

    /**
     * Gets the data type for the conversion type
     * 
     * @param conversionType
     *            conversion type
     * @return data type for conversion type
     */
    public static final DataType getDataType(int conversionType) {
        switch (conversionType) {
            case BIG_DECIMAL_ID:
                return DataType.BIG_DECIMAL;
            case BIG_INTEGER_ID:
                return DataType.BIG_INTEGER;
            case BOOLEAN_ID:
                return DataType.BOOLEAN;
            case DATE_ID:
                return DataType.DATE;
            case DOUBLE_ID:
                return DataType.DOUBLE;
            case FLOAT_ID:
                return DataType.FLOAT;
            case INT_ID:
                return DataType.INTEGER;
            case LONG_ID:
                return DataType.LONG;
            case SHORT_ID:
                return DataType.SHORT;
            case STRING_ID:
            default:
                return DataType.STRING;
        }
    }
}