package CommonFunctions;
import org.openqa.selenium.interactions.Actions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Iterator;
import org.apache.poi.xssf.usermodel.*;
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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import Utils.DriverManager;
import Utils.ExtentTestManager;
import Utils.Log;

public class CommonFunctions {

	protected static WebDriver driver;

	//URLs for the FreeAutomationPractice websites
	public String NopCommerce = "https://www.nopcommerce.com/";
	public String ultimateQA = "https://ultimateqa.com/";
	public String SauceDemo = "https://www.saucedemo.com/";
	public String UltimateQAAutomation= "https://ultimateqa.com/automation";
	public String OrangeHRMLive = "https://opensource-demo.orangehrmlive.com/";
	public String MyShopEcommerce = "http://www.automationpractice.pl/index.php";
	public String expandTesting="https://practice.expandtesting.com/tables";
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
	public static void launchURL(String url) {
		driver.get(url);
	}

	/*
	takes input parameter @browser type from testng.xml
	DriverManager.java is configured as such that after taking input from testng.xml it initializes the driver
	 */
	@BeforeMethod()
	@Parameters("browser")	
	public void setup(String browser) {
		Log.info("Starting Browser");
		driver = DriverManager.getDriver(browser);
	}

//	@AfterMethod()
//	public void teardown() {
//		Log.info("Closing Browser");
//		DriverManager.quitDriver();
//	}
	
	@DataProvider(name="Random-data")
	public Object[][] getData(String str){
		return new Object[][] {
			{"first-value"}, {"second-value"}, {"", ""}
		};
	}
	
	static void PropertyFileReader() throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\config.properties");
		properties.load(fis);
		String browser= properties.getProperty("browser");
		System.out.println("Browser: "+browser);
		String url = properties.getProperty("URL");
		System.out.println("URL: "+url);
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
	
	//Fluent Wait
	public void fluentWait(int waitDurationInSeconds, int pollingEveryInSeconds) {
	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
	wait.withTimeout(Duration.ofSeconds(waitDurationInSeconds));
	wait.pollingEvery(Duration.ofSeconds(pollingEveryInSeconds));
	wait.ignoring(NoSuchElementException.class);
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
		WebElement ele= driver.findElement(By.xpath(xpath));
		ele.clear();
		ele.sendKeys(value);
	}

	public void selectDropdownByText(String xpath, String text) {
		WebElement dropdown=driver.findElement(By.xpath(xpath));
		Select sl = new Select(dropdown);
		sl.selectByVisibleText(text);
	}

	public void selectDropdownByIndex(String xpath, int index) {
		WebElement dropdown=driver.findElement(By.xpath(xpath));
		Select sl = new Select(dropdown);
		sl.selectByIndex(index);;
	}

	public void selectDropdownByValue(String xpath, String text) {
		WebElement dropdowns=driver.findElement(By.xpath(xpath));
		Select sl = new Select(dropdowns);
		sl.selectByValue(text);
	}
	
	public  void selectDropdownInLargeOptionSet(String xpath, String partialText) {
		Select dropdown= new Select(driver.findElement(By.xpath(xpath)));
		List<WebElement> options= dropdown.getOptions();
		for(WebElement option: options) {
			if(option.getText().contains(partialText)) {
				option.click();
				break;
			}
		}
	}
	
	public void selectTheOption(String xpath, String text) {
		List <WebElement> dropdowns= driver.findElements(By.xpath(xpath));
		int indexCount=0;
		boolean flag=false;
		for(WebElement drop: dropdowns) {
			indexCount++;
			if(drop.getText().trim().equalsIgnoreCase(text)){
				drop.click();
				System.out.println("Option found at index: "+indexCount);
				flag=true;
				break;
			}
		}
		if(!flag) {
			System.out.println("No option found with name '"+text+"'");
		}
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

	public String getText(WebElement Element) {
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

	public void switchToWindow(int targetWindow) {
		String parentWindow = driver.getWindowHandle();
		String parentWindowTitle = driver.getTitle();
		System.out.println("Parent Window Title: "+parentWindowTitle);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> allWindowHandlesList = new ArrayList<>(windowHandles);
		System.out.println("Size of the Window List: "+allWindowHandlesList.size());
		if(targetWindow>0 && targetWindow < allWindowHandlesList.size()) {
			driver.switchTo().window(allWindowHandlesList.get(targetWindow));
			System.out.println("New Window: "+driver.getTitle());
		} else {
			System.out.println(targetWindow+" Invalid Window Index");
		}
	}

	public void takesScreenshot(String ssName) throws IOException {	
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src=	ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(".\\screenshots\\"+ssName+".png"));

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
		List<String> list =  Arrays.asList(url, url2, url3);
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
		List<String> list = Arrays.asList(url, url2, url3);
		if(list.size()>1) {
			for(int i=0; i<list.size(); i++) {
				String URL= list.get(i);
				System.out.println("Url: "+ URL);
				driver.switchTo().newWindow(WindowType.WINDOW);
				driver.get(URL);
			}
		} else {
			System.out.println("Doesn't have multiple links to open");
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


	public static void excelFileReader(String filepath) {
		//		String filePath= filepath;
		try {
			FileInputStream input = new FileInputStream(filepath);

			XSSFWorkbook workbook = new XSSFWorkbook(input);
			XSSFSheet sheet = workbook.getSheetAt(0);

			/*	int rows=sheet.getLastRowNum();
			int cols = sheet.getRow(1).getLastCellNum();

			//For-Loop			
			for(int r=0; r<rows; r++) {			//outer loop represents the rows in excel
				XSSFRow row = sheet.getRow(r);

				for(int c=0; c<cols; c++) {		// inner loop represents the cell in each row
					XSSFCell cell = row.getCell(c);

					switch (cell.getCellType()) {
					case STRING : System.out.print(cell.getStringCellValue()); break;
					case NUMERIC : System.out.print(cell.getNumericCellValue()); break;
					case BOOLEAN : System.out.print(cell.getBooleanCellValue()); break;
					case BLANK:
						break;
					case ERROR:
						break;
					case FORMULA:
						break;
					case _NONE:
						break;
					default:
						break;					
					}
					System.out.print(" | ");
				}
				System.out.println();
			}
			 */

			Iterator rowiterator = sheet.iterator();
			while(rowiterator.hasNext()) {
				XSSFRow row = (XSSFRow) rowiterator.next();
				Iterator cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					XSSFCell cell = (XSSFCell) cellIterator.next();

					switch (cell.getCellType()) {
					case STRING : System.out.print(cell.getStringCellValue()); break;
					case NUMERIC : System.out.print(cell.getNumericCellValue()); break;
					case BOOLEAN : System.out.print(cell.getBooleanCellValue()); break;
					case BLANK:
						break;
					case ERROR:
						break;
					case FORMULA:
						break;
					case _NONE:
						break;
					default:
						break;					
					}
					System.out.print(" | ");
				}
				System.out.println();
			}			
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* @param: takes length of the token to be generated 
	 * @Return: String token, UpperCase
	 * @Functionality: can be used to generate random token value based on the input length provided
	 */
	public String randomCharacters(int lengthOfString) {
		String reqRandom= UUID.randomUUID().toString();
		
		reqRandom = reqRandom.replace("-", "");
		reqRandom= reqRandom.substring(0, lengthOfString).toUpperCase();
		//System.out.println(reqRandom);
		return reqRandom;
	}
	
	/*
	 *  @param: takes length of the token to be generated 
	 * @Return: String token
	 * @Functionality: can be used to generate random Alpha-Numeric token value based on the input length provided
	 */
	
	static String randomAlphaNumericTokens(int length) {
		String token= RandomStringUtils.random(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
		System.out.println("Token: "+token);
		return token;
	}
	
	/*
	 *  @param: takes length of the token to be generated & type of the Random Value
	 * @Return: String token
	 * @Functionality: can be used to generate random
	 * 					Numeric token
	 * 					Character token
	 * 					Alpha-Numeric token
	 */
	static String RandomValueGenerator(int length, String type) {
		SecureRandom random = new SecureRandom();		
		type=type.toLowerCase();
		
		if(length<=0) {
			return "Invalid Length";
		}
		
		switch (type) {
		
		//Numeric Value Generator
		case "numeric":
			String numbers = "0123456789";
			StringBuilder numericSB = new StringBuilder();
			for(int i=0; i<length; i++) {
				int randomValue=random.nextInt(numbers.length());
				numericSB.append(numbers.charAt(randomValue));
			}
			//System.out.println("Random Number: "+numericSB.toString());
			return numericSB.toString();
			
		//Characters Value Generator	
		case "character":
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			StringBuilder characterSB = new StringBuilder();
			for(int i=0; i<length; i++) {
				int randomValue= random.nextInt(characters.length());
				characterSB.append(characters.charAt(randomValue));
			}
			//System.out.println("Random Number: "+characterSB.toString());
			return characterSB.toString();
			
		//Alpha-Numeric Value Generator		
		case "alphanumeric":
			String alphanumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			StringBuilder alphanumericSB = new StringBuilder();
			for(int i=0; i<length; i++) {
				int randomValue= random.nextInt(alphanumeric.length());
				alphanumericSB.append(alphanumeric.charAt(randomValue));
			}
			//System.out.println("Random Number: "+alphanumericSB.toString());
			return alphanumericSB.toString();
			
		default:
			return "Invalid Type Provided";
		}
	}
	
	/*
	 * @param: takes locator, actionType to perform
	 * @Functionalities: click, Mouse Hover, Double Click, Right Click, Click and Hold
	 */
	public void ActionsClassOperations(String locator, String actionType) {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(locator));
		actionType.toLowerCase();
		
		switch (actionType) {		
		//Perform Click Action on the Web Element
		case "click": 
			action.moveToElement(element).click();
			break;		
		//Perform Mouse Hover Action on the Web Element
		case "mousehover": 
			action.moveToElement(element).build().perform();
			break;
		//Double Click action on the Web Element	
		case "doubleclick": 
			action.doubleClick(element).perform();
			break;
		//Right Click action on the Web Element
		case "rightclick": 
			action.contextClick(element).perform();
			break;
		//Click and Hold action on the Web Element	
		case "clickandhold":
			action.clickAndHold(element).perform();
			break;
		default: 
			System.out.println("Invalid Type");
		}
	}
	
	static void dragAndDrop(WebElement src, WebElement trg) {
		Actions action = new Actions(driver);		
		action.dragAndDrop(src, trg).perform();
	}
	
	static void toolTipVerify(String locator, String text) {
		WebElement ele= driver.findElement(By.xpath(locator));
		String tooltip= ele.getAttribute("title");
		Assert.assertEquals(text, tooltip);
	}
	
	static void basicURLValidator() {
		List<WebElement> links=driver.findElements(By.tagName("a"));
		for(WebElement link: links) {
			String url=link.getAttribute("href");
			
			if(url.isEmpty() || url==null) {
				System.out.println("URL is empty or not set");
				continue;
			}
			
			if(url.startsWith("http://") || url.startsWith("https://")) {
				System.out.println("URL is valid (format): "+url);
			} else {
				System.out.println("URL is invalid (bad-format): "+url);
			}
		}
	}
	static void URLValidator() {
		List<WebElement> links= driver.findElements(By.tagName("a"));
		for(WebElement link: links) {
			String url= link.getAttribute("href");
			try {
				if(connectionRequest(url)) {
					System.out.println("Valid URL: "+url);
				} else {
					System.out.println("invalid URL: "+url);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	static boolean connectionRequest(String url) throws MalformedURLException, IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		int responseCode= connection.getResponseCode();
		boolean result=false;
		
		if(responseCode>=400) {
			System.out.println("URL is broken (invalid), response code: "+responseCode);
			result=true;
		} else {
			System.out.println("URL is (valid) "+responseCode);
		}
		return result;
	}
	
	static void robotAction() throws AWTException {
		Robot robot = new Robot();
		
		//Enter Button Action
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	


}
