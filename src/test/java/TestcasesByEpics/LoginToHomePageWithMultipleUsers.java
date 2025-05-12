package TestcasesByEpics;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginToHomePageWithMultipleUsers {

	//Locator loc = new Locator();
	
	/*
	 * Simple functionality to check the login credentials for 10 iteration of data passing
	 */

	@Test(dataProvider = "loginCredentials")
	public void loginToPage(String username, String password) {
		// loc.enterUsername(username);
		//loc.enterPassword(password);
		//loc.clickLoginButton();

		// Check if login was successful (dummy condition)
		//  boolean loginSuccess = loc.isHomepageDisplayed(); 

		// Store result (could be written to Excel for reporting)
		// System.out.println("User: " + username + " | Login Successful? " + loginSuccess);
	}

	@DataProvider(name = "loginCredentials")
	public Object[][] getData() throws IOException {
		FileInputStream fis = new FileInputStream(new File("TestData.xlsx"));
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("Login");

		List<Object[]> credentials = new ArrayList<>();

		for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 (skip header)
			Row row = sheet.getRow(i);
			String username = row.getCell(0).getStringCellValue();
			String password = row.getCell(1).getStringCellValue();
			credentials.add(new Object[]{username, password});
		}

		workbook.close();
		return credentials.toArray(new Object[0][]);
	}
}
