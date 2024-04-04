package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import java.net.MalformedURLException;
public class LoginPageTest {
    LoginPage loginPage;
    @Parameters({"URL"})
    @BeforeTest
    public void launchBrowser(String URL) throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
    }
    @Parameters({"URL","Email","Password"})
    @Test
    public void correctTest(String URL,String Email, String Password) throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setEmailAddress(Email);
        loginPage.setPassword(Password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        loginPage.loginVerify();
        loginPage.closeBrowser();
    }
//    @Test(dataProvider = "getData")
//    public void negativeTest(String URL, String email, String password) throws MalformedURLException {
//        loginPage = new LoginPage();
//        loginPage.launchNewBrowserInstance();
//        loginPage.launchUrl(URL);
//        loginPage.setEmailAddress(email);
//        loginPage.setPassword(password);
//        loginPage.rememberMeClick();
//        loginPage.signInClick();
//        loginPage.wrongEmailErrorDisplayed();
//        loginPage.wrongPasswordErrorDisplayed();
//        loginPage.emptyEmailErrorDisplayed();
//        loginPage.emptyPasswordErrorDisplayed();
//        loginPage.closeBrowser();
//    }
    @DataProvider
    public String[][] getData(){
        //4 different data sets of username and password so,
        String[][] data = new String[4][3];
        //1st dataset
        data[0][0]= "https://qa-admin.dogoodsinc.com/admin/";
        data[0][1]= "admin!dogoodsinc.com";
        data[0][2]= "Admin@Shipplug2024!";
        //2nd dataset
        data[1][0]= "https://qa-admin.dogoodsinc.com/admin/";
        data[1][1]= "admin@dogoodsinc.com";
        data[1][2]= "Admin!Shipplug2024!";
        //3rd dataset
        data[2][0]="https://qa-admin.dogoodsinc.com/admin/";
        data[2][1]="";
        data[2][2]= "Admin@Shipplug2024!";
        //4th dataset
        data[3][0]="https://qa-admin.dogoodsinc.com/admin/";
        data[3][1]="admin@dogoodsinc.com";
        data[3][2]= "";
        return data;
    }
    @Parameters({"URL","WrongEmail","Password"})
    @Test
    public void wrongEmailTest(String URL, String wrongEmail, String password) throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setEmailAddress(wrongEmail);
        loginPage.setPassword(password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        loginPage.wrongEmailErrorDisplayed();
        loginPage.closeBrowser();
    }
    @Parameters({"URL","Email","WrongPassword"})
    @Test
    public void wrongPasswordTest(String URL, String Email, String WrongPassword) throws MalformedURLException, InterruptedException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setEmailAddress(Email);
        loginPage.setPassword(WrongPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        loginPage.wrongPasswordErrorDisplayed();
        loginPage.closeBrowser();
    }
    @Parameters({"URL","Password"})
    @Test
    public void emptyEmailTest(String URL, String Password) throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setPassword(Password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        loginPage.emptyEmailErrorDisplayed();
        loginPage.closeBrowser();
    }
    @Parameters({"URL","Email"})
    @Test
    public void emptyPasswordTest(String URL, String Email) throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setEmailAddress(Email);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        loginPage.emptyPasswordErrorDisplayed();
        loginPage.closeBrowser();
    }
    @Parameters({"URL"})
    @Test
    public void verifyForgotPassPageInvoking(String URL) throws InterruptedException, MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.verifyForgetPasswordClick();
        loginPage.closeBrowser();
    }
    @AfterTest
    public void closeApplication() throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.closeBrowser();
    }

}
