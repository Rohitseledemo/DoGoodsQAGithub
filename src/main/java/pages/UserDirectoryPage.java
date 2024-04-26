package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserDirectoryPage extends BasePage{
    WebElement getEditBtn;
    By yourProfile;
    By manageAccount;
    By logOut;
    By addNew;
    By userNameList;
    public UserDirectoryPage(){
        this.yourProfile=By.xpath("//a[@title='Your profile']");
        this.manageAccount=By.xpath("//a[@class='px-4 py-3 border-bottom']");
        this.logOut=By.xpath("//a[@class='px-4 py-3']");
        this.addNew=By.id("addcustomerbutton");
        this.userNameList=By.xpath("//tbody//tr//td[@class='dbox-aname godetails']");
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
    public void clickOnEditBtnByUsername(String userName){
        int index=0;
        this.driver.waitForPresenceOfElement(4,userNameList);
        List<WebElement> userNameListElement = this.getBrowser().findElements(userNameList);
        for (int i = 0; i< userNameListElement.size(); i++) {
            String userNameText = userNameListElement.get(i).getText();
            if (userNameText.equalsIgnoreCase(userName)){
                index=i;
                break;
            }
        }
        jsExecutor.executeScript("window.scrollTo(0, 900);");
        WebElement indexListElement = this.getBrowser().findElements(By.xpath
                ("//i[@class='fas fa-edit get_edit']")).get(index);
        jsExecutor.executeScript("arguments[0].click()",indexListElement);

    }

    public void logoutToLandingPage(){
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(yourProfile));
        this.driver.waitForPresenceOfElement(2,logOut);
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(logOut));
    }
}
