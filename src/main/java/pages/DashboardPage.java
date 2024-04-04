package pages;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage{
    By signOutBtn,sideMenuItemEarnings;
    public DashboardPage(){
        this.signOutBtn= By.xpath("//a[normalize-space()='Sign Out']");
        this.sideMenuItemEarnings = By.xpath("//p[normalize-space()='Earnings']");
    }
    public void clickOnEarnings(){
        this.getBrowser().findElement(sideMenuItemEarnings).click();
    }
}
