package Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import POMclasses.LoginPage;

public class BaseClass {
public WebDriver driver;
public LoginPage landing;
	public WebDriver Initializedriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Finforz Tech\\eclipse-workspace\\SeleniumFramework\\src\\main\\java\\resource\\Globalproperty.properties");
		prop.load(fis);
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
		WebDriver driver = new ChromeDriver();
		this.driver=driver;
		}
		else {
			
		}
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
		public LoginPage Launching() throws IOException {
			driver = Initializedriver();
			landing = new LoginPage(driver);
			driver.manage().window().maximize();
			return landing;
	}
	@AfterMethod(alwaysRun=true)
	public void close() {
		driver.quit();
	}
	public List<HashMap<String, String>> readdata(String filepath) throws IOException {
		//reading data and converting to string
		String jsondata = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		//String to HashMap - Jackson Databind
		//to convert, we must create ObjectMapper class
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> Datas = mapper.readValue(jsondata, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return Datas;
	}
	public String TakingScreenshot(String TestCasename, WebDriver driver) throws IOException {
		  if (driver == null) {
		        System.out.println("WebDriver is null, cannot take screenshot.");
		        return null;
		    }
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//Reports//"+TestCasename+".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+"//Reports//"+TestCasename+".png";
	}
	
}
