
/*******************************************************************************
 * Copyright 2016 Guy Davenport, Herman De Beukelaer
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
 */

package uno.informatics.data.pojo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uno.informatics.data.Data;
import uno.informatics.data.Dataset;
import uno.informatics.data.SimpleEntity;

public class DataPojo extends SimpleEntityPojo implements Data {

    public static final String DATASET_PROPERTY = Data.class.getName() + ".dataset";
    
    private static final String HEADER_ID = "entry%d";
    private static final String HEADER_NAME = "Entry %d";

    private DatasetPojo dataset;

    // headers
    private SimpleEntity[] headers;
    // item IDs (0..n-1)
    private Set<Integer> ids;

    public DataPojo(String name, SimpleEntity[] headers) {
        super(name);

        setHeaders(headers);
    }

    public DataPojo(String uniqueIdentifier, String name, SimpleEntity[] headers) {
        super(uniqueIdentifier, name);
        
        setHeaders(headers);
    }

    public DataPojo(Data data) {
        super(data);

        if (data != null)
            setDataset(data.getDataset());
        else
            throw new IllegalArgumentException("Data is not optional!");

        SimpleEntity[] headers = new SimpleEntity[data.getSize()];

        for (int i = 0; i < headers.length; ++i)
            headers[i] = data.getHeader(i);

        setHeaders(headers);
    }

    @Override
    public Dataset getDataset() {
        return dataset;
    }

    public final void setDataset(Dataset dataset) {

        DatasetPojo oldValue = this.dataset;

        if (dataset != null)
            this.dataset = new DatasetPojo(dataset);
        else
            this.dataset = null;

        getPropertyChangeSupport().firePropertyChange(DATASET_PROPERTY, oldValue, this.dataset);
    }

    @Override
    public SimpleEntity getHeader(int id) {
        return headers == null ? null : headers[id];
    }

    @Override
    public int getSize() {
        return ids.size();
    }
    
    @Override
    public Set<Integer> getIDs() {
        return ids;
    }

    private void setHeaders(SimpleEntity[] headers) {

        if (headers == null)
            throw new IllegalArgumentException("Headers not provided!");
        
        ids = checkHeaders(headers) ;
        
        this.headers = Arrays.copyOf(headers, headers.length);
    }

    public static final Set<Integer> checkHeaders(SimpleEntity[] headers) {
        int n = headers.length;

        HashSet<Integer> ids = new HashSet<Integer>();

        // check unique identifiers
        Set<String> identifiers = new HashSet<>();
        for (int i = 0; i < n; i++) {
            ids.add(i);
            SimpleEntity header = headers[i];
            if (header == null || header.getUniqueIdentifier() == null) {
                throw new IllegalArgumentException(String.format("No identifier defined for item %d.", i));
            }
            if (!identifiers.add(header.getUniqueIdentifier())) {
                throw new IllegalArgumentException(
                        String.format("Identifiers are not unique. Duplicate identifier %s for item %d.",
                                header.getUniqueIdentifier(), i));
            }
        }

        return Collections.unmodifiableSet(ids);
    }
    
    public static final SimpleEntity[] updateOrCreateHeaders(SimpleEntity[] headers, int length) {
        return updateOrCreateHeaders(headers, length, HEADER_ID, HEADER_NAME) ;
    }
    
    public static final SimpleEntity[] updateOrCreateHeaders(SimpleEntity[] headers, int length, String headerIdFormat, String headerNameFormat) {
        
        SimpleEntity[] newHeaders = new SimpleEntity[length] ;

        if (headers != null) {
            
           int minLength = length < headers.length ?  length : headers.length ;
           
           for (int i = 0 ; i < minLength ; ++i) 
               //if (headers[i] == null)
              //     newHeaders[i] = new SimpleEntityPojo(String.format(headerIdFormat, i + 1), String.format(headerNameFormat, i + 1)) ;
              // else
                   newHeaders[i] = headers[i] ;
           
           //for (int i = minLength ; i < length ; ++i) 
          //     newHeaders[i] = new SimpleEntityPojo(String.format(headerIdFormat, i + 1), String.format(headerNameFormat, i + 1)) ;
           
        } else {
            for (int i = 0 ; i < length ; ++i) 
                newHeaders[i] = new SimpleEntityPojo(String.format(headerIdFormat, i + 1), String.format(headerNameFormat, i + 1)) ;
        }

        return newHeaders ;
    }

}
