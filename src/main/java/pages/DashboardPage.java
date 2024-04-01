package pages;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage{
    By signOutBtn;
    public DashboardPage(){
        this.signOutBtn= By.xpath("//a[normalize-space()='Sign Out']");
    }
}
