package pages;
import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    By emailAddress, password,signIn,emailError,rememberMe,forgetPassword,passwordError,loginError,enterPasswordError;
    public LoginPage(){
        this.emailAddress = By.xpath("//input[@id='email_addtess']");
        this.password = By.xpath("//input[@id='login_pass']");
        this.signIn=By.xpath("//*[text()='Sign In']");
        this.emailError=By.xpath("//div[@id='login_error']");
        this.rememberMe=By.xpath("//input[@id='gridCheck']");
        this.forgetPassword= By.xpath("//a[@class='text-secondary text-decoration-none font-weight-bold fget']");
        this.passwordError=By.xpath("//span[@class='text-danger']");
        this.loginError=By.id("login_error");
        this.enterPasswordError=By.id("pass_error");
    }
    public void setEmailAddress(String inputEmail){
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
    public void ForgetPasswordClick(){
    	this.getBrowser().findElement(forgetPassword).click();
    }



}
