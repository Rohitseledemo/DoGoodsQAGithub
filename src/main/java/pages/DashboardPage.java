package pages;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage{
    By signOutBtn;
    By sideMenuItemEarnings;
    By sideMenuItemUserDirectory;
    By getSideMenuCustomer;
    public DashboardPage(){
        this.signOutBtn= By.xpath("//a[normalize-space()='Sign Out']");
        this.sideMenuItemEarnings = By.xpath("//p[normalize-space()='Earnings']");
        this.sideMenuItemEarnings = By.xpath("//p[normalize-space()='Earnings']");
        this.getSideMenuCustomer =By.xpath("//i[@class='fas fa-user-tie']");
    }
    public void clickOnEarnings(){
        this.driver.waitForPresenceOfElement(4,sideMenuItemEarnings);
        this.getBrowser().findElement(sideMenuItemEarnings).click();
    }
    public void clickOnUserDirectory(){
        this.getBrowser().findElement(sideMenuItemUserDirectory).click();
    }
    public void clickOnCustomer(){
        this.driver.waitForPresenceOfElement(4,getSideMenuCustomer);
        this.getBrowser().findElement(getSideMenuCustomer).click();
    }
}
