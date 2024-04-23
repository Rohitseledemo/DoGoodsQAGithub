package test;

import org.testng.Assert;
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
    @Test
    public void monthlyEarningsTest() {
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt("Activ");
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.compareDates();
        respectiveClientEarningsPage.totalSavingsCalculate();
    }
    @Test
    public void viewDetailsBtnValidation(){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt("Activ");
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.clickOnEarningsIcon(0);
        Assert.assertTrue(respectiveClientEarningsPage.viewDetailsBtnVerify());
    }
    @Test
    public void accountValidation(){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt("Azazie");
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.clickOnEarningsIcon();
        respectiveClientEarningsPage.changeAccountAndValidateTotalSavings();
    }
    @Test
    public void monthValidation(){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt("Azazie");
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.clickOnEarningsIcon();
        respectiveClientEarningsPage.changeMonthAndValidateTotalSavings();
    }
    @Test
    public void groundEntriesCountTest(){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt("Sel");
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.clickOnEarningsIcon();
        respectiveClientEarningsPage.averageSavingsPkgDataCardClick();
        respectiveClientEarningsPage.groundEntriesCount();
    }
    @Test(dataProvider = "getDatePickerData")
    public void generateEarningsBtnTest(String scenario, String dateValue, String yearValue){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        earningsPage.typeClientNameAndClickOnIt("Activ Post");
        respectiveClientEarningsPage = new RespectiveClientEarningsPage();
        respectiveClientEarningsPage.generateEarningsBtnClick();
        respectiveClientEarningsPage.datePickerHandler(dateValue,yearValue);

        if(scenario.equalsIgnoreCase("No Data found in given period testCase 1")){
              Assert.assertTrue(respectiveClientEarningsPage.generateEarningsNoDataFound());
        }
        else if(scenario.equalsIgnoreCase("regenerate pop-up testCase 2")){
              Assert.assertTrue(respectiveClientEarningsPage.generateEarningsRegeneratePopup());
        }
        else if (scenario.equalsIgnoreCase("Agreement date pop-up testCase 3")) {
              Assert.assertTrue(respectiveClientEarningsPage.generateEarningsAgreementDatePopup());
        }
        else if (scenario.equalsIgnoreCase("generating earnings testCase 4")) {
              Assert.assertTrue(respectiveClientEarningsPage.generateEarningsSuccessfully());
        }
    }

    @DataProvider
    public String[][] getDatePickerData() {
        String[][] data = new String[4][3];
        // 1st dataset
        data[0][0] = "No Data found in given period testCase 1";
        data[0][1] = "Dec";
        data[0][2] = "2024";
        // 2nd dataset
        data[1][0] = "regenerate pop-up testCase 2";
        data[1][1] = "Mar";
        data[1][2] = "2024";
        // 3rd dataset
        data[2][0] = "Agreement date pop-up testCase 3";
        data[2][1] = "Dec";
        data[2][2] = "2012";
        // 4th dataset
        data[3][0] = "generating earnings testCase 4";
        data[3][1] = "Mar";
        data[3][2] = "2024";

        return data;
    }

//    @AfterMethod
//    public void closeApplication() throws MalformedURLException {
//        loginPage.closeBrowser();
//    }

}
