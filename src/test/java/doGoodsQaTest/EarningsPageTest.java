package doGoodsQaTest;
import doGoodsQaPages.DashboardPage;
import doGoodsQaPages.EarningsPage;
import doGoodsQaPages.LoginPage;
import doGoodsQaPages.RespectiveClientEarningsPage;

import java.net.MalformedURLException;
public class EarningsPageTest {
    LoginPage loginPage;
    EarningsPage earningsPage;
    RespectiveClientEarningsPage rcep;
    DashboardPage dashboardPage;
    public EarningsPageTest(){
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
    //@AfterTest
    public void closeApplication() throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.closeBrowser();
    }


}
