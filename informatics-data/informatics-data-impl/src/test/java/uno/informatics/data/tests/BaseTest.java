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

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    private static final String TEST_PROPERTIES = "/test.properties";

    private Properties properties;

    private Class<? extends Object> resourceClass;

    public BaseTest() {
        this.resourceClass = getClass();
    }

    public BaseTest(Class<? extends Object> resourceClass) {
        super();
        this.resourceClass = resourceClass;
    }

    public final String getTestString(Object testObject, String key) throws Exception {
        String prefix = null;

        if (testObject != null)
            prefix = testObject.getClass().getSimpleName() + ".";

        if (getTestProperties().containsKey(prefix + key))
            return getTestProperties().getProperty(prefix + key);
        else if (getTestProperties().containsKey(key))
            return getTestProperties().getProperty(key);
        else
            throw new IllegalArgumentException("Unknown key :" + key);
    }

    @SuppressWarnings("rawtypes")
    public final String getTestString(Class testClass, String key) throws Exception {
        String prefix = null;

        if (testClass != null)
            prefix = testClass.getSimpleName() + ".";

        if (getTestProperties().containsKey(prefix + key))
            return getTestProperties().getProperty(prefix + key);
        else if (getTestProperties().containsKey(key))
            return getTestProperties().getProperty(key);
        else
            throw new IllegalArgumentException("Unknown key :" + key);
    }

    public final String[] getTestStringArray(Object testObject, String key) throws Exception {
        return getTestString(testObject, key).split(",");
    }

    public final int getTestInt(Object testObject, String key) throws Exception {
        return Integer.valueOf(getTestString(testObject, key));
    }

    public final int[] getTestIntArray(Object testObject, String key) throws Exception {
        String[] values = getTestStringArray(testObject, key);

        int[] array = new int[values.length];

        for (int i = 0; i < values.length; ++i)
            array[i] = Integer.valueOf(values[i]);

        return array;
    }

    public final Properties loadTestProperties(Class<? extends Object> resourceClass) throws IOException {
        Properties properties = new Properties();

        properties.load(resourceClass.getResourceAsStream(TEST_PROPERTIES));

        return properties;
    }

    private final Properties getTestProperties() throws IOException {
        if (properties == null) {
            properties = loadTestProperties(resourceClass);
        }

        return properties;
    }
}
