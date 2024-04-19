package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utility.BrowserKeeper;
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
      this.datePicker=By.id("datepicker");
      this.doneBtn=By.xpath("//button[@class='ui-datepicker-close ui-state-default " +
              "ui-priority-primary ui-corner-all']");
      this.regenerateConfirmBtn = By.xpath("//button[@fdprocessedid='lqum2s']");

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
      this.driver.waitForPresenceOfElement(4,email);
      this.getBrowser().get(URL);
  }

    public void generateEarningsHandler(int dateValue, String yearValue){
        WebElement generateEarningsBtnElement = this.getBrowser().findElement(generateEarningsBtn);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", generateEarningsBtnElement);
        jsExecutor.executeScript("arguments[0].click()", generateEarningsBtnElement);

        WebElement datePickerElement = this.getBrowser().findElement(datePicker);
        Actions ac = new Actions(this.getBrowser());
        ac.moveToElement(datePickerElement).contextClick();
//        jsExecutor.executeScript("arguments[0].click()", datePickerElement);

        this.driver.waitForPresenceOfElement(2,monthSelectDropdown);
        monthSelectDropdownElement = this.getBrowser().findElement(monthSelectDropdown);
        Select monthSelect = new Select(monthSelectDropdownElement);
        monthSelect.selectByIndex(dateValue);

        yearSelectDropdownElement = this.getBrowser().findElement(yearSelectDropdown);
        Select yearSelect = new Select(yearSelectDropdownElement);
        yearSelect.selectByVisibleText(yearValue);

        WebElement doneBtnElement = this.getBrowser().findElement(doneBtn);
        jsExecutor.executeScript("arguments[0].click()", doneBtnElement);

        WebElement goBtnElement = this.getBrowser().findElement(goBtn);
        jsExecutor.executeScript("arguments[0].click()", goBtnElement);

        if (this.getBrowser().findElement(regenerateConfirmBtn).isDisplayed()){
            jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(regenerateConfirmBtn));
        }

    }


}
  

