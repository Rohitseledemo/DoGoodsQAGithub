package test;

import org.testng.annotations.Test;
import pages.ForgotPasswordPage;

public class ForgotPasswordPagePageTest {
    ForgotPasswordPage forgotPasswordPage;
    @Test
    public void resetPassword(){
        forgotPasswordPage.setEmailForgot("");
    }
    @Test
    public void returnToLoginCheck(){
        forgotPasswordPage.returnToLoginClick();
    }
}
