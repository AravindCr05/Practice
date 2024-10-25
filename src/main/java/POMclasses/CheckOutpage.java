package POMclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.WaitingTime;

public class CheckOutpage extends WaitingTime {
	WebDriver driver;

	public CheckOutpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}


	@FindBy(xpath = "//div[@class='form-group']/input[@class='input txt text-validated']")
	WebElement country;

	By dropdown = By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button");

	@FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> CountryList;

	public List<WebElement> checkout() {
		country.sendKeys("Ind");
		Elementvisible(dropdown);
		return CountryList;
	}

	public PlaceOrder Countryselection() {
		// List<WebElement> CountryList = checkout();
		for (WebElement country1 : CountryList) {
			String countryname = country1.getText();
			System.out.println(countryname);
			if (countryname.equals("India")) {
				country1.click();
				break;
				
				
			}
		}
		PlaceOrder placeorder = new PlaceOrder(driver);
		return placeorder;
	}
}
