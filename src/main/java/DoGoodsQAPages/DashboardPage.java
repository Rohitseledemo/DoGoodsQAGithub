package DoGoodsQAPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utility.WebDriverWaits;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {
    WebDriverWaits wait;

    List<WebElement> menuItemListWebElements;
    By signOutBtn;
    By sideMenuItemEarnings;
    By sideMenuItemUserDirectory;
    By getSideMenuCustomer;
    protected By menuItemList;

    public DashboardPage() {
        this.signOutBtn = By.xpath("//a[normalize-space()='Sign Out']");
        this.sideMenuItemEarnings = By.xpath("//p[normalize-space()='Earnings']");
        this.getSideMenuCustomer = By.xpath("//i[@class='fas fa-user-tie']");
        this.menuItemList = By.xpath("//li[@class='nav-item nav-item-custom']");
        this.sideMenuItemUserDirectory=By.xpath("//i[@class='fas fa-address-book']");
    }

    public void clickOnEarnings() {
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4, sideMenuItemEarnings);
        this.getBrowser().findElement(sideMenuItemEarnings).click();
    }

    public void clickOnUserDirectory() {
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4, sideMenuItemUserDirectory);
        this.getBrowser().findElement(sideMenuItemUserDirectory).click();
    }

    public void clickOnCustomer() {
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4, getSideMenuCustomer);
        this.getBrowser().findElement(getSideMenuCustomer).click();
    }

    public List<WebElement> getAllMenuItemNames() {
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4, menuItemList);
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
                        break;
                    }
                }
            }
            if (!valueFound || getAllMenuItemNames().size()>2){
                allValuesFound = false;
                break;}
        }
        Assert.assertTrue(allValuesFound);
        }


        public boolean checkDashboardMenuList(List<String> menuNames) {
            wait = new WebDriverWaits(this.getBrowser());

            boolean allValuesFound = true;
            wait.waitForPresenceOfElement(4,menuItemList);
            menuItemListWebElements = this.getBrowser().findElements(menuItemList);
            if (menuItemListWebElements.size() != menuNames.size()) {
                allValuesFound = false;
            }
            else {
                 //to avoid this loop in case size!=
                for (int i = 0; i < menuItemListWebElements.size(); i++) {
                    String elementText = menuItemListWebElements.get(i).getText();
//                    String menuListText = menuNames.get(i);
                   menuNames.contains(elementText);
                    if (!menuNames.contains(elementText)) {
                        allValuesFound = false;
                    }
                    else{
                        System.out.println(elementText);
                    }
                }
            }
               return allValuesFound;
        }

    }

