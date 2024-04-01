package pages;

import org.openqa.selenium.WebDriver;

import utility.BrowserKeeper;

public class BasePage {
  public BrowserKeeper driver;
  public BasePage(){
	  driver = new BrowserKeeper();
  }
  public  WebDriver getBrowser(){
      return this.driver.getBrowserInstance();
  }
  public void launchNewBrowserInstance() {
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
}
  

