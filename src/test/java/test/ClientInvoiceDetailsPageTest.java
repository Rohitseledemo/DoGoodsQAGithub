package test;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.net.MalformedURLException;

public class ClientInvoiceDetailsPageTest {
    String url, email, password;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    EarningsPage earningsPage;
    RespectiveClientEarningsPage respectiveClientEarningsPage;
    ClientInvoiceDetailsPage clientInvoiceDetailsPage;

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


    @Test
    public void monthlyEarningsTest() {
        boolean finalResult;
        dashboardPage = new DashboardPage();
        earningsPage = new EarningsPage();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        clientInvoiceDetailsPage = new ClientInvoiceDetailsPage();

        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage.clickOnEarnings();
        earningsPage.typeClientNameAndClickOnIt("Activ");
        respectiveClientEarningsPage.compareDates();
        finalResult = clientInvoiceDetailsPage.totalSavingsCalculate();
        Assert.assertTrue(finalResult);
    }

    @Test
    public void viewDetailsBtnValidation(){
        dashboardPage = new DashboardPage();
        earningsPage = new EarningsPage();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        clientInvoiceDetailsPage = new ClientInvoiceDetailsPage();

        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage.clickOnEarnings();
        earningsPage.typeClientNameAndClickOnIt("Activ");
        respectiveClientEarningsPage.clickOnEarningsIcon(0);
        Assert.assertTrue(clientInvoiceDetailsPage.viewDetailsBtnVerify());
    }

    @Test
    public void accountValidation(){
        boolean result;
        dashboardPage = new DashboardPage();
        earningsPage = new EarningsPage();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        clientInvoiceDetailsPage = new ClientInvoiceDetailsPage();

        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage.clickOnEarnings();
        earningsPage.typeClientNameAndClickOnIt("Azazie");
        respectiveClientEarningsPage.clickOnEarningsIcon();
        result = clientInvoiceDetailsPage.changeAccountAndValidateTotalSavings();
        Assert.assertTrue(result);
    }
    @Test
    public void monthValidation(){
        boolean result;
        dashboardPage = new DashboardPage();
        earningsPage = new EarningsPage();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        clientInvoiceDetailsPage = new ClientInvoiceDetailsPage();

        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage.clickOnEarnings();
        earningsPage.typeClientNameAndClickOnIt("Azazie");
        respectiveClientEarningsPage.clickOnEarningsIcon();
        result = clientInvoiceDetailsPage.changeMonthAndValidateTotalSavings();
        Assert.assertTrue(result);
    }

    @Test
    public void groundEntriesCountTest() throws InterruptedException {
        earningsPage = new EarningsPage();
        dashboardPage = new DashboardPage();
        dashboardPage = new DashboardPage();
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        clientInvoiceDetailsPage = new ClientInvoiceDetailsPage();

        int index;
        String clientClickedName;
        String expectedCount;
        String actualCount;
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage.clickOnEarnings();
        respectiveClientEarningsPage.verifyClientName();
        earningsPage.typeClientNameAndClickOnIt("Selenium Testing");
        respectiveClientEarningsPage.clickOnEarningsIcon();
        clientInvoiceDetailsPage.averageSavingsPkgDataCardClick();
        clientClickedName =clientInvoiceDetailsPage.clickOnService("Ground");
        index =clientInvoiceDetailsPage.getServiceIndexByName(clientClickedName);
        expectedCount = clientInvoiceDetailsPage.getServiceCount(index);
        actualCount= clientInvoiceDetailsPage.groundEntriesCount();
        Assert.assertEquals(actualCount,expectedCount);
    }

    @Test
    public void accountNumberBtnValidation(){
       loginPage = new LoginPage();
       dashboardPage = new DashboardPage();
       earningsPage = new EarningsPage();
       respectiveClientEarningsPage = new RespectiveClientEarningsPage();
       clientInvoiceDetailsPage = new ClientInvoiceDetailsPage();

       loginPage.launchUrl(this.url);
       loginPage.setEmailAddress(this.email);
       loginPage.setPassword(this.password);
       loginPage.rememberMeClick();
       loginPage.signInClick();
       dashboardPage.clickOnEarnings();
       earningsPage.typeClientNameAndClickOnIt("Selenium Testing");
       respectiveClientEarningsPage.clickOnAccountNumberBtnOnRight();
       int names = respectiveClientEarningsPage.countAccountBtnAccountNames();
       respectiveClientEarningsPage.clickInvoiceByAccountCheckbox();
       respectiveClientEarningsPage.clickAccountBtnSubmitBtn();
       respectiveClientEarningsPage.clickOnEarningsIcon();
       clientInvoiceDetailsPage.doGoodsInvoiceLinkClick();
       int accountCount = clientInvoiceDetailsPage.getAccountNumberColumnCount();
       Assert.assertEquals(names,accountCount);

    }


    @AfterMethod
    public void closeApplication() throws MalformedURLException {
        loginPage.closeBrowser();
    }

}
