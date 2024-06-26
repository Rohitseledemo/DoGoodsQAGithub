package doGoodsQaTest;

import org.testng.Assert;
import org.testng.annotations.*;
import doGoodsQaPages.*;

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
        loginPage.launchNewBrowserInstance();// getting error here on remote web browser
    }


    @Test
    public void monthlyEarningsTest() throws InterruptedException {
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
        earningsPage.filterByClientName("Activ");
        earningsPage.filteredClientClick();
        respectiveClientEarningsPage.compareDates();
        finalResult = clientInvoiceDetailsPage.totalSavingsCalculate();
        Assert.assertTrue(finalResult);
    }

    @Test
    public void viewDetailsBtnValidation() throws InterruptedException {
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
        earningsPage.filterByClientName("Activ");
        earningsPage.filteredClientClick();
        respectiveClientEarningsPage.clickOnEarningsIcon(0);
        Assert.assertTrue(clientInvoiceDetailsPage.viewDetailsBtnVerify());
    }

    @Test
    public void accountValidation() throws InterruptedException {
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
        earningsPage.filterByClientName("Selenium Testing");
        earningsPage.filteredClientClick();
        respectiveClientEarningsPage.clickOnEarningsIcon();
        clientInvoiceDetailsPage.changeAccountsByDropDown();
        result = clientInvoiceDetailsPage.totalSavingsCalculate();
        Assert.assertTrue(result);
    }
    @Test
    public void monthValidation() throws InterruptedException {
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
        earningsPage.filterByClientName("Azazie");
        earningsPage.filteredClientClick();
        respectiveClientEarningsPage.clickOnEarningsIcon();
        clientInvoiceDetailsPage.changeMonthsByDropDown();
        result = clientInvoiceDetailsPage.totalSavingsCalculate();
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
        earningsPage.filterByClientName("Selenium Testing");
        earningsPage.filteredClientClick();
        respectiveClientEarningsPage.clickOnEarningsIcon();
        clientInvoiceDetailsPage.averageSavingsPkgDataCardClick();
        clientClickedName =clientInvoiceDetailsPage.clickOnService("Ground");
        index =clientInvoiceDetailsPage.getServiceIndexByName(clientClickedName);
        expectedCount = clientInvoiceDetailsPage.getServiceCount(index);
        actualCount= clientInvoiceDetailsPage.groundEntriesCount();
        Assert.assertEquals(actualCount,expectedCount);
    }

    @Test
    public void accountNumberBtnValidation() throws InterruptedException {
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
        earningsPage.filterByClientName("Selenium Testing");
        earningsPage.filteredClientClick();
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
