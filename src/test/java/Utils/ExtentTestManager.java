package Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentTestManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;	// UI of the report
	public ExtentReports extent;	// populate common info on the report
	public ExtentTest test;		// creating test case entry into the report and update status of the test methods
	
	public void onStart(ITestContext context) {
	    sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myReport.html");
	    sparkReporter.config().setDocumentTitle("Automation Report"); // title of the report
	    sparkReporter.config().setReportName("Functional Testing"); // name of the report
	    sparkReporter.config().setTheme(Theme.DARK); // theme of the report
	    
	    extent = new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    extent.setSystemInfo("Computer", "Localhost");
	    extent.setSystemInfo("Environment", "QA");
	    extent.setSystemInfo("Tester Name", "Prashant");
	    extent.setSystemInfo("OS", "Windows10");
	    extent.setSystemInfo("Browser Name", "Chrome");
	    
	  }
	
	public void onTestStart(ITestResult result) {
	    // not implemented
	  }
	
	public void onTestSuccess(ITestResult result) {
	    test = extent.createTest(result.getName());	// create a new entry in the report
	    test.log(Status.PASS, "Test Case Passed is: "+result.getName());	// update status pass / fail / skipped
	  }
	
	public void onTestFailure(ITestResult result) {
	    test = extent.createTest(result.getName());
	    test.log(Status.FAIL, "Test case failed is: "+result.getName());
	    test.log(Status.FAIL, "Test case failed due to: "+result.getThrowable());
	  }
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case Skipped: "+result.getName());
	  }
	
	public void onFinish(ITestContext context) {
	    extent.flush();
	  }
	

}
