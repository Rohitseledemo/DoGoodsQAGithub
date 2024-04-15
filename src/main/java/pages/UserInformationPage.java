package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import userInputObjects.UserInfoObject;

import java.util.*;

public class UserInformationPage extends BasePage {
    UserInfoObject userInfoObject;

    JavascriptExecutor js;

    By clientList;
    By menuList;
    By saveBtn;

     private Set<Integer> randomMenuIndex;
     private Set<Integer> randomClientIndex;

    public UserInformationPage() {
        this.clientList = By.xpath("//div[@class='col-sm-4']");
        this.menuList = By.xpath("//input[@class='form-check-input child child_1']");
        this.saveBtn = By.xpath("//button[@type='submit']");
    }

    public void getRandomIndexes(int count) {
        this.randomMenuIndex = new HashSet<>();
        this.randomClientIndex = new HashSet<>();
        Random random = new Random();
        while(randomMenuIndex.size()<=count){
//        for(int i=0; i<menuCount; i++) {
            this.randomMenuIndex.add(random.nextInt(15));
        }
        while(randomClientIndex.size()<=count){
//        for(int i=0; i<clientCount; i++) {
            this.randomClientIndex.add(random.nextInt(91));
        }
    }

    public List<String> randomMenuClick() {
        js = (JavascriptExecutor) this.getBrowser();
        js.executeScript("window.scrollTo(0, 800);");
        List<WebElement> mnList = this.getBrowser().findElements(menuList);
        List<String> menuNames = new ArrayList<>();

        for(Integer i : randomMenuIndex) {
            WebElement click= this.getBrowser().findElement(By.xpath("//input[@id='mainSub_20']"));
            js.executeScript("arguments[0].click()",click);
            //mnList.get(i).click();
            menuNames.add(mnList.get(i).getText());
        }
        return menuNames;
    }

    public List<String> randomClientClick() {
        js = (JavascriptExecutor) this.getBrowser();
        js.executeScript("window.scrollTo(0, 900);");
        List<WebElement> clList = this.getBrowser().findElements(clientList);
        List<String> clNames = new ArrayList<>();

        for(Integer i : randomClientIndex) {
            WebElement click = this.getBrowser().findElement(By.xpath("//input[@id='main_93']"));
            js.executeScript("arguments[0].click()",click);
            //clList.get(i).click();
            clNames.add(clList.get(i).getText());
        }
        return clNames;
    }

    public void clickOnSaveBtn() {
        WebElement click = this.getBrowser().findElement(saveBtn);
        js.executeScript("arguments[0].click()",click);
//        js.executeScript("window.scrollTo(0, 900);");
//        this.getBrowser().findElement(saveBtn).click();
    }

    public void testQaMenuAccess() {
        userInfoObject = new UserInfoObject();
        List<WebElement> actual = this.getBrowser().findElements(menuList);
        boolean allValuesFound = true;
        for (String menuCheckBox : userInfoObject.menuCheckBox) {
            boolean valueFound = false;
            for (WebElement element : actual) {
                String elementText = element.getText();
                if (elementText.contains(menuCheckBox)) {
                    valueFound = true;
                    break;
                }
            }

            if (!valueFound) {
                allValuesFound = false;
                break;
            }
        }
        Assert.assertTrue(allValuesFound);
    }
}




