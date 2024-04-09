package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {
    By signOutBtn;
    By sideMenuItemEarnings;
    By sideMenuItemUserDirectory;
    By getSideMenuCustomer;
    By menuItemList;

    public DashboardPage() {
        this.signOutBtn = By.xpath("//a[normalize-space()='Sign Out']");
        this.sideMenuItemEarnings = By.xpath("//i[@class='fas fas fa-file-invoice-dollar']");
        this.getSideMenuCustomer = By.xpath("//i[@class='fas fa-user-tie']");
        this.menuItemList = By.xpath("//li[@class='nav-item nav-item-custom']");
    }

    public void clickOnEarnings() {
        this.driver.waitForPresenceOfElement(4, sideMenuItemEarnings);
        this.getBrowser().findElement(sideMenuItemEarnings).click();
    }

    public void clickOnUserDirectory() {
        this.getBrowser().findElement(sideMenuItemUserDirectory).click();
    }

    public void clickOnCustomer() {
        this.driver.waitForPresenceOfElement(4, getSideMenuCustomer);
        this.getBrowser().findElement(getSideMenuCustomer).click();
    }

    public List<WebElement> getAllMenuItemNames() {
        this.driver.waitForPresenceOfElement(4, menuItemList);
        return this.getBrowser().findElements(menuItemList);
    }

    public void searchDashboardMenuList() {
        List<String> names = new ArrayList<>();
        names.add("CUSTOMER");
        names.add("EARNINGS");
        boolean allValuesFound = true;
        for (String name : names) {
            boolean valueFound = false;
            if (getAllMenuItemNames().size() <= 2){
                for (WebElement element : getAllMenuItemNames()){
                    String elementText = element.getText();
                    if (elementText.equalsIgnoreCase(name)){
                        valueFound = true;
                        break;}
                }
            }
            if (!valueFound || getAllMenuItemNames().size()>2){
                allValuesFound = false;
                break;}
        }
        Assert.assertTrue(allValuesFound);
        }
    }

