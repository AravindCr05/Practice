package resource;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	public class ExtentReporter{
		
		@BeforeTest
		public static ExtentReports Report() {
			//Two object need to be created - Extents Report(create the test result execution), Extent Spark Reporter(create the report)
			//Need to pass the path in the object for creating the report in a path
			String path = System.getProperty("user.dir") +"//Report//report.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			reporter.config().setReportName("Automation Results");
			reporter.config().setDocumentTitle("Execution Results");
			ExtentReports extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Ramesh");
			return extent;
		}
	}
