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

import uno.informatics.data.io.FileType;

public class ExcelFileProperties extends FileProperties
{
	private static final String SHEET_PROPERTY = ExcelFileProperties.class.getName() + ".sheet";
	
	private String selectedSheet ;
	
	public ExcelFileProperties(String file)
  {
		super(file, FileType.XLSX);
  }
	
	public ExcelFileProperties(String file, FileType fileType)
  {
		super(file, FileType.XLSX);
  }
	
	public ExcelFileProperties(File file)
  {
		super(file, FileType.XLSX);
  }
	
	public ExcelFileProperties(File file, FileType fileType)
  {
		super(file, FileType.XLSX);
  }
	
	public final String getSelectedSheet()
	{
		return selectedSheet;
	}

	public void setSelectedSheet(String selectedSheet)
	{
		String oldValue = this.selectedSheet ;
		
		this.selectedSheet = selectedSheet;
		
		getPropertyChangeSupport().firePropertyChange(SHEET_PROPERTY, oldValue, this.selectedSheet) ;
	}
	
	@Override
  public int hashCode()
  {
	  final int prime = 31;
	  int result = super.hashCode();
	  result = prime * result
	      + ((selectedSheet == null) ? 0 : selectedSheet.hashCode());
	  return result;
  }

	@Override
  public boolean equals(Object obj)
  {
	  if (this == obj)
		  return true;
	  if (!super.equals(obj))
		  return false;
	  if (getClass() != obj.getClass())
		  return false;
	  ExcelFileProperties other = (ExcelFileProperties) obj;
	  if (selectedSheet == null)
	  {
		  if (other.selectedSheet != null)
			  return false;
	  }
	  else
		  if (!selectedSheet.equals(other.selectedSheet))
			  return false;
	  return true;
  }

	protected final void validateFileType(FileType fileType) throws IllegalArgumentException
  {
		if (!(FileType.XLS.equals(fileType) || FileType.XLSX.equals(fileType)))
			throw new IllegalArgumentException("File type must be " + FileType.XLS + " or " + FileType.XLSX + " and not : " + fileType) ;
  }
}
