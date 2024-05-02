package DemoQATest;

import DemoQAPages.DemoQADashboardPage;
import DemoQAPages.DemoQALoginPage;
import DemoQAPages.DemoQAPriorAgreementComparison;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;

public class QADemoDoGoodTest {
    String url, email, password;
    DemoQALoginPage demoQALoginPage;
    DemoQAPriorAgreementComparison demoQAPriorAgreementComparison;
    DemoQADashboardPage demoQADashboardPage;


    @BeforeMethod
    @Parameters({"Email", "Password","URL" })
    public void launchBrowser(@Optional("subdomain@dogoodsinc.com") String Email,
                              @Optional("Dev@Admin2023!")String Password,
                              @Optional("https://qa-demo.dogoodsinc.com/")String Url)
            throws MalformedURLException {

        this.url = Url;
        this.email = Email;
        this.password = Password;

        demoQALoginPage = new DemoQALoginPage();
        demoQALoginPage.launchNewBrowserInstance();
    }

    @Test
    public void priorAgreementComparisonColourTest(){
        demoQALoginPage = new DemoQALoginPage();
        demoQADashboardPage = new DemoQADashboardPage();
        demoQAPriorAgreementComparison = new DemoQAPriorAgreementComparison();

        demoQALoginPage.launchUrl(this.url);
        demoQALoginPage.setEmailAddress(this.email);
        demoQALoginPage.setPassword(this.password);
        demoQALoginPage.rememberMeClick();
        demoQALoginPage.signInClick();
        demoQADashboardPage.clickOnPriorAgreementComparison();
        demoQAPriorAgreementComparison.changeMonthsByDropDown();
        String expected = "#28a745";//green HexColor
        String totalSavingsActual = demoQAPriorAgreementComparison.totalSavingsColourVerify();
        Assert.assertEquals(totalSavingsActual,expected);
        String surchargeActual = demoQAPriorAgreementComparison.surchargeSavingsColorVerify();
        Assert.assertEquals(surchargeActual,expected);
        String transportationActual = demoQAPriorAgreementComparison.transportationSavingsColorVerify();
        Assert.assertEquals(transportationActual,expected);
        String avgSavingsPkgActual = demoQAPriorAgreementComparison.averageSavingsPerPackageColorVerify();
        Assert.assertEquals(avgSavingsPkgActual,expected);
        String actualCreditRefunds = demoQAPriorAgreementComparison.actualCreditRefundsColorVerify();
        Assert.assertEquals(actualCreditRefunds,expected);

    }

    @Test
    public void priorAgreementComparisonTotalSavingsTest(){
        demoQALoginPage = new DemoQALoginPage();
        demoQADashboardPage = new DemoQADashboardPage();
        demoQAPriorAgreementComparison = new DemoQAPriorAgreementComparison();

        demoQALoginPage.launchUrl(this.url);
        demoQALoginPage.setEmailAddress(this.email);
        demoQALoginPage.setPassword(this.password);
        demoQALoginPage.rememberMeClick();
        demoQALoginPage.signInClick();
        demoQADashboardPage.clickOnPriorAgreementComparison();
        demoQAPriorAgreementComparison.changeMonthsByDropDown();
        demoQAPriorAgreementComparison.totalSavingsCalculate();
    }

    @DataProvider
    public String[][] getDemoQACredentials(){

        String[][] data = new String[1][3];
        data[0][0] = "https://qa-demo.dogoodsinc.com/";
        data[0][1] = "subdomain@dogoodsinc.com";
        data[0][2] = "Dev@Admin2023!";

        return data;
    }


    @AfterMethod
    public void closeApplication() throws MalformedURLException {
        demoQALoginPage.closeBrowser();
    }

}
