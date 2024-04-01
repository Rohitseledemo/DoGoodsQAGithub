package pages;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class DashboardPage extends BasePage{
    By signOutBtn;
    public DashboardPage(){
        this.signOutBtn= By.xpath("//a[normalize-space()='Sign Out']");
        System.out.println("hello 2");
    }
}
