package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.net.MalformedURLException;

public class LoginPageTest {
    LoginPage loginPage;
    @Parameters({"URL","Email","Password"})
    @Test(priority = 6)
    public void correctTest(String URL,String Email, String Password) throws MalformedURLException, InterruptedException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setEmailAddress(Email);
        loginPage.setPassword(Password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.loginVerify("Hi Admin"));
        loginPage.closeBrowser();
    }
    @Parameters({"URL","WrongEmail","Password"})
    @Test(priority = 1)
    public void wrongEmailTest(String URL, String WrongEmail, String Password) throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setEmailAddress(WrongEmail);
        loginPage.setPassword(Password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Assert.assertTrue(loginPage.wrongEmailErrorDisplayed("Please enter valid email."));
        loginPage.closeBrowser();
    }
    @Parameters({"URL","Email","WrongPassword"})
    @Test(priority = 2)
    public void wrongPasswordTest(String URL, String Email, String WrongPassword) throws MalformedURLException, InterruptedException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setEmailAddress(Email);
        loginPage.setPassword(WrongPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.wrongPasswordErrorDisplayed
                (" Invalid Email address or password. Please try again."));
        loginPage.closeBrowser();
    }
    @Parameters({"URL","Password"})
    @Test(priority = 3)
    public void emptyEmailTest(String URL, String Password) throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setPassword(Password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Assert.assertTrue(loginPage.emptyEmailErrorDisplayed("Please provide your email."));
        loginPage.closeBrowser();
    }
    @Parameters({"URL","Email"})
    @Test(priority = 4)
    public void emptyPasswordTest(String URL, String Email) throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setEmailAddress(Email);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Assert.assertTrue(loginPage.emptyPasswordErrorDisplayed("Please provide password."));
        loginPage.closeBrowser();
    }
    @Parameters({"URL"})
    @Test(priority = 5)
    public void verifyForgotPassPageInvoking(String URL) throws InterruptedException, MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.verifyForgetPasswordClick();
        Thread.sleep(2000);
        WebElement forgotPassText = loginPage.getBrowser().findElement
                (By.xpath("//h3[@class='font-weight-bold']"));
        Assert.assertEquals("Forgot Password : ShipPlug",forgotPassText.getText());
        loginPage.closeBrowser();
    }

}
