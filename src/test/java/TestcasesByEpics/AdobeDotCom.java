package TestcasesByEpics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import CommonFunctions.CommonFunctions;
import CommonFunctions.JavaScriptUtils;
import nopCommerce.locators.AdobeLocators;

public class AdobeDotCom extends CommonFunctions{
	
	/*
	 * Adobe.com extract product names and print on console
	 */
	@Test(groups="Exploratory", description="Extract names from website")
	public void Get_product_names_from_adobe_website() throws Exception {
		JavaScriptUtils js= new JavaScriptUtils();
		AdobeLocators adolo= new AdobeLocators();
		launchURL("https://www.adobe.com/");
		WebElement closeDialogue= driver.findElement(By.xpath(adolo.getCloseDialogueBoxPopup()));
		try {
			System.out.println("Inside try block: Handling Alert popup");
			closeDialogue.click();
			System.out.println("Alert popup closed successfully");
		} catch (Exception e) {
			System.out.println("No alert poup was displayed");
		}
		String[] menuArray= {"Acrobat", "Explore"};
		waitTime(5);
		String viewAllProductsLink = "//h4[text()='Explore']//following::a[contains(text(), 'View all products')]";
		
		JavaScriptUtils.scrollToElement(viewAllProductsLink);
		waitTime(5);
		clickOn(viewAllProductsLink, "All Adove Products Page");
		waitTime(5);
		if(driver.findElement(By.xpath(adolo.getCloseDialogueBoxPopup())).isDisplayed()) {
			driver.findElement(By.xpath(adolo.getCloseDialogueBoxPopup())).click();
			System.out.println("alert been handled at product page");
		}
		waitTime(5);
		JavaScriptUtils.scrollByPixels(200);
		
		waitTime(5);
		List<WebElement> allProducts= driver.findElements(By.xpath(adolo.getproductsXpaths()));
		waitTime(5);
		Map<Integer, String> productMaps= new HashMap<>();
		List<String> productNames = new ArrayList<>();
		for(int i=0; i<allProducts.size(); i++) {
			String res=allProducts.get(i).getText().trim();
			productNames.add(res);
			productMaps.put(i, res);
		}
		for(Map.Entry<Integer, String> outMap: productMaps.entrySet()) {
			if(!outMap.getValue().isEmpty())
			System.out.println(outMap.getKey()+": ["+outMap.getValue()+"]");
			
		}
		//System.out.println(productMaps);
	}

}
