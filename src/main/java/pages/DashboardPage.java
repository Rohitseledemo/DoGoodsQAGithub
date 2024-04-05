package pages;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage{
    By signOutBtn;
    By sideMenuItemEarnings;
    By sideMenuItemUserDirectory;
    public DashboardPage(){
        this.signOutBtn= By.xpath("//a[normalize-space()='Sign Out']");
        this.sideMenuItemEarnings = By.xpath("//p[normalize-space()='Earnings']");
        this.sideMenuItemEarnings = By.xpath("//i[@class='fas fa-address-book']");
    }
    public void clickOnEarnings(){
        this.getBrowser().findElement(sideMenuItemEarnings).click();
    }
    public void clickOnUserDirectory(){
        this.getBrowser().findElement(sideMenuItemUserDirectory).click();
    }
}
