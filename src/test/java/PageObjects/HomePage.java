package PageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import CommonFunctions.CommonFunctions;
import CommonFunctions.JavaScriptUtils;
import nopCommerce.locators.locators;

public class HomePage extends CommonFunctions{

	//	public HomePage(WebDriver driver) {
	//		this.driver=driver;
	//	}
	
	locators loc= new locators();

	@Test(description = "Login to HomePage", groups={"Smoke", "Regression"})
	public void homePageLogin() throws Exception {
		System.out.println("Running Test Method");
		driver.get(DatePickerURL);
		
		/*
		 * takesScreenshot("HomePage");
		 * System.out.println("Page Title: "+getPageTitle());
		 * 
		 * String actualTitle =
		 * "Free and open-source eCommerce platform. ASP.NET Core based shopping cart. - nopCommerce"
		 * ; Assert.assertEquals(actualTitle, getPageTitle());
		 * 
		 * takesScreenshotOfSection("//div[@class='testimonial-slider']",
		 * "Testimonials"); takesScreenshotOfElement("//img[@title='nopCommerce']",
		 * "Logo"); System.out.println("Test Passed!!");
		 * 
		 * clickOn(loc.getProductPage(), "ProductPage");
		 * System.out.println("product page option navigated and clicked");
		 * clickOn(loc.getFeaturesInProduct(), "FeaturePage");
		 * System.out.println("feature page option navigated and clicked"); waitTime(6);
		 * takesScreenshot("FeaturePage");
		 */

		//	Thread.sleep(5000);

		String verificationXpath = "//h4[contains(text(), 'Type the characters you see in this image:')]";
		
		datePicker("2026", "March", "5");

		/*
		 * if(verifyVisibleByXpath(verificationXpath)) { waitTime(5); String captcha=
		 * getText("//div[@class= 'a-row a-text-center']/img");
		 * enterByXpath("//input[@id= 'captchacharacters']", captcha); waitTime(5);
		 * clickOn("//button[contains(text(), 'Continue shopping')]",
		 * "continue shopping Button");
		 * 
		 * }else { System.out.println("Captcha Verification Page is not displayed!!"); }
		 */
		
		
//		openLinkInNewTab(ultimateQA, SauceDemo, UltimateQAAutomation);
//		JavaScriptUtils.scrollDown();
//		JavaScriptUtils.scrollToElement("//h2[@class='business-title']");
//		waitTime(3);
//		JavaScriptUtils.highlightElement("//h2[@class='business-title']");
//		waitTime(3);
//		JavaScriptUtils.drawBorder("//h3[normalize-space()='Enterprise business']");
//		waitTime(5);
		
		
		

	}
	
	

}
