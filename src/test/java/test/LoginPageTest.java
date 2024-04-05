package test;

import org.testng.annotations.*;
import pages.LoginPage;
import java.net.MalformedURLException;

public class LoginPageTest {
    String url, email, password;
    LoginPage loginPage;

    @Parameters({ "URL", "Email", "Password" })
    @BeforeTest
    public void launchBrowser(String URL, String Email, String Password) throws MalformedURLException {

        this.url = URL;
        this.email = Email;
        this.password = Password;

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
            loginPage.wrongEmailErrorDisplayed();

        } else if (scenario.equalsIgnoreCase("IncorrectPassword")) {
            loginPage.wrongPasswordErrorDisplayed();

        } else if (scenario.equalsIgnoreCase("EmptyEmail")) {
            loginPage.emptyEmailErrorDisplayed();

        } else if (scenario.equalsIgnoreCase("EmptyPassword")) {
            loginPage.emptyPasswordErrorDisplayed();

        } else if (scenario.equalsIgnoreCase("CorrectCredentials")) {
            loginPage.loginVerify();

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
        data[4][1] = this.email;
        data[4][2] = this.password;

        return data;
    }

    @AfterTest
    public void closeApplication() throws MalformedURLException {
        loginPage.closeBrowser();
    }

}
