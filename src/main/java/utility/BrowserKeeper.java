package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserKeeper { 
  private static WebDriver browser;
  	
   public BrowserKeeper(){
	   
   }
   public void setupWebDriver() {
	    DesiredCapabilities caps=new DesiredCapabilities();
		ChromeOptions option=new ChromeOptions();	
		option.addArguments("start-maximized");
		option.merge(caps);
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromeDriver/chromedriver.exe");
	    browser = new ChromeDriver(option);  
   }
   
   public  WebDriver getBrowserInstance() {
	return browser;
   }
}
