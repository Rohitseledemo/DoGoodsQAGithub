package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EarningsPage extends BasePage {
    By title;
    By clientNameClick;
    By companyTextBox;

    public EarningsPage() {
        this.title = By.xpath("//h3[normalize-space()='Customer Listing']");
        this.clientNameClick = By.xpath("//td[normalize-space()='Digital Color Concepts']");
        this.companyTextBox = By.xpath("//input[@placeholder='Company']");
//      this.clientNameClick=this.getBrowser().findElement(By.xpath(("//td[normalize-space()='Digital Color Concepts']")));
//      this.companyTextBox=this.getBrowser().findElement(By.xpath("//input[@placeholder='Company']"));
    }

    public List<WebElement> getAllClientsNames() {
        return this.getBrowser().findElements(By.xpath("//tbody/tr/td[1]"));
    }

    public Optional<WebElement> getSpecificClientName(String clientName) {
        List<WebElement> clientNames = this.getAllClientsNames();
        return clientNames.stream().filter(name -> name.getText().equals(clientName)).findFirst();
    }

    public boolean verifyTitle() {
        this.driver.waitForPresenceOfElement(4, title);
        return this.getBrowser().findElement(title).isDisplayed();
    }


    public void typeClientNameAndClickOnIt() {
        Actions ac = new Actions(this.getBrowser());
//        ac.moveToElement(companyTextBox).sendKeys(clientName, Keys.RETURN).perform();
//        ac.click(clientNameClick);
    }

    public void searchEarningsClientsInList() {
        List<String> names = new ArrayList<>();
        names.add("Activ Post");
        names.add("America Sunshine");
        boolean allValuesFound = true;
        for (String name : names) {
            boolean valueFound = false;
            if (getAllClientsNames().size() <= 2) {
                for (WebElement element : getAllClientsNames()) {
                    String elementText = element.getText();
                    if (elementText.contains(name)){
                        valueFound = true;
                        break;
                    }
                }
            }
                if (!valueFound || getAllClientsNames().size()>2) {
                    allValuesFound = false;
                    break;
                }
            }
            Assert.assertTrue(allValuesFound);
        }
    }




