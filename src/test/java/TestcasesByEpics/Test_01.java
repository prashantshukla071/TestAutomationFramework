package TestcasesByEpics;

import org.testng.annotations.Test;

import CommonFunctions.CommonFunctions;
import Utils.Log;

public class Test_01 extends CommonFunctions{
	
	

	@Test()
	public void testValidLogin() {
		launchURL(DatePickerURL);
		datePicker("2026", "March", "5");
		Log.info("Successfully entered the date..");
	}
	
}
