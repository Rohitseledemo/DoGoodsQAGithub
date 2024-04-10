package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import userInputObjects.UserInfoObject;

import java.util.ArrayList;
import java.util.List;

public class UserInformationPage extends BasePage {
    UserInfoObject userInfoObject;

    JavascriptExecutor js;
    RandomGenerator randomGenerator;

    By clientList;
    By menuList;
    By saveBtn;

    public UserInformationPage() {
        this.clientList = By.xpath("//div[@class='col-sm-4']");
        this.menuList = By.xpath("//input[@class='form-check-input child child_1']");
        this.saveBtn = By.xpath("//button[@type='submit']");
    }

    public List<Integer> getRandomMenuIndex() {
        randomGenerator = new RandomGenerator();
        return randomGenerator.generateRandomIndexes(2);
    }

    public List<Integer> getRandomClientIndex() {
        randomGenerator = new RandomGenerator();
        return randomGenerator.generateRandomIndexes(2);
    }

    public List<String> randomMenuClick() {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 500);");
        List<WebElement> mnList = this.getBrowser().findElements(menuList);
        List<String> menuNames = new ArrayList<>();
        for (int i = 0; i <= getRandomMenuIndex().size(); i++) {
            mnList.get(i).click();
            menuNames.add(mnList.get(i).getText());
        }
        return menuNames;
    }

    public List<String> randomClientClick() {
        js = (JavascriptExecutor) this.getBrowser();
        js.executeScript("window.scrollTo(0, 900);");
        List<WebElement> clList = this.getBrowser().findElements(clientList);
        List<String> clNames = new ArrayList<>();
        for (int i = 0; i <= getRandomClientIndex().size(); i++) {
            clList.get(i).click();
            clNames.add(clList.get(i).getText());
        }
        return clNames;
    }

    public void clickOnSaveBtn() {
        this.getBrowser().findElement(saveBtn).click();
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




