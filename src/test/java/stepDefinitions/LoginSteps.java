package stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import java.net.MalformedURLException;

public class LoginSteps{
	LoginPage loginPage;
	
	@Given("I navigate to test URL")
	public void i_navigate_to_test_url() throws MalformedURLException {
		loginPage = new LoginPage();
		loginPage.launchNewBrowserInstance();
		loginPage.launchUrl("https://qa-admin.dogoodsinc.com/admin/");
	}
	@When("I enter the {string} address on login page")
	public void i_enter_the_address_on_login_page(String inputEmail) {
	    loginPage = new LoginPage();
	    loginPage.setEmailAddress(inputEmail);
	}

	@When("I enter the {string} on login page")
	public void i_enter_the_on_login_page(String inputPassword) {
		loginPage = new LoginPage();
	    loginPage.setPassword(inputPassword);
	}
	
	@Then("I close the test URL")
	public void I_close_the_test_URL() {
		loginPage = new LoginPage();
		loginPage.closeBrowser();
	}
}
