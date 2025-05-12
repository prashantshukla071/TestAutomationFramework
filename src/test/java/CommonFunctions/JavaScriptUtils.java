package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils extends CommonFunctions {

	//	public JavaScriptUtils() {

	//	}
	public static void scrollDown() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");  // Scroll down by 500 pixels
		System.out.println("ScrollDown by 500 pixels");
	}

	public static void scrollDown(String bottom) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		System.out.println("ScrollDown to the Bottom");
	}

	public static void scrollUp() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-500)");  // Scroll up by 500 pixels
		System.out.println("ScrollDown up 500 pixels");
	}

	public static void scrollUp(String top) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
		System.out.println("ScrollDown up to Top");
	}

	public static void highlightElement(String element) throws Exception {
		WebElement ele= driver.findElement(By.xpath(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor = 'yellow'", ele);
		System.out.println("Highlighted element");
	}

	public static void drawBorder(String element) throws Exception {
		WebElement ele= driver.findElement(By.xpath(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", ele);
		System.out.println("drawn Border around the element");
	}

	public static void highlightAndBorderElement(String element) throws Exception{
		WebElement ele= driver.findElement(By.xpath(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor = 'yellow'; arguments[0].style.border='3px solid red'", ele);
		System.out.println("Highlighted and Drawn Border around the element");
	}
	
	public static void scrollToElement(String element) throws Exception{
	    WebElement ele = driver.findElement(By.xpath(element));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", ele);
	}

	public static void scrollByPixels(int pixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, arguments[0])", pixel);
	}

}
