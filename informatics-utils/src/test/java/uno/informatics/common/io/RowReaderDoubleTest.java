/*******************************************************************************
 * Copyright 2015 Guy Davenport
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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Guy Davenport
 *
 */
public abstract class RowReaderDoubleTest extends RowReaderTest {
    private static final double PRECISION = 0.000001;

    protected final static double[] ROW1 = new double[] {
        1.1, 2.1, 3.1
    };
    protected final static double[] ROW2 = new double[] {
        1.2, 2.2, 3.2
    };
    protected final static double[] ROW3 = new double[] {
        1.3, 2.3, 3.3
    };

    protected final static double[] ROW2_WITH_GAPS = new double[] {
        1.2, 0.0, 3.2
    };
    protected final static double[] ROW3_WITH_GAPS = new double[] {
        1.3, 2.3, 0.0
    };

    protected final static Object[] OBJ_ROW1 = new Object[] {
        1.1, 2.1, 3.1
    };
    protected final static Object[] OBJ_ROW2 = new Object[] {
        1.2, 2.2, 3.2
    };
    protected final static Object[] OBJ_ROW3 = new Object[] {
        1.3, 2.3, 3.3
    };

    protected final static Object[] OBJ_ROW2_WITH_GAPS = new Object[] {
        1.2, null, 3.2
    };
    protected final static Object[] OBJ_ROW3_WITH_GAPS = new Object[] {
        1.3, 2.3, null
    };

    protected static final List<List<Object>> TABLE_AS_LIST = new ArrayList<List<Object>>();

    static {
        TABLE_AS_LIST.add(new ArrayList<Object>(ROW1.length));

        TABLE_AS_LIST.get(0).add(ROW1[0]);
        TABLE_AS_LIST.get(0).add(ROW1[1]);
        TABLE_AS_LIST.get(0).add(ROW1[2]);

        TABLE_AS_LIST.add(new ArrayList<Object>(ROW2.length));

        TABLE_AS_LIST.get(1).add(ROW2[0]);
        TABLE_AS_LIST.get(1).add(ROW2[1]);
        TABLE_AS_LIST.get(1).add(ROW2[2]);

        TABLE_AS_LIST.add(new ArrayList<Object>(ROW3.length));

        TABLE_AS_LIST.get(2).add(ROW3[0]);
        TABLE_AS_LIST.get(2).add(ROW3[1]);
        TABLE_AS_LIST.get(2).add(ROW3[2]);
    }

    protected static final Object[][] TABLE_AS_ARRAY = new Object[][] {
        OBJ_ROW1, OBJ_ROW2, OBJ_ROW3
    };

    protected static final List<List<Object>> TABLE_AS_LIST_WITH_GAPS = new ArrayList<List<Object>>();

    static {
        TABLE_AS_LIST_WITH_GAPS.add(new ArrayList<Object>(ROW1.length));

        TABLE_AS_LIST_WITH_GAPS.get(0).add(ROW1[0]);
        TABLE_AS_LIST_WITH_GAPS.get(0).add(ROW1[1]);
        TABLE_AS_LIST_WITH_GAPS.get(0).add(ROW1[2]);

        TABLE_AS_LIST_WITH_GAPS.add(new ArrayList<Object>(ROW2.length));

        TABLE_AS_LIST_WITH_GAPS.get(1).add(ROW2[0]);
        TABLE_AS_LIST_WITH_GAPS.get(1).add(null);
        TABLE_AS_LIST_WITH_GAPS.get(1).add(ROW2[2]);

        TABLE_AS_LIST_WITH_GAPS.add(new ArrayList<Object>(ROW3.length));

        TABLE_AS_LIST_WITH_GAPS.get(2).add(ROW3[0]);
        TABLE_AS_LIST_WITH_GAPS.get(2).add(ROW3[1]);
        TABLE_AS_LIST_WITH_GAPS.get(2).add(null);
    }

    protected static final Object[][] TABLE_AS_ARRAY_WITH_GAPS = new Object[][] {
        OBJ_ROW1, OBJ_ROW2_WITH_GAPS, OBJ_ROW3_WITH_GAPS
    };

    protected static final List<List<Double>> TABLE_AS_LIST2 = new ArrayList<List<Double>>();

    static {
        TABLE_AS_LIST2.add(new ArrayList<Double>(ROW1.length));

        TABLE_AS_LIST2.get(0).add(ROW1[0]);
        TABLE_AS_LIST2.get(0).add(ROW1[1]);
        TABLE_AS_LIST2.get(0).add(ROW1[2]);

        TABLE_AS_LIST2.add(new ArrayList<Double>(ROW2.length));

        TABLE_AS_LIST2.get(1).add(ROW2[0]);
        TABLE_AS_LIST2.get(1).add(ROW2[1]);
        TABLE_AS_LIST2.get(1).add(ROW2[2]);

        TABLE_AS_LIST2.add(new ArrayList<Double>(ROW3.length));

        TABLE_AS_LIST2.get(2).add(ROW3[0]);
        TABLE_AS_LIST2.get(2).add(ROW3[1]);
        TABLE_AS_LIST2.get(2).add(ROW3[2]);
    }

    protected static final double[][] TABLE_AS_ARRAY2 = new double[][] {
        ROW1, ROW2, ROW3
    };

    protected static final List<List<Double>> TABLE_AS_LIST2_WITH_GAPS = new ArrayList<List<Double>>();

    static {
        TABLE_AS_LIST2_WITH_GAPS.add(new ArrayList<Double>(ROW1.length));

        TABLE_AS_LIST2_WITH_GAPS.get(0).add(ROW1[0]);
        TABLE_AS_LIST2_WITH_GAPS.get(0).add(ROW1[1]);
        TABLE_AS_LIST2_WITH_GAPS.get(0).add(ROW1[2]);

        TABLE_AS_LIST2_WITH_GAPS.add(new ArrayList<Double>(ROW2.length));

        TABLE_AS_LIST2_WITH_GAPS.get(1).add(ROW2[0]);
        TABLE_AS_LIST2_WITH_GAPS.get(1).add(null);
        TABLE_AS_LIST2_WITH_GAPS.get(1).add(ROW2[2]);

        TABLE_AS_LIST2_WITH_GAPS.add(new ArrayList<Double>(ROW3.length));

        TABLE_AS_LIST2_WITH_GAPS.get(2).add(ROW3[0]);
        TABLE_AS_LIST2_WITH_GAPS.get(2).add(ROW3[1]);
        TABLE_AS_LIST2_WITH_GAPS.get(2).add(null);
    }

    protected static final double[][] TABLE_AS_ARRAY2_WITH_GAPS = new double[][] {
        ROW1, ROW2_WITH_GAPS, ROW3_WITH_GAPS
    };

    protected abstract List<List<Object>> getExpectedList();

    protected abstract Object[][] getExpectedArray();

    protected abstract List<List<Double>> getExpectedAsList();

    protected abstract double[][] getExpectedAsArray();

    @Test
    public void testReadCellsAsDouble() {
        try {
            RowReader reader = createReader();

            List<List<Double>> expected = getExpectedAsList();

            assertTrue(reader.ready());

            int i = 0;

            while (i < expected.size() && reader.nextRow()) {
                assertEquals("row " + i + " not equal", expected.get(i), reader.getRowCellsAsDouble());
                ++i;
            }

            assertFalse("Still rows to read!", reader.nextRow());

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadCellsAsArrayAsDouble() {
        try {
            RowReader reader = createReader();

            assertTrue(reader.ready());

            double[][] expected = getExpectedAsArray();

            int i = 0;

            while (i < expected.length && reader.nextRow()) {
                assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsDoubleArray(),
                    PRECISION);
                ++i;
            }

            assertFalse(reader.nextRow());

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }

}
