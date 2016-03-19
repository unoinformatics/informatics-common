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
 */

package uno.informatics.data.pojo;

import java.util.List;

import uno.informatics.data.Entity;
import uno.informatics.data.Germplasm;

public class GermplasmPojo extends EntityPojo implements Germplasm {

    private List<Germplasm> source;

    public GermplasmPojo(String name) {
        super(name);
    }

    public GermplasmPojo(String uniqueIdentifier, String name) {
        super(uniqueIdentifier, name);
    }

    public GermplasmPojo(String uniqueIdentifier, String name, String description) {
        super(uniqueIdentifier, name, description);
    }

    public GermplasmPojo(Entity identifier) {
        super(identifier);
    }

    @Override
    public List<Germplasm> getSource() {
        return source ;
    }
    
    // TODO property event
    // TODO add source
}
