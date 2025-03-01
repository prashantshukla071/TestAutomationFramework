package CommonFunctions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import Utils.DriverManager;
import Utils.ListenerClass;
import Utils.Log;

public class CommonFunctions {

	public static WebDriver driver;
	
//	ListenerClass listenerClass;
	
	public static void main(String[] args) {
		ListenerClass ls = new ListenerClass();
		ls.statementOne();
		
	}
	

	
	//URLs for the FreeAutomationPractice websites
	public String NopCommerce = "https://www.nopcommerce.com/";
	public String ultimateQA = "https://ultimateqa.com/";
	public String SauceDemo = "https://www.saucedemo.com/";
	public String UltimateQAAutomation= "https://ultimateqa.com/automation";
	public String OrangeHRMLive = "https://opensource-demo.orangehrmlive.com/";
	public String MyShopEcommerce = "http://www.automationpractice.pl/index.php";
	public String DatePickerURL= "https://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html";

	/*
	 * @BeforeMethod(alwaysRun = true) public void setup() {
	 * System.out.println("In Setup ============"); String projectPath =
	 * System.getProperty("user.dir");
	 * System.out.println("Project Path: "+projectPath);
	 * System.setProperty("webdriver.chrome.driver",
	 * projectPath+"/src/test/resources/drivers/chromedriver.exe");
	 * 
	 * driver = new ChromeDriver(); driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); }
	 */



	/*
	 * @AfterMethod(alwaysRun = true) public void tearDown() { try {
	 * System.out.println("In TearDown Closing Browser ============");
	 * if(driver!=null) { driver.close(); driver.quit(); } }
	 
		catch (Exception e) { 
			Alert alert = driver.switchTo().alert(); 
			alert.accept(); 
			driver.quit(); } 
	}
	*/
	
	@BeforeMethod()
	@Parameters("browser")	//Pass the browser type from TestNG.XML
	public void setup(String browser) {
		Log.info("Starting Browser");
		driver = DriverManager.getDriver(browser);
	}
	
	@AfterMethod()
	public void teardown() {
		Log.info("Closing Browser");
		DriverManager.quitDriver();
	}


	/*
	 * public boolean verifyVisibleByXpath(String xpath) { boolean result =
	 * driver.findElement(By.xpath(xpath)).isDisplayed(); return result; }
	 */

	public String getPageTitle() throws Exception{
		return driver.getTitle();
	}

	public void waitTime(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Explicit Wait
	public void explicitWait(String xpathOfElement, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOfElement)));
	}

	public void SwitchToSelectedFrame(int iframe){
		try {
		waitTime(5);
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println("The Sixe of the frame is: "+frames.size());
		if(frames.size()!=0) {
			driver.switchTo().frame(iframe);
			Log.info("Successfully Switched the frame: {"+iframe+"}");
		}
	}catch(IndexOutOfBoundsException e) {
		System.out.println("Provided frame is out of bound.."+ e.getMessage());
	}
	}

	public void SwitchToFrame() throws Exception{		
		waitTime(5);
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println("The Sixe of the frame is: "+frames.size());
		if(frames.size()!=0) {
			driver.switchTo().frame(frames.get(0));
			waitTime(5);
		}		
	}

	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	public boolean verifyVisibleByXpath(String xpath) {
		int element = driver.findElements(By.xpath(xpath)).size();
		if(element>0) {
			return true;
		} else return false;
	}

	public void clickOn(String xpath, String obj) {
		String text= driver.findElement(By.xpath(xpath)).getText();
		driver.findElement(By.xpath(xpath)).click();
		System.out.println("Button with name: "+text+ " is clicked successfully");
	}

	public void enterByXpath(String xpath, String value) {
		driver.findElement(By.xpath(xpath)).clear();
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}

	public void selectDropdownByText(String xpath, String text) {
		WebElement dropdowns=driver.findElement(By.xpath(xpath));
		Select sl = new Select(dropdowns);
		sl.selectByVisibleText(text);
	}

	public void selectDropdownByIndex(String xpath, int index) {
		WebElement dropdowns=driver.findElement(By.xpath(xpath));
		Select sl = new Select(dropdowns);
		sl.selectByIndex(index);;
	}

	public void selectDropdownByValue(String xpath, String text) {
		WebElement dropdowns=driver.findElement(By.xpath(xpath));
		Select sl = new Select(dropdowns);
		sl.selectByValue(text);
	}

	public String getText(String xpath) {
		String GetText="";
		try {			
			WebElement element= driver.findElement(By.xpath(xpath));
			GetText = element.getText().trim();
			System.out.println("['" +GetText+ "'] value Captured successfully.");
		} catch (Exception e){
			e.printStackTrace();
			System.err.println("Unable to capture Text");
		}
		return GetText;
	}

	public String getTextByWebElement(WebElement Element) {
		String GetText="";
		try {			
			GetText = Element.getText().trim();
			System.out.println("['" +GetText+ "'] value Captured successfully.");
		} catch (Exception e){
			e.printStackTrace();
			System.err.println("Unable to capture Text");
		}
		return GetText;
	}
	
	public void newWindow(int windowIndex) throws Exception{
		String parentWindow=driver.getWindowHandle();
		System.out.println("Current Window: "+driver.getTitle());
		List<String> allWindowHandles= new ArrayList<String>(driver.getWindowHandles());
		if(allWindowHandles.size()!=0) {
			if(windowIndex < allWindowHandles.size()) {
				driver.switchTo().window(allWindowHandles.get(windowIndex));
				System.out.println("New Window: "+driver.getTitle());
			} else {
				System.out.println("Invalid window index = "+windowIndex);
			}
		} else {
			System.out.println("Window size is invalid!!");
		}		
	}
	

	public void navigateToNewWindow() throws Exception{
		String parentWindow= driver.getWindowHandle();
		System.out.println("Parent Window: "+parentWindow);
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		System.out.println("In New Window: "+driver.getTitle());
	}
	
	public void switchToWindow(int win) {
		String parentWindow = driver.getWindowHandle();
		String parentWindowTitle = driver.getTitle();
		System.out.println("Parent Window Title: "+parentWindowTitle);
		
		ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		if(win<windowHandles.size()) {
			driver.switchTo().window(windowHandles.get(win));
			System.out.println("New Window: "+driver.getTitle());
		}
	}

	public void takesScreenshot(String ssName) throws IOException {	
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\"+ssName+".png");
		FileUtils.copyFile(src, trg);

	}
	public void takesScreenshotOfSection(String xpathOfSection, String sectionName) throws IOException {
		WebElement section= driver.findElement(By.xpath(xpathOfSection));
		File src = section.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\"+sectionName+".png");
		FileUtils.copyFile(src, trg);
	}
	public void takesScreenshotOfElement(String xpathOfElement, String elementName) throws IOException {
		WebElement ele= driver.findElement(By.xpath(xpathOfElement));
		File src = ele.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\"+elementName+".png");
		FileUtils.copyFile(src, trg);
	}	
	public String toolTipCapturer(String xpath) {
		String tooltip="";
		WebElement key= driver.findElement(By.xpath(xpath));
		tooltip= key.getAttribute("title");
		return tooltip;
	}

	public void openLinkInNewTab(String linktext) {		 
		String tab= Keys.chord(Keys.CONTROL, Keys.ENTER);
		driver.findElement(By.linkText(linktext)).sendKeys(tab);
	}

	public void openLinkInNewTab(String url, String url2, String url3) throws IOException {
		List<String> list = new ArrayList<String>();
		list.add(url);
		list.add(url2);
		list.add(url3);
		if(list.size()>1) {
			for(int i=0; i<list.size(); i++) {
				String URL= list.get(i);
				System.out.println("Url: "+ URL);
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(URL);
				System.out.println("Page Title: "+driver.getTitle());
				takesScreenshot(driver.getTitle());
				System.out.println("Navigated to the URL in New Tab");
			}
		} else {
			System.out.println("Doesnot have multiple links to open");
		}
	}

	public void openLinkInTab(String url) {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(url);
	}	
	public void openLinkInWindow(String url) {
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get(url);
	}
	
	public void openLinkInNewWindow(String url, String url2, String url3) {
		List<String> list = new ArrayList<String>();
		list.add(url);
		list.add(url2);
		list.add(url3);
		if(list.size()>1) {
			for(int i=0; i<list.size(); i++) {
				String URL= list.get(i);
				System.out.println("Url: "+ URL);
				driver.switchTo().newWindow(WindowType.WINDOW);
				driver.get(URL);
			}
		} else {
			System.out.println("Doesnot have multiple links to open");
		}
	}

	public void datePicker(String expYear, String expMonth, String day) {
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		
		//Implementing explicit wait mechanism
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
		
		String currentMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		String currentYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		
		while(!(currentMonth.equals(expMonth) && currentYear.equals(expYear))) {
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			currentMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
			currentYear = driver.findElement(By.className("ui-datepicker-year")).getText();			
		}
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(), '"+day+"')]")).click();
	}


}
