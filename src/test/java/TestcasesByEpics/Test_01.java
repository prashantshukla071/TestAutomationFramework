package TestcasesByEpics;

import org.testng.annotations.Test;

import CommonFunctions.CommonFunctions;
import Utils.DriverManager;
import Utils.Log;

public class Test_01 extends CommonFunctions{
	
	

	@Test()
	public void testValidLogin() {
		launchURL(DatePickerURL);
		datePicker("2026", "March", "5");
		Log.info("Successfully entered the date..");
	}
	
	@Test()
	public void testReadExcelAndPrintOnConsole() {
		
		String filepath = ".\\datafiles\\countries.xlsx";
		Log.info("Excel reader method is initialized");
		excelFileReader(filepath);
		System.out.println("The Data has been printed on console");
		Log.info("Testcase is complete, the data hasbeen successfully printed");
	}
	
}
