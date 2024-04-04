package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Optional;

public class EarningsPage extends BasePage{
    By title;
    WebElement clientNameClick;
    WebElement companyTextBox;
    public String clientName= "Digital Color Concepts";
    public EarningsPage(){
      this.title=By.xpath("//h3[normalize-space()='Customer Listing']");
      this.clientNameClick=this.getBrowser().findElement(By.xpath(("//td[normalize-space()='Digital Color Concepts']")));
      this.companyTextBox=this.getBrowser().findElement(By.xpath("//input[@placeholder='Company']"));
    }
    public List<WebElement> getAllClientsNames(){
        return this.getBrowser().findElements(By.xpath("//tbody/tr/td[1]"));
    }
    public Optional<WebElement> getSpecificClientName(String clientName){
        List<WebElement> clientNames = this.getAllClientsNames();
            return clientNames.stream().filter(name -> name.getText().equals(clientName)).findFirst();
        }
    public boolean verifyTitle(){
        return this.getBrowser().findElement(title).isDisplayed();
    }


    public void typeClientNameAndClickOnIt(){
        Actions ac = new Actions(this.getBrowser());
        ac.moveToElement(companyTextBox).sendKeys(clientName, Keys.RETURN).perform();
        ac.click(clientNameClick);
    }
    }


