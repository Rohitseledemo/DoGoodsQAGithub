package test;

import org.testng.annotations.*;
import pages.*;

import java.net.MalformedURLException;

public class RespectiveClientEarningsPageTest {
    String url, email, password;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    EarningsPage earningsPage;
    RespectiveClientEarningsPage respectiveClientEarningsPage;

    @BeforeMethod
    @Parameters({"URL" })
    public void launchBrowser(@Optional("https://qa-admin.dogoodsinc.com/admin/")String Url)
            throws MalformedURLException {

        this.url = Url;
        this.email = email;
        this.password = password;

        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
    }
    @Test(dataProvider = "getTestData")
    public void monthlyEarningsTest(String testEmail, String testPassword){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(testEmail);
        loginPage.setPassword(testPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.compareDates();
        respectiveClientEarningsPage.totalSavingsCalculate();
    }
    @Test(dataProvider = "getTestData")
    public void accountValidation(String testEmail, String testPassword){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(testEmail);
        loginPage.setPassword(testPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.clickOnEarningsIcon();
        respectiveClientEarningsPage.changeAccountAndValidateTotalSavings();
    }
    @Test(dataProvider = "getTestData")
    public void monthValidation(String testEmail, String testPassword){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(testEmail);
        loginPage.setPassword(testPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.clickOnEarningsIcon();
        respectiveClientEarningsPage.changeMonthAndValidateTotalSavings();
    }

    @DataProvider
    public String[][] getTestData() {
        String[][] data = new String[1][2];
        // 1st dataset
        data[0][0] = "Testqa@dogoodsinc.com";
        data[0][1] = "Test@418";
        return data;
    }

    @AfterMethod
    public void closeApplication() throws MalformedURLException {
        loginPage.closeBrowser();
    }
}
