package userInputObjects;

import pages.BasePage;
import pages.UserInformationPage;

import java.util.ArrayList;
import java.util.List;

public class UserInfoObject {
    UserInformationPage userInformationPage;

    String email,password,confirmPassword, userType, activeStatus;
    public List<String>  menuCheckBox;
    public List<String> clientCheckBox;

public UserInfoObject(){
    this.menuCheckBox=setMenuValues();
    this.clientCheckBox=setClientValues();
}
   public List<String> setMenuValues(){
    userInformationPage = new UserInformationPage();
    return userInformationPage.randomMenuClick();
}

    public List<String> setClientValues(){
    return userInformationPage.randomClientClick();
   }

}
