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
    @Parameters({"Email","Password","URL" })
    public void launchBrowser(@Optional("admin@dogoodsinc.com") String email,
                              @Optional("Admin@Shipplug2024!")String password,
                              @Optional("https://qa-admin.dogoodsinc.com/admin/")String Url)
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
        respectiveClientEarningsPage.viewDetailsBtnVerify();
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
   // @Test
    public void groundEntriesCountTest(){
          loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt2();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.clickOnEarningsIcon();
        respectiveClientEarningsPage.averageSavingsPkgDataCardClick();
        System.out.println(respectiveClientEarningsPage.groundEntriesCount());
    }
//    @Test
    public void generateEarningsBtnTest(){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt2();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.generateEarningsValidate(1);
        respectiveClientEarningsPage.generateEarningsValidate(2);
        respectiveClientEarningsPage.generateEarningsValidate(3);
        respectiveClientEarningsPage.generateEarningsValidate(4);
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
