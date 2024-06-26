package demoQaPages;

import org.openqa.selenium.By;
import doGoodsQaPages.DashboardPage;
import utility.WebDriverWaits;

public class DemoQADashboardPage extends DashboardPage {
    WebDriverWaits wait;

    By sideMenuItemPriorAgreementComparison;

    public DemoQADashboardPage(){
        this.menuItemList=By.cssSelector("li.nav-item");
        this.sideMenuItemPriorAgreementComparison= By.xpath("//p[text()='Prior Agreement']");

    }

    public void clickOnPriorAgreementComparison() {
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4, sideMenuItemPriorAgreementComparison);
        this.getBrowser().findElement(sideMenuItemPriorAgreementComparison).click();
    }
}
