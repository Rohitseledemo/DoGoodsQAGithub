package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
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
   public boolean waitForPresenceOfElement(int waitTime, By ele){
       WebDriverWait wait = new WebDriverWait( browser,Duration.ofSeconds(waitTime));
       boolean result = true;
       try{
       wait.until(ExpectedConditions.presenceOfElementLocated(ele));
       }
       catch (Exception e){
           result = false;
       }
           return result;
   }
}
