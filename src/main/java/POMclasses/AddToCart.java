package POMclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.WaitingTime;

public class AddToCart extends WaitingTime {
	WebDriver driver;

	public AddToCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// for initializing & constructing during the runtime for finding the
												// locator
	}

	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")
	List<WebElement> Products;

	By add = By.xpath(".//button[contains(text(),'Add To Cart')]");
	
	By products = By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']");

	By toast = By.id("toast-container");
	By loadicon = By.cssSelector(".ng-animating");
	
	@FindBy (id="toast-container")
	WebElement text;
	
	
	public List<WebElement> AllProducts() {
		Elementvisible(products);
		return Products;
	}

	public WebElement Productbyname(String ProductName1) {
		WebElement prod = Products.stream().filter(p -> p.findElement(By.xpath(".//div/div/h5/b")).getText().equals(ProductName1))
				.findFirst().orElse(null);
		if(prod==null) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,2000)");
			prod = Products.stream().filter(p -> p.findElement(By.xpath(".//div/div/h5/b")).getText().equals(ProductName1))
					.findFirst().orElse(null);
		}
		System.out.println(prod.getSize());
		return prod;
	}

	public CartPage Addingtocart(String ProductName1) {
		WebElement prod = Productbyname(ProductName1);
		prod.findElement(add).click();
		Elementvisible(toast);
		Elementinvisible(loadicon);
		System.out.println(text.getText());
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	

}
