package doGoodsQaTest;

import doGoodsQaPages.ForgotPasswordPage;

public class ForgotPasswordPagePageTest {
    ForgotPasswordPage forgotPasswordPage;
   //@Test
    public void resetPassword(){
        forgotPasswordPage.setEmailForgot("");
    }
    //@Test
    public void returnToLoginCheck(){
        forgotPasswordPage.returnToLoginClick();
    }
}
