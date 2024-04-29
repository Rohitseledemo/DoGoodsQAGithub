package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utility.BrowserKeeper;
import utility.WebDriverWaits;

import java.net.MalformedURLException;

public class BasePage {
    public BrowserKeeper driver;

    JavascriptExecutor jsExecutor;


    WebElement monthSelectDropdownElement;
    WebElement yearSelectDropdownElement;

    By email;
    By pass;
    By signInBtn;
    By generateEarningsBtn;
    By doneBtn;
    By goBtn;
    By monthSelectDropdown;
    By yearSelectDropdown;
    By datePicker;
    By regenerateConfirmBtn;
    By noDataFoundTxt;
    By clientTitle;

  public BasePage(){
	  driver = new BrowserKeeper();

      jsExecutor = (JavascriptExecutor)this.getBrowser();

      this.email= By.xpath("//input[@id='email_addtess']");
      this.pass= By.xpath("//input[@id='login_pass']");
      this.signInBtn=By.xpath("//*[text()='Sign In']");
      this.generateEarningsBtn=By.id("genearning");
      this.goBtn = By.id("earninggen");
      this.monthSelectDropdown =By.xpath("//select[@class='ui-datepicker-month']");
      this.yearSelectDropdown =By.xpath("//select[@class='ui-datepicker-year']");
      this.datePicker=By.xpath("//input[@id='datepicker']");
      this.doneBtn=By.xpath("//button[@class='ui-datepicker-close ui-state-default " +
              "ui-priority-primary ui-corner-all']");
      this.regenerateConfirmBtn = By.xpath("//button[@class='btn btn-primary confirm_btn']");
      this.noDataFoundTxt=By.xpath("//div[@class='callout callout-danger text-danger']");
      this.clientTitle=By.xpath("//div[@class='mb-3 d-sm-block d-md-flex']//h3");
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
      WebDriverWaits wait = new WebDriverWaits(this.getBrowser());

      wait.waitForPresenceOfElement(4,email);
      this.getBrowser().get(URL);
  }

}
  

