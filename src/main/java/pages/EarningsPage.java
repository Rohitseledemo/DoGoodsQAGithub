package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EarningsPage extends BasePage {
    By title;
    By clientNameClick;
    By testClientName;
    By companyTextBox;
    By clientNamesList;

    public EarningsPage() {
        this.title = By.xpath("//h3[normalize-space()='Customer Listing']");
        this.clientNameClick= By.xpath("//td[normalize-space()='Azazie']");
        this.testClientName= By.xpath("//td[normalize-space()='Selenium Testing']");
        this.companyTextBox=By.xpath("//input[@placeholder='Company']");
        this.clientNamesList=By.xpath("//tbody/tr/td[3]");
    }

    public List<WebElement> getAllClientsNames() {
        return this.getBrowser().findElements(clientNamesList);
    }

    public Optional<WebElement> getSpecificClientName(String clientName) {
        List<WebElement> clientNames = this.getAllClientsNames();
        return clientNames.stream().filter(name -> name.getText().equals(clientName)).findFirst();
    }

    public boolean verifyTitle() {
        this.driver.waitForPresenceOfElement(4, title);
        return this.getBrowser().findElement(title).isDisplayed();
    }

    public void typeClientNameAndClickOnIt(String clientName) {
        Actions ac = new Actions(this.getBrowser());
        ac.moveToElement(this.getBrowser().findElement(companyTextBox)).click().sendKeys
                (clientName, Keys.RETURN).build().perform();
        WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(2));
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(clientNamesList,2));
        WebElement firstRowAppearance = this.getBrowser().findElement(clientNamesList);
        this.driver.waitForVisibilityOfWebElement(4,firstRowAppearance);
        jsExecutor.executeScript("arguments[0].click()",firstRowAppearance);
    }

    public boolean searchEarningsClientsInList() {
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
            return allValuesFound;
        }
    }




