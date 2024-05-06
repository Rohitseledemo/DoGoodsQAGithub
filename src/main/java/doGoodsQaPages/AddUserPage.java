package doGoodsQaPages;

import org.openqa.selenium.By;

public class AddUserPage extends BasePage {
    By inputName;
    By emailAddress;
    By password;
    By userType;
    By activeInactive;
    By selectOption;
    By save;
    public AddUserPage(){
        this.inputName=By.id("admin_name");
    }


}
