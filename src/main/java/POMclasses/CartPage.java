package POMclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.WaitingTime;

public class CartPage extends WaitingTime{
WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartnav;
	
	By productlist = By.xpath("//app-profile[@class='ng-star-inserted']");
	
	@FindBy(xpath = "//div[@class='cart']")
	List<WebElement> ProductList;
	
	By productnamevisible = By.xpath("//div[@class='cartSection']/h3");
	
	By Productmatch =By.xpath("//div[@class='cartSection']/h3");
	
	@FindBy(xpath = "//ul[@style='list-style-type: none;']/li[3]/button")
	WebElement checkout;

	public void Cartpagenavigation() {
		cartnav.click();
		//Elementinvisible(productlist);
	}
	public List<WebElement> ProductList() {
		Elementvisible(productnamevisible);
		return ProductList;
		
	}
	public Boolean ProductCheck(String ProductName1) {
	List<WebElement> ProductList = ProductList();
	Boolean cart = ProductList.stream().anyMatch(c->c.findElement(Productmatch).getText().equals(ProductName1));
	return cart;
	}
	public CheckOutpage checkout() {
		checkout.click();
		CheckOutpage checkout = new CheckOutpage(driver);
		return checkout;
	}
}
