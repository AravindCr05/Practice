package POMclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.WaitingTime;

public class PlaceOrder extends WaitingTime {

	WebDriver driver;
	public PlaceOrder(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	By checkoutbutton = By.xpath("//a[@class='btnn action__submit ng-star-inserted']");
	
	@FindBy(xpath=".//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeorder;
	
	By Confirmationpage = By.cssSelector("td[class='content-wrap']");
	@FindBy(css="h1[class='hero-primary']")
	WebElement Confirmationtext;
	
	By dropdown = By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button");
	public void orderplacing() throws InterruptedException {
		Elementinvisible(dropdown);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,5000)");
		Visibilityof(placeorder);
		Thread.sleep(2000);
		Elementclickable(checkoutbutton);
		placeorder.click();
//		Actions act=new Actions(driver);
//		act.moveToElement(placeorder);
//		placeorder.click();
	}
	public String Confirmationmessage() {
		Elementvisible(Confirmationpage);
		String text = Confirmationtext.getText();
		return text;
	}
}
