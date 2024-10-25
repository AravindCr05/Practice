package POMclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.WaitingTime;

public class OrderPage1 extends WaitingTime{
WebDriver driver;
	public OrderPage1(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> Orderproduct1;

	
	public Boolean OrderpageProduct(String ProductName1) {
	ListWebElementvisible(Orderproduct1);	
	Boolean productmatch1 = Orderproduct1.stream().anyMatch(c->c.getText().equals(ProductName1));
	return productmatch1;
	}
}
