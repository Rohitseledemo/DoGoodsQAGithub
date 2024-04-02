package pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BrowserKeeper;
import java.net.MalformedURLException;
import java.time.Duration;

public class BasePage {
  public BrowserKeeper driver;
  public BasePage(){
	  driver = new BrowserKeeper();
  }
  public  WebDriver getBrowser(){
    return this.driver.getBrowserInstance();
  }
  public void launchNewBrowserInstance() throws MalformedURLException {
	  driver.setupWebDriver();
  }
  public void closePage(){
	  this.getBrowser().close();
  }

  public void closeBrowser() {
      this.getBrowser().quit();
  }
  public void launchUrl(String URL) {
      this.getBrowser().get(URL);
  }
  public void waitForElementToAppear(WebElement elem){
    WebDriverWait wait = new WebDriverWait(this.getBrowser(), Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOf(elem));
  }

}
  

