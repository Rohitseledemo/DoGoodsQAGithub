package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserDirectoryPage extends BasePage{
    WebElement getEditBtn;
    By yourProfile;
    By manageAccount;
    By logOut;
    By addNew;
    By userTestEditBtn;
    public UserDirectoryPage(){
        this.yourProfile=By.xpath("//a[@title='Your profile']");
        this.manageAccount=By.xpath("//a[@class='px-4 py-3 border-bottom']");
        this.logOut=By.xpath("//a[@class='px-4 py-3']");
        this.addNew=By.id("addcustomerbutton");
        this.userTestEditBtn=By.xpath("//tbody/tr[19]/td[4]/a[1]/i[1]");
    }
    public void clickOnYourProfile(){
        this.getBrowser().findElement(yourProfile).click();
    }
    public void clickOnManageAccount(){
        this.getBrowser().findElement(yourProfile).click();
        this.getBrowser().findElement(manageAccount).click();
    }
    public void clickOnAddNew(){
        this.getBrowser().findElement(addNew).click();
    }
    public void userTestEditBtnClick(){
        jsExecutor.executeScript("window.scrollTo(0, 900);");
        this.driver.waitForPresenceOfElement(4,userTestEditBtn);
        getEditBtn = this.getBrowser().findElement(userTestEditBtn);
        jsExecutor.executeScript("arguments[0].click()", getEditBtn);
    }
    public void logoutToLandingPage(){
        this.getBrowser().findElement(yourProfile).click();
        this.driver.waitForPresenceOfElement(2,logOut);
        this.getBrowser().findElement(logOut).click();
    }
}
