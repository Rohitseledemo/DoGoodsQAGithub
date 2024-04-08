package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CustomerPage extends BasePage {
    By compList;

    public CustomerPage() {
        this.compList = By.xpath("//tr/td[2]");
    }

    public List<WebElement> getAllNames() {
        this.driver.waitForPresenceOfElement(4,compList);
        return this.getBrowser().findElements(compList);
    }

    public void searchClientInList() {
        List<String> names = new ArrayList<>();
        names.add("Activ Post");
        names.add("America Sunshine");
        boolean allValuesFound = true;
        for (String name : names) {
            boolean valueFound = false;
            for (WebElement element : getAllNames()) {
                String elementText = element.getText();
                if (elementText.contains(name)){
                    valueFound = true;
                    break;
                }
            }
            if (!valueFound) {
                allValuesFound = false;
                break;
            }
        }
         Assert.assertTrue(allValuesFound);
    }
}



