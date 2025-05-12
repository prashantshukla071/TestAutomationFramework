package Utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	//Singleton pattern to maintain only one instance throughout
	//private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static WebDriver driver;
	
	//private DriverManager constructor to prevent instantiation.
	private DriverManager() {		
	}
	
	//Factory Method to get the driver instance
	public static WebDriver getDriver(String browserType) {
		if(driver==null) {
			browserType.toLowerCase();
			System.out.println("Browser: "+browserType);
			
			switch(browserType) {
			
			case "chrome": 
				ChromeOptions option= new ChromeOptions();
				//option.addArguments("--headless");
				WebDriverManager.chromedriver().setup();
				
				driver= new ChromeDriver(option);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				break;
				
			case "firefox": 
				WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				break;
				
			case "edge": 
				WebDriverManager.edgedriver().setup();
				driver= new EdgeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				break;
				
			default: 
				throw new IllegalArgumentException("Unsupported Browser Type: "+browserType);
			}
		}
		return driver;		
	}
	
	//Method to quit the driver
	
	public static void quitDriver() {
		if(driver!=null) {
			driver.quit();
			driver=null;	//Reset the driver instance
		}
	}

}
