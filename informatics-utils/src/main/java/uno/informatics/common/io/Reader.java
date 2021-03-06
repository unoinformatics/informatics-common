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

package uno.informatics.common.io;

/**
 * Base functionality for all readers
 * 
 * @author Guy Davenport
 *
 */
public interface Reader extends AutoCloseable {
    /**
     * Determines if the data source can be read
     * 
     * @return <code>true</code> if the data source can be read, false otherwise
     */
    public boolean ready();

    /**
     * Closes the data source, freeing up an resources.
     */
    @Override
    public void close();
}