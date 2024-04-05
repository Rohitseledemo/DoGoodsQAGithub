package pages;

import org.openqa.selenium.By;

public class UserDirectoryPage extends BasePage{
    By yourProfile;
    By manageAccount;
    By logOut;
    By addNew;
    public UserDirectoryPage(){
        this.yourProfile=By.xpath("//a[@title='Your profile']");
        this.manageAccount=By.xpath("//a[@class='px-4 py-3 border-bottom']");
        this.logOut=By.xpath("//a[@class='px-4 py-3']");
        this.addNew=By.id("addcustomerbutton");
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
}
