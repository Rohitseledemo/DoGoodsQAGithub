package DoGoodsQATest;

import org.testng.Assert;
import org.testng.annotations.*;
import DoGoodsQAPages.LoginPage;
import java.net.MalformedURLException;

public class LoginPageTest {
    String url, email, password;
    LoginPage loginPage;

    @BeforeMethod
    @Parameters({"Email", "Password","URL" })
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

    @Test(dataProvider = "getData")
    public void loginTest(String scenario, String testInputEmail, String testInputPassword)
            throws MalformedURLException {
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(testInputEmail);
        loginPage.setPassword(testInputPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();

        if (scenario.equalsIgnoreCase("IncorrectEmail")) {
            Assert.assertTrue(loginPage.wrongEmailErrorDisplayed());

        } else if (scenario.equalsIgnoreCase("IncorrectPassword")) {
            Assert.assertTrue(loginPage.wrongPasswordErrorDisplayed());

        } else if (scenario.equalsIgnoreCase("EmptyEmail")) {
            Assert.assertTrue(loginPage.emptyEmailErrorDisplayed());

        } else if (scenario.equalsIgnoreCase("EmptyPassword")) {
            Assert.assertTrue(loginPage.emptyPasswordErrorDisplayed());

        } else if (scenario.equalsIgnoreCase("CorrectCredentials")) {
            Assert.assertTrue(loginPage.loginVerify());

        }

    }
    @DataProvider
    public String[][] getData() {
        // 5 different data sets of username and password so,
        String[][] data = new String[5][3];
        // 1st dataset
        data[0][0] = "IncorrectEmail";
        data[0][1] = "admin!dogoodsinc.com";
        data[0][2] = "Admin@Shipplug2024!";
        // 2nd dataset
        data[1][0] = "IncorrectPassword";
        data[1][1] = "admin@dogoodsinc.com";
        data[1][2] = "Admin!Shipplug2024!";
        // 3rd dataset
        data[2][0] = "EmptyEmail";
        data[2][1] = "";
        data[2][2] = "Admin@Shipplug2024!";
        // 4th dataset
        data[3][0] = "EmptyPassword";
        data[3][1] = "admin@dogoodsinc.com";
        data[3][2] = "";
        // 5th DataSet
        data[4][0] = "CorrectCredentials";
        data[4][1] = "admin@dogoodsinc.com";
        data[4][2] = "Admin@Shipplug2024!";

        return data;
    }

    @AfterMethod
    public void closeApplication() throws MalformedURLException {
        loginPage.closeBrowser();
    }

}
