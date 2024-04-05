package test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EarningsPage;
import pages.LoginPage;
import pages.respectiveClientEarningsPage;

import java.net.MalformedURLException;

public class respectiveClientPageTest {
    LoginPage loginPage;
    EarningsPage earningsPage;
    DashboardPage dashboardPage;
    public respectiveClientPageTest(){

    }
    //@Parameters({"URL","Email","Password"})
    //@BeforeTest
    public void launchApplication(String URL, String Email, String Password) throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl(URL);
        loginPage.setEmailAddress(Email);
        loginPage.setPassword(Password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
    }
    //@Test
    public void claimsAmountTest(){
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnEarnings();
        earningsPage = new EarningsPage();
        Assert.assertTrue(earningsPage.verifyTitle());
        earningsPage.typeClientNameAndClickOnIt();
        respectiveClientEarningsPage rcep = new respectiveClientEarningsPage();
        Assert.assertEquals(rcep.verifyClientName(),earningsPage.clientName);
    }
}
