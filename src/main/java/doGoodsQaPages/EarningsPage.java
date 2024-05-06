package doGoodsQaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utility.JavaScriptExecutorMethods;
import utility.WebDriverWaits;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EarningsPage extends BasePage {
    WebDriverWaits wait;

    By title;
    By clientNameClick;
    By testClientName;
    By companyTextBox;
    By clientNamesList;
    By mbgSelectDropdown;

    public EarningsPage() {
        this.title = By.xpath("//h3[normalize-space()='Customer Listing']");
        this.clientNameClick= By.xpath("//td[normalize-space()='Azazie']");
        this.testClientName= By.xpath("//td[normalize-space()='Selenium Testing']");
        this.companyTextBox=By.xpath("//input[@placeholder='Company']");
        this.clientNamesList=By.xpath("//tbody/tr/td[3]");
        this.mbgSelectDropdown=By.xpath("//select[@name='earnings_calc_types']");
    }

    public List<WebElement> getAllClientsNames() {
        return this.getBrowser().findElements(clientNamesList);
    }

    public Optional<WebElement> getSpecificClientName(String clientName) {
        List<WebElement> clientNames = this.getAllClientsNames();
        return clientNames.stream().filter(name -> name.getText().equals(clientName)).findFirst();
    }

    public boolean verifyTitle() {
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4, title);
        return this.getBrowser().findElement(title).isDisplayed();
    }
    public void filterByClientName(String clientName) throws InterruptedException {
        wait = new WebDriverWaits(this.getBrowser());
        wait.waitForVisibilityOfWebElement(4,this.getBrowser().findElement(clientTitle));
        WebElement staleElement = this.driver.staleElementRetry(companyTextBox,2,1000);
        if (staleElement != null) {
            // Performing the action again on the returned staleElement
            staleElement.sendKeys(clientName, Keys.ENTER);
        } else {
            System.out.println("Element not found after retries.");
        }
    }

    public void filteredClientClick() throws InterruptedException {
        wait = new WebDriverWaits(this.getBrowser());
        wait.waitForNumberOfElementsToBeLessThan(clientNamesList,2);

        WebElement firstRowAppearance = this.getBrowser().findElement(clientNamesList);
        wait.waitForVisibilityOfWebElement(4,firstRowAppearance);
        javaScript.clickWebElement(firstRowAppearance);
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




