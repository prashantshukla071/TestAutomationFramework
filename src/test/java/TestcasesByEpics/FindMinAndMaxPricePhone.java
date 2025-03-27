package TestcasesByEpics;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import CommonFunctions.CommonFunctions;

public class FindMinAndMaxPricePhone extends CommonFunctions{
		
	/*
	 	Functionality: it checks for the input, stores data in a Map<WebElement, Integer>
	 	WebElement:>> hyperlink for the product, Integer:>> price of the product
	 	sorts by value, then clicks on the key for the value passed
	 */
	@Test(description = "Test-ID", groups={"Smoke", "Regression" })	
	void minAndMaxPrices() throws Exception {
		launchURL("https://www.flipkart.com/");
		
		WebElement searchButton= driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
		searchButton.sendKeys("mobile phones");
		searchButton.submit();
		waitTime(10);
		
		List<WebElement> phonePrices= driver.findElements(By.xpath("//div[@class='Nx9bqj _4b5DiR']"));
		Map<WebElement, Integer> priceMap = new HashMap<WebElement, Integer>();
		for(int i=0; i<phonePrices.size(); i++) {
			if(phonePrices.get(i).getText() != "") {
				priceMap.put(phonePrices.get(i), Integer.parseInt(phonePrices.get(i).getText().replaceAll("₹", "").replaceAll(",", "")));
			}
		}
		
		List<Entry<WebElement, Integer>> listEntry = new ArrayList<Map.Entry<WebElement, Integer>>(priceMap.entrySet());
		listEntry.sort(Entry.comparingByValue());
		
		for(Entry e : listEntry) {
			System.out.println(e.getValue());
		}
		
		listEntry.get(0).getKey().click();
		waitTime(10);
		
		
		
	}
}
	

