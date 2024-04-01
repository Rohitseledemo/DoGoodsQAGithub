package utility;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserKeeper { 
  private static WebDriver browser;
  	
   public BrowserKeeper(){
	   
   }
   public void setupWebDriver() throws MalformedURLException {
       String username = System.getenv("BROWSERSTACK_USERNAME");
       String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
       //String username = "rohitkumar_ufv9J8";
       //String accessKey = "FYLuo28MBhzbbAhQCXxz";
       MutableCapabilities capabilities = new MutableCapabilities();
       capabilities.setCapability("browserName", "Chrome");
       HashMap<String, String> browserstackOptions = new HashMap<String, String>();
       browserstackOptions.put("os", "Windows");
       capabilities.setCapability("bstack:options", browserstackOptions);
       browser = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"), capabilities);
//        DesiredCapabilities caps=new DesiredCapabilities();
//		ChromeOptions option=new ChromeOptions();
//		option.addArguments("start-maximized");
//		option.merge(caps);
//	    browser = new ChromeDriver(option);
   }
   
   public  WebDriver getBrowserInstance() {
	return browser;
   }
}
