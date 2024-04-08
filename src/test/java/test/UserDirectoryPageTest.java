package test;

import org.testng.annotations.*;
import pages.CustomerPage;
import pages.DashboardPage;
import pages.EarningsPage;
import pages.LoginPage;

import java.net.MalformedURLException;

public class UserDirectoryPageTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;
    EarningsPage earningsPage;
    String url, email, password;
    @BeforeTest
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
    public void accessTest(String testEmail, String testPassword){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(testEmail);
        loginPage.setPassword(testPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnCustomer();
        customerPage=new CustomerPage();
        customerPage.searchClientInList();
        dashboardPage.clickOnEarnings();
        earningsPage =  new EarningsPage();
        earningsPage.searchClientsInList();
    }
    @DataProvider
    public String[][] getTestData() {
        String[][] data = new String[1][2];
        // 1st dataset
        data[0][0] = "test@dogoodsinc.com";
        data[0][1] = "Test@418";
        return data;
    }

    @AfterTest
    public void closeApplication() throws MalformedURLException {
        loginPage.closeBrowser();
    }
}
