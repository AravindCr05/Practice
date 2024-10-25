package POMclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.WaitingTime;

public class LoginPage extends WaitingTime{
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);//for initializing & constructing during the runtime for finding the locator
	}
	@FindBy(id="userEmail")
	WebElement Email;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement Click;
	
	@FindBy(xpath="//div[contains(@class,'toast-message')]")
	WebElement error;
	
	public AddToCart login(String Emailid, String Pass) {
		driver.get("https://rahulshettyacademy.com/client");
		Email.sendKeys(Emailid);
		Password.sendKeys(Pass);
		Click.click();
		AddToCart cart = new AddToCart(driver);
		return cart;
	}
	public String ErrorMessage() {
		WebElementvisible(error);
		System.out.println(error.getText());
		return error.getText();
	}
}
