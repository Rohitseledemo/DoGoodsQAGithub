package userInputObjects;

import doGoodsQaPages.UserInformationPage;
import java.util.List;

public class UserInfoObject {
    UserInformationPage userInformationPage;

    public List<String> menuCheckBox;
    public List<String> clientCheckBox;


   public List<String> setMenuValues(){
        userInformationPage = new UserInformationPage();
        return this.menuCheckBox = userInformationPage.randomMenuClick();
}

    public List<String> setClientValues(){
        userInformationPage = new UserInformationPage();
        return this.clientCheckBox = userInformationPage.randomClientClick();
   }

}
