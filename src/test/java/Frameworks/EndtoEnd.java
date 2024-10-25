package Frameworks;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EndtoEnd {

	public static void main(String[] args) throws InterruptedException {
		
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		String ProductName = "IPHONE 13 PRO";
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("ramesharavind96@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")));
		List<WebElement> product = driver.findElements(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")));
		//System.out.println(product.get(1));
		//WebElement prod = product.stream().filter(p->p.findElement(By.xpath("//div/div/h5/b")).getText().equals(ProductName)).findFirst().orElse(null);
		WebElement prod = product.stream()
                .filter(p -> p.findElement(By.xpath(".//b")).getText().equals(ProductName))
                .findFirst().orElse(null);
		System.out.println(prod.getSize());
		prod.findElement(By.xpath(".//button[contains(text(),'Add To Cart')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		System.out.println(driver.findElement(By.id("toast-container")).getText());
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-profile[@class='ng-star-inserted']")));
		List<WebElement> CartProduct = driver.findElements(By.xpath("//div[@class='cart']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cartSection']/h3")));
		Boolean cart = CartProduct.stream().anyMatch(c->c.findElement(By.xpath("//div[@class='cartSection']/h3")).getText().equals(ProductName));
		Assert.assertTrue(cart);
		driver.findElement(By.xpath("//ul[@style='list-style-type: none;']/li[3]/button")).click();
		driver.findElement(By.xpath("//div[@class='form-group']/input[@class='input txt text-validated']")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button")));
		List<WebElement> autosuggest = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
//		if (autosuggest.isEmpty()) {
//		    System.out.println("No suggestions found.");
//		} else {
//		    System.out.println("Suggestions found: " + autosuggest.size());
//		}
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button")));
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,200)");
	    //autosuggest.stream().filter(a->a.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")).getText().equals("India"));
		for(WebElement auto:autosuggest) {
			String countryname = auto.getText();
			System.out.println(countryname);
			if(countryname.equals("India")) {
	    auto.click();
	    break;
		}
		}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1700)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")));
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[class='content-wrap']")));
		String text = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
	}

	}
