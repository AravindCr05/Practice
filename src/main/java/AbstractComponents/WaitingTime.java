package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import POMclasses.OrderPage;
import POMclasses.OrderPage1;

public class WaitingTime {

	WebDriver driver;
	public WaitingTime(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//i[@class = 'fa fa-handshake-o']/parent::button")
	WebElement Orderpage;
	public void Elementvisible(By findby) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
		
	}
	public void WebElementvisible(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	public void ListWebElementvisible(List<WebElement> findby) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(findby));
	}
	public void Elementinvisible(By findsby) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findsby));
	}
	public void Elementclickable(By click) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(click));
	}
	public void Visibilityof(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public OrderPage1 Orderpagenavigation() {
		WebElementvisible(Orderpage);
		Orderpage.click();
		OrderPage1 orderpage1 = new OrderPage1(driver);
		return orderpage1;
		
	}
}
