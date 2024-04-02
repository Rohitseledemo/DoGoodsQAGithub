package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.net.MalformedURLException;

public class LoginPageTest {
    LoginPage loginPage;
    @Test(priority = 6)
    public void correctTest() throws MalformedURLException, InterruptedException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl("https://qa-admin.dogoodsinc.com/admin/");
        String inputEmail ="admin@dogoodsinc.com";
        loginPage.setEmailAddress(inputEmail);
        String inputPassword = "Admin@Shipplug2024!";
        loginPage.setPassword(inputPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.loginVerify("Hi Admin"));
        loginPage.closeBrowser();
    }
    @Test(priority = 1)
    public void wrongEmailTest() throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl("https://qa-admin.dogoodsinc.com/admin/");
        String inputEmail ="admin!dogoodsinc.com";
        loginPage.setEmailAddress(inputEmail);
        String inputPassword = "Admin@Shipplug2024!";
        loginPage.setPassword(inputPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Assert.assertTrue(loginPage.wrongEmailErrorDisplayed("Please enter valid email."));
        loginPage.closeBrowser();
    }
    @Test(priority = 2)
    public void wrongPasswordTest() throws MalformedURLException, InterruptedException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl("https://qa-admin.dogoodsinc.com/admin/");
        String inputEmail ="admin@dogoodsinc.com";
        loginPage.setEmailAddress(inputEmail);
        String inputPassword = "Admin!Shipplug2024!";
        loginPage.setPassword(inputPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.wrongPasswordErrorDisplayed
                (" Invalid Email address or password. Please try again."));
        loginPage.closeBrowser();
    }
    @Test(priority = 3)
    public void emptyEmailTest() throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl("https://qa-admin.dogoodsinc.com/admin/");
        String inputPassword = "Admin@Shipplug2024!";
        loginPage.setPassword(inputPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Assert.assertTrue(loginPage.emptyEmailErrorDisplayed("Please provide your email."));
        loginPage.closeBrowser();
    }
    @Test(priority = 4)
    public void emptyPasswordTest() throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl("https://qa-admin.dogoodsinc.com/admin/");
        String inputEmail ="admin@dogoodsinc.com";
        loginPage.setEmailAddress(inputEmail);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        Assert.assertTrue(loginPage.emptyPasswordErrorDisplayed("Please provide password."));
        loginPage.closeBrowser();
    }
    @Test(priority = 5)
    public void verifyForgotPasswordPage() throws InterruptedException, MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl("https://qa-admin.dogoodsinc.com/admin/");
        loginPage.verifyForgetPasswordClick();
        Thread.sleep(2000);
        WebElement forgotPassText = loginPage.getBrowser().findElement
                (By.xpath("//h3[@class='font-weight-bold']"));
        Assert.assertEquals("Forgot Password : ShipPlug",forgotPassText.getText());
        loginPage.closeBrowser();
    }

}
