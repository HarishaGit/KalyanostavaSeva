import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TimeSheet_Consolidate 
{
	public static void main(String[] args) throws IOException
	{	

    List<String> sEmployeeBuilder = new ArrayList<String>();
    List<String> sEmployeeDate = new ArrayList<String>();
    int TotalDaysAttended = 0;
    int l=0;
    String ExpEmpID=null,  ActEmpID=null, ActEmployeeName=null, ExpEmployeeName=null, ActEmpStatus=null;
    Date ActDate;
    String TimeSheetPath="C:\\temp\\TCTimeSheets";
	File folder = new File(TimeSheetPath);
	File[] listOfFiles = folder.listFiles();
	List<String> sFileInfo = new ArrayList<String>();
	for (int i = 0; i < listOfFiles.length; i++) 
	{
	  if (listOfFiles[i].isFile()) 
	  {
		sFileInfo.add(listOfFiles[i].getName());   
//	    System.out.println("File " + listOfFiles[i].getName());
	  } 
	  else if (listOfFiles[i].isDirectory())
	  {
//	    System.out.println("Directory " + listOfFiles[i].getName());
	  }
	}
//    System.out.println(sFileInfo.toString());
//    System.out.println(sFileInfo.size());
      int iFileCount=sFileInfo.size();
 outer:   
for(int f=0;f<iFileCount;)  
{
    String MainSheetPath= "C:\\temp\\TimeSheets\\MainDoc.xlsx";
	FileInputStream fisMainDoc = new FileInputStream(MainSheetPath);  //Path to fetch the failed modules data.
    @SuppressWarnings("resource")
	XSSFWorkbook MainDocWorkBook= new XSSFWorkbook(fisMainDoc);
    XSSFSheet MainDocSheet = MainDocWorkBook.getSheet("Sheet1");
    int rowCount = MainDocSheet.getLastRowNum(); 
	String sFileName=sFileInfo.get(f);
	FileInputStream TimeSheetRead = new FileInputStream(TimeSheetPath+"\\"+sFileName);  //Path to fetch the failed modules data.
	System.out.println(sFileName);
    @SuppressWarnings("resource")
	XSSFWorkbook TimeSheetworkbook = new XSSFWorkbook(TimeSheetRead);
    XSSFSheet TimeSheet = TimeSheetworkbook.getSheet("Sheet1");
    ActEmpID = TimeSheet.getRow(1).getCell(2).getStringCellValue();
    ExpEmployeeName = TimeSheet.getRow(0).getCell(2).getStringCellValue();
    System.out.println(ActEmpID);
    ActEmpID = ActEmpID.substring(ActEmpID.length() - 3);
    int TimeSheetRowCount = TimeSheet.getLastRowNum(); 
    int ActualDaysInMonth=TimeSheetRowCount+9;
    System.out.println("Employee "+ExpEmployeeName);
    System.out.println("Employee ID: "+ActEmpID);
    //Fetching Employee Info
	for (int p = 2; p<= TimeSheetRowCount; p++)
	  {
		  
		  if(TimeSheet.getRow(p).getCell(0).getDateCellValue().toString()==null)
		  {
			  System.out.println("null");
		  }
		  ActDate=TimeSheet.getRow(p).getCell(0).getDateCellValue();
		  ActEmpStatus=TimeSheet.getRow(p).getCell(2).getStringCellValue();
		  String sActDate=ActDate.toString();
		  String[] parts = sActDate.split(" ");
		  sEmployeeBuilder.add(ActEmpStatus);   
		  sEmployeeDate.add(parts[2]);
		  if(ActEmpStatus.contains("Present"))
		  {
			  TotalDaysAttended++;
		  }

	  }
	
    System.out.println(sEmployeeBuilder.toString());
    System.out.println(sEmployeeDate.toString()); 
	
    for (int q = 0; q <= rowCount; q++)	
    { 	
    	  ExpEmpID = MainDocSheet.getRow(q).getCell(0).getStringCellValue();   
    	  ActEmployeeName=MainDocSheet.getRow(q).getCell(1).getStringCellValue();
    	  if(ExpEmpID.contains(ActEmpID))    		  
           {
    		  System.out.println("Sigtoo");
    		  System.out.println(ActEmployeeName);
    		  Row TotalDay = MainDocSheet.getRow(q);
     		  Cell Totalcell = TotalDay.createCell(43);
     		  Totalcell.setCellValue(TotalDaysAttended);  
     		  String ActVal="Present";
     		  for(int m=11;m<=ActualDaysInMonth;m++)
     		  {
     			 
 	    		 ActVal=sEmployeeBuilder.get(m-11);
     			 Row row = MainDocSheet.getRow(q);
	    		 Cell cell = row.createCell(m);
	    		 System.out.println(ActVal);
	    		 if(ActVal.contains("Present"))
	    		  {
	    			  ActVal="P";
	    		  }
	    		  else if(ActVal.contains("Sick Leave"))
	    		  {
	    			  ActVal="SL";
	    		  }

	    		  else if(ActVal.contains("Earned Leave"))
	    		  {
	    			  ActVal="EL";
	    		  }
	    		  else if(ActVal.contains("Comp Off"))
	    		  {
	    			  ActVal="COF";
	    		  }

	    		  else if(ActVal.contains("Weekend"))
	    		  {
	    			  ActVal="W/O";
	    		  }

	    		  else if(ActVal.contains("LOP"))
	    		  {
	    			  ActVal="LOP";
	    		  }
	    		  else if(ActVal.contains("Holiday"))
	    		  {
	    			  ActVal="H";
	    		  }
	    		  else if(ActVal.contains("Not Applicable"))
	    		  {
	    			  ActVal="NA";
	    		  }
	    		  System.out.println(ActVal);
	    		  cell.setCellValue(ActVal);    
     			      			  
     			  
     		  }
     		  
     		  
   
        
 	        }  
   
     }
    FileOutputStream SaveMainFile = new FileOutputStream(MainSheetPath);
	MainDocWorkBook.write(SaveMainFile);
	SaveMainFile.close();
    System.out.println("Total Days Worked: "+TotalDaysAttended);
    sEmployeeBuilder.clear();
    sEmployeeDate.clear();
    TotalDaysAttended=0;
    f++;


   }
  }	
}


