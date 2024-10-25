package Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resource.ExtentReporter;

public class Listener extends BaseClass implements ITestListener{
	ExtentReports extent = ExtentReporter.Report();
	ExtentTest extenttest;
	ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();
	//to assign the unique threadid to extenttest, so that it will not map the result to the different test
	  public void onTestStart(ITestResult result) {
		    // not implemented
		  extenttest= extent.createTest(result.getMethod().getMethodName());
		  thread.set(extenttest);//assign unique threadid for the extenttest object
		 
		  }

		  public void onTestSuccess(ITestResult result) {
		    // not implemented
			  thread.get().log(Status.PASS, "Test case is passed");
			  //thread.get - will take unqiue thread id
		  }

		  public void onTestFailure(ITestResult result) {
		    // not implemented
			  ExtentTest test = thread.get();
			  if(test!=null) {
				  test.fail(result.getThrowable());
			  }
			  else {
				  System.out.println("ExtentTest instance not found for this thread.");
			  }
			  try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(driver!=null) {
			  String path=null;
			  try {
				path = TakingScreenshot(result.getMethod().getMethodName(), driver);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  thread.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
			  }
			  else {
				  System.out.println("WebDriver is null, cannot take screenshot.");
			  }
		  }

		  public void onTestSkipped(ITestResult result) {
		    // not implemented
			  
			  
		  }

		  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }

		  public void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }

		  public void onStart(ITestContext context) {
		    // not implemented
		  }

		  public void onFinish(ITestContext context) {
		    // not implemented
			  extent.flush();
		  }
		}
