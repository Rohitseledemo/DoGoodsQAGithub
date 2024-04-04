package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Optional;

public class EarningsPage extends BasePage{
    By title;
    public EarningsPage(){
      this.title= By.xpath("//h3[normalize-space()='Customer Listing']");
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

    }
