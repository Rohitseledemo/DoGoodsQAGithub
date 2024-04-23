package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import userInputObjects.UserInfoObject;

import java.util.*;

public class UserInformationPage extends BasePage {
    WebElement saveBtnWebElement;
    WebElement clientIndexElement;
    List<WebElement> menuClickWebElements;
    List<WebElement> clientClickWebElements;

    public List<String> menuNames;
    public List<String> clNames;

    UserInfoObject userInfoObject;

    JavascriptExecutor js;

    By clientList;
    By menuList;
    By saveBtn;

      Set<Integer> randomMenuIndex;
      Set<Integer> randomClientIndex;

    public UserInformationPage() {
        this.clientList = By.xpath("//input[@name='user[accessible_customer][]']");
        this.menuList = By.xpath("//input[@class='form-check-input child child_1']");
        this.saveBtn = By.xpath("//button[@type='submit']");
    }

    public void getRandomIndexes(int count) {
        this.randomMenuIndex = new HashSet<>();
        this.randomClientIndex = new HashSet<>();
        Random random = new Random();
        while(randomMenuIndex.size()<=count){
            this.randomMenuIndex.add(random.nextInt(15));
        }
        while(randomClientIndex.size()<=count){
            this.randomClientIndex.add(random.nextInt(80));
        }
    }

    public List<String> randomMenuClick() {
        getRandomIndexes(3);
        js = (JavascriptExecutor) this.getBrowser();
        menuClickWebElements = this.getBrowser().findElements(menuList);
        for (WebElement menuCheckbox : menuClickWebElements) {
            if (menuCheckbox.isSelected()) {
                js.executeScript("arguments[0].click()", menuCheckbox);
            }
        }
        menuNames = new ArrayList<String>();

        for(int i=0;i<randomMenuIndex.size();i++) {
                WebElement menuIndexElement = menuClickWebElements.get(i);
                js.executeScript("arguments[0].scrollIntoView(true);", menuIndexElement);
                js.executeScript("arguments[0].click()", menuIndexElement);
                Object s= js.executeScript("arguments[0].innerText()",menuIndexElement);
                String s1 = s.toString();
                System.out.println(s1);
                menuNames.add(menuIndexElement.getText());
            }
        return menuNames;
    }

    public List<String> randomClientClick() {
        getRandomIndexes(3);
        clientClickWebElements = this.getBrowser().findElements(clientList);
        for (WebElement clientCheckbox : clientClickWebElements) {
            if (clientCheckbox.isSelected()){
                jsExecutor.executeScript("arguments[0].click()", clientCheckbox);
            }
        }
        clNames = new ArrayList<>();

        for(Integer i : randomClientIndex) {
            clientIndexElement = clientClickWebElements.get(i);
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", clientIndexElement);
            jsExecutor.executeScript("arguments[0].click()", clientIndexElement);
                clNames.add(clientIndexElement.getText());
            }
        return clNames;
    }

    public void clickOnSaveBtn() {
        saveBtnWebElement = this.getBrowser().findElement(saveBtn);
        jsExecutor.executeScript("arguments[0].click()", saveBtnWebElement);
//        javaScriptExecutor.executeScript("window.scrollTo(0, 900);");
//        this.getBrowser().findElement(saveBtn).saveBtnWebElement();
    }

//    public void testQaMenuAccess() {
//        userInfoObject = new UserInfoObject();
//        List<WebElement> actual = this.getBrowser().findElements(menuList);
//        boolean allValuesFound = true;
//        for (String menuCheckBox : userInfoObject.menuCheckBox) {
//            boolean valueFound = false;
//            for (WebElement element : actual) {
//                String elementText = element.getText();
//                if (elementText.contains(menuCheckBox)) {
//                    valueFound = true;
//                    break;
//                }
//            }
//
//            if (!valueFound) {
//                allValuesFound = false;
//                break;
//            }
//        }
//        Assert.assertTrue(allValuesFound);
//    }


}




