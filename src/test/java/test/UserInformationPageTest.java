package test;

import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.UserDirectoryPage;
import pages.UserInformationPage;
import userInputObjects.UserInfoObject;

import java.net.MalformedURLException;

public class UserInformationPageTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    UserDirectoryPage userDirectoryPage;
    UserInformationPage userInformationPage;
    UserInfoObject userInfoObject;

    String url, email, password;

    @BeforeMethod
    @Parameters({"Email","Password","URL"})
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
   @Test(dataProvider = "getTestData")
   public void setAccessTest(String testEmail, String testPassword){
        loginPage.launchUrl(this.url);
        loginPage.setEmailAddress(this.email);
        loginPage.setPassword(this.password);
        loginPage.rememberMeClick();
        loginPage.signInClick();
        dashboardPage = new DashboardPage();
        dashboardPage.clickOnUserDirectory();
        userDirectoryPage = new UserDirectoryPage();
        userDirectoryPage.userTestEditBtnClick();
        userInfoObject= new UserInfoObject();
        userInformationPage = new UserInformationPage();
        userInformationPage.randomMenuClick();
        userInformationPage.randomClientClick();
        userInformationPage.clickOnSaveBtn();
        userDirectoryPage.logoutToLandingPage();

        loginPage.setEmailAddress(testEmail);
        loginPage.setPassword(testPassword);
        loginPage.rememberMeClick();
        loginPage.signInClick();

        dashboardPage.checkDashboardMenuList(userInformationPage.menuNames);


    }


    @DataProvider
    public String[][] getTestData() {
        String[][] data = new String[1][2];
        // 1st dataset
        data[0][0] = "rohitqa@dogoodsinc.com";
        data[0][1] = "Test@418";
        return data;
    }

//    @AfterMethod
//    public void closeApplication() throws MalformedURLException {
//        loginPage.closeBrowser();
//    }

}
