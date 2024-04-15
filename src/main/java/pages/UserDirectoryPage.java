package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class UserDirectoryPage extends BasePage{
    WebElement getEditBtn;
    JavascriptExecutor js;
    By yourProfile;
    By manageAccount;
    By logOut;
    By addNew;
    By userTestQAEditBtn;
    public UserDirectoryPage(){
        this.yourProfile=By.xpath("//a[@title='Your profile']");
        this.manageAccount=By.xpath("//a[@class='px-4 py-3 border-bottom']");
        this.logOut=By.xpath("//a[@class='px-4 py-3']");
        this.addNew=By.id("addcustomerbutton");
        this.userTestQAEditBtn=By.xpath("//tbody/tr[25]/td[4]/a[1]/i[1]");
    }
    public void clickOnYourProfile(){
        this.getBrowser().findElement(yourProfile).click();
    }
    public void clickOnManageAccount(){
        this.getBrowser().findElement(manageAccount).click();
    }
    public void clickOnLogOut(){
        this.getBrowser().findElement(logOut).click();
    }
    public void clickOnAddNew(){
        this.getBrowser().findElement(addNew).click();
    }
    public void userTestQAEditBtnClick(){
        js = (JavascriptExecutor) this.getBrowser();
        js.executeScript("window.scrollTo(0, 900);");
        this.driver.waitForPresenceOfElement(4,userTestQAEditBtn);
        getEditBtn = this.getBrowser().findElement(userTestQAEditBtn);
        js.executeScript("arguments[0].click()", getEditBtn);
    }
}
