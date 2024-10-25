package Frameworks;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import POMclasses.AddToCart;
import POMclasses.CartPage;
import POMclasses.CheckOutpage;
import POMclasses.LoginPage;
import POMclasses.OrderPage1;
import POMclasses.PlaceOrder;
import Testcomponents.BaseClass;

public class POM extends BaseClass{
	//String ProductName = "IPHONE 13 PRO";
	@Test(dataProvider="data")
	 public void OrderPlacement(HashMap<String,String> hashmap) throws IOException, InterruptedException{
		//WebDriver driver = new ChromeDriver();
		// TODO Auto-generated method stub
		//LoginPage landing=Launching();
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AddToCart cart = landing.login(hashmap.get("email"), hashmap.get("password"));
		List<WebElement> Products = cart.AllProducts();
		//cart.Addingtocart(ProductName);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")));
		//System.out.println(product.get(1));
		//WebElement prod = product.stream().filter(p->p.findElement(By.xpath("//div/div/h5/b")).getText().equals(ProductName)).findFirst().orElse(null);
		CartPage cartpage =cart.Addingtocart(hashmap.get("ProductName"));
		cartpage.Cartpagenavigation();
		Boolean cart1 = cartpage.ProductCheck(hashmap.get("ProductName"));
		Assert.assertTrue(cart1);
		CheckOutpage checkout = cartpage.checkout();
//		if (autosuggest.isEmpty()) {
//		    System.out.println("No suggestions found.");
//		} else {
//		    System.out.println("Suggestions found: " + autosuggest.size());
//		}
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button")));
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,200)");
	    //autosuggest.stream().filter(a->a.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")).getText().equals("India"));
        checkout.checkout();
        PlaceOrder placeorder = checkout.Countryselection();
		placeorder.orderplacing();
		String text= placeorder.Confirmationmessage();
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
	}
	@Test(dependsOnMethods="OrderPlacement", dataProvider="data")
	public void OrderPage(HashMap<String, String> hashmap) throws InterruptedException {
		AddToCart cart = landing.login(hashmap.get("email"), hashmap.get("password"));
		//Thread.sleep(1000);
		OrderPage1 orderpage1 = cart.Orderpagenavigation();
		//Thread.sleep(2000);
		Assert.assertTrue(orderpage1.OrderpageProduct(hashmap.get("ProductName")));
	}
	//Datafeeding through 2D-Array
//	@DataProvider
//	public Object[][] data() {
//		return new Object[][] {{"ramesharavind96@gmail.com","Test@123","IPHONE 13 PRO"}, {"shetty@gmail.com","Iamking@000","ZARA COAT 3"}};
//	}
	//Datafeeding through Hashmap
	@DataProvider
	public Object[][] data() throws IOException {
//		HashMap<Object,Object> hash = new HashMap<Object,Object>();
//		hash.put("email", "ramesharavind96@gmail.com");
//		hash.put("password", "Test@123");
//		hash.put("ProductName", "IPHONE 13 PRO");
//		
//		HashMap<Object,Object> hash1 = new HashMap<Object,Object>();
//		hash1.put("email", "shetty@gmail.com");
//		hash1.put("password", "Iamking@000");
//		hash1.put("ProductName", "ZARA COAT 3");
		List<HashMap<String, String>> data =  readdata(System.getProperty("user.dir")+"\\src\\test\\java\\data\\DataReader.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
}
