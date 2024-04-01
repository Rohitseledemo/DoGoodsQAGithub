package stepDefinitions;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.net.MalformedURLException;

public class LoginPageTest {
    LoginPage loginPage;
    @Test
    public void loginPageTest() throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.launchNewBrowserInstance();
        loginPage.launchUrl("https://qa-admin.dogoodsinc.com/admin/");
        String inputEmail ="admin@dogoodsinc.com";
        loginPage.setEmailAddress(inputEmail);
        String inputPassword = "Admin@Shipplug2024!";
        loginPage.setPassword(inputPassword);
        loginPage.closeBrowser();
    }
}
