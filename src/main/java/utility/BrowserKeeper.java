package utility;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserKeeper { 
  private static WebDriver browser;
    public WebDriverWait wait;
   public BrowserKeeper(){
	   
   }

    public void setupWebDriver() throws MalformedURLException {
       String username = System.getenv("BROWSERSTACK_USERNAME");
       String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
       ChromeOptions option=new ChromeOptions();
       option.addArguments("start-maximized");
       MutableCapabilities capabilities = new MutableCapabilities();
       capabilities.setCapability("browserName", "Chrome");
       capabilities.setCapability(ChromeOptions.CAPABILITY, option);
       HashMap<String, String> browserstackOptions = new HashMap<String, String>();
       browserstackOptions.put("os", "Windows");
       capabilities.setCapability("bstack:options", browserstackOptions);
       browser = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey +
               "@hub.browserstack.com/wd/hub"), capabilities);

    }

   public  WebDriver getBrowserInstance() {
	return browser;
   }

   public WebElement staleElementRetry(By locator,int maxAttempts, long retryInterval){
       int attempts = 0;
       WebElement staleElement = null;

       while (attempts < maxAttempts) {
           try {
               staleElement = browser.findElement(locator);
               break;
           } catch (StaleElementReferenceException e) {
               try {
                   Thread.sleep(retryInterval);
               } catch (InterruptedException ex) {
                   Thread.currentThread().interrupt();
               }
           }
           attempts++;
       }
       return staleElement;
   }

   }
