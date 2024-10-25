package POMclasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.WaitingTime;

public class OrderPage extends WaitingTime {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//tr/td[2]")
	List<WebElement> OrderProduct;
	
	public Boolean Productverification(String ProductName1) {
		Boolean productmatch = OrderProduct.stream().anyMatch(p->p.getText().equals(ProductName1));
		return productmatch;

	}
	
}
