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

import java.util.List;

public interface Scale extends Entity {
    
    /**
     * Gets the maximum value in the scale.
     * 
     * @return the maximum value in the scale, or <code>null</code> if the
     *         maximum value is not applicable, e.g. for nominal scales
     */
    public Number getMaximumValue();
    
    public void setMaximumValue(Number maximum);

    /**
     * Gets the minimum value in the scale.
     * 
     * @return the minimum value in the scale, or <code>null</code> if the
     *         maximum value is not applicable, e.g. for nominal scales
     */
    public Number getMinimumValue();
    
    public void setMinimumValue(Number minimum);

    
    /**
     * Gets all possible values for this scale. Values can not be repeated. The
     * order of the values is important for ordinal scales.
     * 
     * @return all possible values for this scale, or <code>null</code> the
     *         number of possible values is large or infinite, e.g. for ratio
     *         and interval scales
     */
    public List<Object> getValues();
    
    public void setValues(List<? extends Object> values);
    
    /**
     * Get the index of a value in the list of possible values.
     * Should be faster than calling <code>indexOf</code> on the list
     * of values as returned by {@link #getValues()}. Especially useful
     * for ordinal scales where the order of values is important.
     * 
     * @param value one of the values in the list obtained with {@link #getValues()}
     * @return index of the given value, or -1 if an unknown value is given
     *         or if the number of possible values is large or infinite,
     *         e.g. for ratio and interval scales
     */
    public int indexOf(Object value);

    /**
     * Gets the data type for this scale.
     * 
     * @return the data type for this scale
     */
    public DataType getDataType();
    
    public void setDataType(DataType type);

    /**
     * Gets the scale type for this scale.
     * 
     * @return the scale type for this scale
     */
    public ScaleType getScaleType();
    
    public void setScaleType(ScaleType type);
    
}
