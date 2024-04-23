package pages;
import org.openqa.selenium.By;
import org.testng.Assert;
import utility.BrowserKeeper;

public class LoginPage extends BasePage{
    By emailAddress;
    By password;
    By signIn;
    By emailError;
    By rememberMe;
    By forgetPassword;
    By passwordError;
    By emptyEmailError;
    By emptyPasswordError;
    By loginSuccessCheck;
    public LoginPage(){
        this.emailAddress = By.xpath("//input[@id='email_addtess']");
        this.password = By.xpath("//input[@id='login_pass']");
        this.signIn=By.xpath("//*[text()='Sign In']");
        this.emailError=By.xpath("//div[@id='login_error']");
        this.rememberMe=By.xpath("//input[@id='gridCheck']");
        this.forgetPassword= By.xpath("//a[@class='text-secondary text-decoration-none font-weight-bold fget']");
        this.passwordError=By.xpath("//span[@class='text-danger']");
        this.emptyEmailError=By.id("login_error");
        this.emptyPasswordError=By.id("pass_error");
        this.loginSuccessCheck=By.xpath("//div[@class='card-body']");
    }
    public void setEmailAddress(String inputEmail){
        this.driver.waitForPresenceOfElement(4,emailAddress);
    	this.getBrowser().findElement(emailAddress).sendKeys(inputEmail);
    }
    public void setPassword(String inputPassword){
        this.getBrowser().findElement(password).sendKeys(inputPassword);
    }
    public void signInClick(){
    	this.getBrowser().findElement(signIn).click();
    }
    public void rememberMeClick(){
    	this.getBrowser().findElement(rememberMe).click();
    }
    public void verifyForgetPasswordClick(){
        this.driver.waitForPresenceOfElement(2,forgetPassword);
        this.getBrowser().findElement(forgetPassword).click();
    }
    public boolean wrongEmailErrorDisplayed(){
        this.driver.waitForPresenceOfElement(2,loginSuccessCheck);
        return this.getBrowser().findElement(emailError).isDisplayed();
    }
    public boolean wrongPasswordErrorDisplayed(){
        this.driver.waitForPresenceOfElement(2,passwordError);
        return this.getBrowser().findElement(passwordError).isDisplayed();
    }
    public boolean emptyPasswordErrorDisplayed(){
        this.driver.waitForPresenceOfElement(2,emptyPasswordError);
        return this.getBrowser().findElement(emptyPasswordError).isDisplayed();
    }
    public boolean emptyEmailErrorDisplayed(){
        this.driver.waitForPresenceOfElement(2,emptyEmailError);
        return this.getBrowser().findElement(emptyEmailError).isDisplayed();
    }
    public boolean loginVerify(){
        this.driver.waitForPresenceOfElement(2,loginSuccessCheck);
        this.getBrowser().findElement(loginSuccessCheck).click();
        return this.getBrowser().findElement(loginSuccessCheck).isDisplayed();
    }



}
