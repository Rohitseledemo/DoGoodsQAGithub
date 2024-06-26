package doGoodsQaPages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import utility.BrowserKeeper;
import utility.JavaScriptExecutorMethods;
import utility.WebDriverWaits;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class BasePage {
    public BrowserKeeper driver;

    JavaScriptExecutorMethods javaScript;

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
      javaScript = new JavaScriptExecutorMethods(this.getBrowser());

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
    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)this.getBrowser();
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//do_goods_qa_report//"+testCaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//do_goods_qa_report//"+testCaseName+".png";
    }

}
  

