package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import POMclasses.AddToCart;
import POMclasses.CartPage;
import POMclasses.CheckOutpage;
import POMclasses.PlaceOrder;
import POMclasses.LoginPage;
import Testcomponents.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Orderplacing extends BaseClass {
	public AddToCart cart;
	public CartPage cartpage;
	public CheckOutpage checkout;
	public PlaceOrder placeorder;

	@Given("I launch the application")
	public void I_launch_the_application() throws IOException {
		landing = Launching();
	}

	@Given("^Logged into application using (.+) and (.+)$")
	public void Login(String username, String password) {
		cart = landing.login(username, password);
	}

	@When("^I add the product(.+) to the cart$")
	public void ProductToCart(String ProductName) {
		List<WebElement> Products = cart.AllProducts();
		cartpage = cart.Addingtocart(ProductName);
		cartpage.Cartpagenavigation();
		Boolean cart1 = cartpage.ProductCheck(ProductName);
		Assert.assertTrue(cart1);
	}

	@When("I checkout and submit the order")
	public void ChekoutandOrderSubmit() throws InterruptedException {
		checkout = cartpage.checkout();
		checkout.checkout();
		placeorder = checkout.Countryselection();
		placeorder.orderplacing();

	}
	@Then("{string} message is displayed")
	public void Confirmationpage(String message) {
		String text= placeorder.Confirmationmessage();
		Assert.assertTrue(text.equalsIgnoreCase(message));
	}
	@Then("{string} message is displayed in LoginPage")
	public void LoginError(String message1) {
		Assert.assertEquals(landing.ErrorMessage(), message1);
	}
}
