package doGoodsQaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.WebDriverWaits;

import java.util.ArrayList;
import java.util.List;

public class CustomerPage extends BasePage {
    WebDriverWaits wait;
    By compList;

    public CustomerPage() {
        this.compList = By.xpath("//tr/td[2]");
    }

    public List<WebElement> getAllNames() {
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4,compList);
        return this.getBrowser().findElements(compList);
    }

    public boolean searchCustomerClientInList() {
        List<String> names = new ArrayList<>();
        names.add("Activ Post");
        names.add("America Sunshine");
        boolean allValuesFound = true;
        for (String name : names) {
            boolean valueFound = false;
            if (getAllNames().size() <= 2) {
                for (WebElement element : getAllNames()) {
                    String elementText = element.getText();
                    if (elementText.contains(name)) {
                        valueFound = true;
                        break;
                    }
                }
            }
            if (!valueFound || getAllNames().size()>2){
                allValuesFound = false;
                break;
            }
        }
         return allValuesFound;
    }
}



