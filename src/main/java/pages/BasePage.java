package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BrowserKeeper;
import java.net.MalformedURLException;
import java.time.Duration;

public class BasePage {
  public BrowserKeeper driver;
  By email,pass,signInBtn;
  public BasePage(){
	  driver = new BrowserKeeper();
      this.email= By.xpath("//input[@id='email_addtess']");
      this.pass= By.xpath("//input[@id='login_pass']");
      this.signInBtn=By.xpath("//*[text()='Sign In']");

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
//  @Parameters({"Email","Password","URL"})
//  public void getIntoApplication(String URL, String Email, String Password){
//    this.getBrowser().get(URL);
//    this.getBrowser().findElement(email).sendKeys(Email);
//    this.getBrowser().findElement(pass).sendKeys(Password);
//    this.getBrowser().findElement(signInBtn).click();
//  }


}
  

