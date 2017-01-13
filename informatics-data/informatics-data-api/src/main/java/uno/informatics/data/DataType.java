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

package uno.informatics.data;

public enum DataType {
    BOOLEAN(DataTypeConstants.BOOLEAN_ID, "Boolean", "B"), 
    SHORT(DataTypeConstants.SHORT_ID, "Short", "T"), 
    INTEGER(DataTypeConstants.INTEGER_IDS, "Integer", "I"), 
    LONG(DataTypeConstants.LONG_ID, "Long", "L"), 
    FLOAT(DataTypeConstants.FLOAT_ID, "Float", "F"), 
    DOUBLE(DataTypeConstants.DOUBLE_ID, "Double", "D"), 
    BIG_INTEGER(DataTypeConstants.BIG_INTEGER_ID, "Big Integer", "R"), 
    BIG_DECIMAL(DataTypeConstants.BIG_DECIMAL_ID, "Big Decimal", "M"), 
    DATE(DataTypeConstants.DATE_ID, "Date", "A"), 
    STRING(DataTypeConstants.STRING_ID, "String", "S"), 
    SIMPLE_ENTRY(DataTypeConstants.SIMPLE_ENTITY_ID, "SimpleEntry", "E"), 
    UNKNOWN(DataTypeConstants.UNKNOWN_ID, "None", "X");

    private static final DataType[] allTypes = new DataType[] { BOOLEAN, SHORT, INTEGER, LONG, FLOAT, DOUBLE,
            BIG_INTEGER, BIG_DECIMAL, DATE, STRING };

    private String name;
    private String abbreviation;
    private int id;

    private DataType(int id, String name, String abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public final String getAbbreviation() {
        return abbreviation;
    }

    public synchronized final int getId() {
        return id;
    }

    public static final DataType[] getAllTypes() {
        return allTypes;
    }

    public static final DataType getTypeByName(String name) {
        int i = 0;
        DataType dataType = null;

        while (i < allTypes.length) {
            if (allTypes[i].getName().equals(name))
                dataType = allTypes[i];

            ++i;
        }

        return dataType;
    }

    public static final DataType getTypeByAbbreviation(String abbreviation) {
        int i = 0;
        DataType dataType = null;

        while (i < allTypes.length) {
            if (allTypes[i].getAbbreviation().equals(abbreviation))
                dataType = allTypes[i];

            ++i;
        }

        return dataType;
    }

    /**
     * Gets the data type for the given type id
     * 
     * @param id
     *            the type id
     * @return data type for the give type id
     */
    public static final DataType getDataTypeById(int id) {
        int i = 0;
        DataType dataType = null;

        while (i < allTypes.length) {
            if (allTypes[i].getId() == id)
                dataType = allTypes[i];

            ++i;
        }

        return dataType;
    }
}
