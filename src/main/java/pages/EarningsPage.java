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
    By testClientName;
    By companyTextBox;

    public EarningsPage() {
        this.title = By.xpath("//h3[normalize-space()='Customer Listing']");
        this.clientNameClick= By.xpath("//td[normalize-space()='Azazie']");
        this.testClientName= By.xpath("//td[normalize-space()='Selenium Testing']");
        this.companyTextBox=By.xpath("//input[@placeholder='Company']");
    }

    public List<WebElement> getAllClientsNames() {
        return this.getBrowser().findElements(By.xpath("//tbody/tr/td[2]"));
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
        WebElement companyTextBoxWebElement=this.getBrowser().findElement(companyTextBox);
        String clientName = "sel";
        ac.moveToElement(companyTextBoxWebElement).sendKeys(clientName, Keys.RETURN).perform();
        this.driver.waitForPresenceOfElement(4,clientNameClick);
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(clientNameClick));
    }
    public void typeClientNameAndClickOnIt2() {
        Actions ac = new Actions(this.getBrowser());
        WebElement companyTextBoxWebElement=this.getBrowser().findElement(companyTextBox);
        String clientName = "sel";
        ac.moveToElement(companyTextBoxWebElement).sendKeys(clientName, Keys.RETURN).perform();
        this.driver.waitForPresenceOfElement(4,testClientName);
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(testClientName));
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




