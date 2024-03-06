package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base_Library.Base_Class;

public class Login_Page extends Base_Class{

	public Login_Page() {
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath = "//input[@id='email_addtess']")
	private WebElement emailaddres;
	@FindBy(xpath = "//input[@id='login_pass']")
	private WebElement password;
	@FindBy(xpath = "//*[text()='Sign In']")
	private WebElement signin;
	
	public void loginToApplication() 
	{
		emailaddres.sendKeys("admin@dogoodsinc.com");
		password.sendKeys("Dev@Admin2023!");
		signin.click();
	}
}
