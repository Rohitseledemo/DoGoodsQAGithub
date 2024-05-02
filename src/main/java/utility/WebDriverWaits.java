package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverWaits {
WebDriver driver;

    public WebDriverWaits(WebDriver driver){
          this.driver = driver;
    }

    public boolean waitForPresenceOfElement(int waitTime, By ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        boolean result = true;
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(ele));
        }
        catch (Exception e){
            result = false;
        }
        return result;
    }

    public boolean waitForVisibilityOfWebElement(int waitTime, WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTime));
        boolean result = true;
        try{
            wait.until(ExpectedConditions.visibilityOf(ele));
        }
        catch (Exception e){
            result = false;
        }
        return result;
    }
    public boolean waitForStalenessOfWebElement(int waitTime, WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTime));
        boolean result = true;
        try{
            wait.until(ExpectedConditions.stalenessOf(ele));
        }
        catch (Exception e){
            result = false;
        }
        return result;
    }
    public boolean waitForNumberOfElementsToBeLessThan(By locator, int ele){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
        boolean result = true;
        try{
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(locator,ele));
        }
        catch (Exception e){
            result = false;
        }
        return result;
    }


}
