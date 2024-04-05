package pages;

import org.openqa.selenium.By;

public class ForgotPasswordPage extends BasePage {
    By emailForgot,returnToLogin,sendEmail,resetPasswordLink;
    public ForgotPasswordPage(){
        this.emailForgot= By.xpath("//input[@id='email_address']");
        this.returnToLogin=By.xpath("//a[@class='text-secondary text-decoration-none font-weight-bold fget']");
        this.sendEmail=By.xpath("//button[@type='submit']");
        this.resetPasswordLink=By.xpath("//span[@class='text-success']");
    }
    public void setEmailForgot(String inputEmail){

        this.getBrowser().findElement(emailForgot).sendKeys(inputEmail);
    }
    public void sendEmailClick(){

        this.getBrowser().findElement(sendEmail).click();
    }
    public void returnToLoginClick()
    {
    	this.getBrowser().findElement(returnToLogin).click();
    }

}
