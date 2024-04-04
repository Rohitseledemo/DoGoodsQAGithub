package pages;
import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    By emailAddress, password,signIn,emailError,rememberMe,forgetPassword,
            passwordError,emptyEmailError,emptyPasswordError,loginSuccessCheck;
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
        this.loginSuccessCheck=By.xpath("//span[@class='d-inline-block text-left mr-2']");
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
    public void verifyForgetPasswordClick(){

        this.getBrowser().findElement(forgetPassword).click();
    }
    public boolean wrongEmailErrorDisplayed(String wrongEmailString){
        return this.getBrowser().findElement(emailError).isDisplayed();
    }
    public boolean wrongPasswordErrorDisplayed(String wrongPasswordString){
        return this.getBrowser().findElement(passwordError).isDisplayed();
    }
    public boolean emptyPasswordErrorDisplayed(String emptyPasswordString){
        return this.getBrowser().findElement(emptyPasswordError).isDisplayed();
    }
    public boolean emptyEmailErrorDisplayed(String emptyEmailString){
        return this.getBrowser().findElement(emptyEmailError).isDisplayed();
    }
    public boolean loginVerify(String adminName){
        return this.getBrowser().findElement(loginSuccessCheck).isDisplayed();
    }



}
