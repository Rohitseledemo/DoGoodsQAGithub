package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
        this.clientList = By.xpath("//li[@class='mb-1 form-check']//label[@class='form-check-label']");
        this.menuList = By.xpath("//li[@class='ml-4 mb-1 form-check']//label[@class='form-check-label']");
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
            this.randomClientIndex.add(random.nextInt(90));
        }
    }

    public List<String> randomMenuClick() {
        getRandomIndexes(3);
        List <WebElement> menuClickCheckboxes = this.getBrowser().findElements(By.xpath("//label[@class='form-check-label'] //input[@class='form-check-input child child_1']"));
        menuClickWebElements=this.getBrowser().findElements(menuList);

        for (WebElement menuCheckbox : menuClickCheckboxes) {
            if (menuCheckbox.isSelected()) {
                jsExecutor.executeScript("arguments[0].click()", menuCheckbox);
            }
        }
        menuNames = new ArrayList<String>();
        for(int i :randomMenuIndex) {
                WebElement menuIndexElement = menuClickWebElements.get(i);
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", menuIndexElement);
                jsExecutor.executeScript("arguments[0].click()", menuIndexElement);
                menuNames.add(menuIndexElement.getText().toUpperCase());
            }
        return menuNames;
    }

    public List<String> randomClientClick() {
        getRandomIndexes(3);
        List <WebElement> clientClickCheckboxes = this.getBrowser().findElements(By.xpath("//input[@class='form-check-input parent customer-select']"));
        clientClickWebElements = this.getBrowser().findElements(clientList);
        for (WebElement clientCheckbox : clientClickCheckboxes) {
            if (clientCheckbox.isSelected()){
                jsExecutor.executeScript("arguments[0].click()", clientCheckbox);
            }
        }
        clNames = new ArrayList<>();
        for(int i : randomClientIndex) {
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
    }

}




