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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uno.informatics.data.io.FileType;

public class ExcelFilePropertiesWithSheets extends ExcelFileProperties {
    private static final String SHEETS_PROPERTY = ExcelFilePropertiesWithSheets.class.getName() + ".sheets";

    private List<String> sheets;

    public ExcelFilePropertiesWithSheets(File file) {
        this(file, FileType.XLSX, null);
    }

    public ExcelFilePropertiesWithSheets(File file, FileType fileType) {
        this(file, FileType.XLSX, null);
    }

    public ExcelFilePropertiesWithSheets(File file, FileType fileType, List<String> sheets) {
        super(file, fileType);

        this.sheets = new ArrayList<String>();
        if (sheets != null)
            this.sheets.addAll(sheets);
    }

    public void setSelectedSheet(String selectedSheet) {
        super.setSelectedSheet(selectedSheet);

        if (selectedSheet != null && !this.sheets.contains(selectedSheet))
            addSheet(selectedSheet);
    }

    public final List<String> getSheets() {
        return sheets;
    }

    public final void setSheets(List<String> sheets) {
        if (this.sheets != sheets) {
            List<String> oldValue = sheets;

            if (sheets != null) {
                this.sheets = new ArrayList<String>(sheets.size());

                this.sheets.addAll(sheets);

                getPropertyChangeSupport().firePropertyChange(SHEETS_PROPERTY, oldValue, this.sheets);

                if (getSelectedSheet() != null && !this.sheets.contains(getSelectedSheet()))
                    setSelectedSheet(null);
            } else {
                this.sheets = new ArrayList<String>(0);

                getPropertyChangeSupport().firePropertyChange(SHEETS_PROPERTY, oldValue, this.sheets);

                setSelectedSheet(null);
            }
        }
    }

    public final boolean addSheet(String sheet) {
        if (sheet != null) {
            List<String> oldValue = sheets;

            sheets = new ArrayList<String>(oldValue.size() + 1);

            boolean success = oldValue.isEmpty() || sheets.addAll(oldValue);

            if (success)
                success = sheets.add(sheet);

            if (success)
                getPropertyChangeSupport().firePropertyChange(SHEETS_PROPERTY, oldValue, this.sheets);

            return success;
        } else {
            return false;
        }
    }

    public final boolean removeSheet(String sheet) {
        if (sheet != null) {
            List<String> oldValue = sheets;

            sheets = new ArrayList<String>(oldValue.size() > 0 ? oldValue.size() - 1 : 0);

            boolean success = oldValue.isEmpty() || sheets.addAll(oldValue);

            if (success)
                success = sheets.remove(sheet);

            if (success)
                getPropertyChangeSupport().firePropertyChange(SHEETS_PROPERTY, oldValue, this.sheets);

            return success;
        } else {
            return false;
        }
    }

    public final void removeAllFiles() {
        List<String> oldValue = sheets;

        sheets = new ArrayList<String>(0);

        getPropertyChangeSupport().firePropertyChange(SHEETS_PROPERTY, oldValue, this.sheets);
    }

    public final String[] getSheetsAsArray() {
        return sheets.toArray(new String[sheets.size()]);
    }
}
