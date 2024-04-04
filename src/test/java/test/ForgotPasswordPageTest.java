package test;

import org.testng.annotations.Test;
import pages.ForgotPassword;

public class ForgotPasswordPageTest {
    ForgotPassword forgotPassword;
    @Test
    public void resetPassword(){
        forgotPassword.setEmailForgot("");
    }
    @Test
    public void returnToLoginCheck(){
        forgotPassword.returnToLoginClick();
    }
}
