package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorMethods {
    WebDriver jsDriver;

    public JavaScriptExecutorMethods(WebDriver jsDriver){
        this.jsDriver=jsDriver;
    }

    public boolean clickWebElement(WebElement element) {
        boolean result = true;
        JavascriptExecutor jsExec = (JavascriptExecutor) jsDriver;
        try {
            jsExec.executeScript("arguments[0].click()", element);
        }
        catch (Exception e){
            result = false;
        }
        return result;
    }

    public boolean scrollWebElement(int x, int y) {
        boolean result = true;
        JavascriptExecutor jsExec = (JavascriptExecutor) jsDriver;
        try {
            jsExec.executeScript("window.scrollTo(x, y);");
        }
        catch (Exception e){
            result = false;
        }
        return result;
    }
    public boolean scrollIntoViewWebElement(WebElement element) {
        boolean result = true;
        JavascriptExecutor jsExec = (JavascriptExecutor) jsDriver;
        try {
            jsExec.executeScript("arguments[0].scrollIntoView(true);", element);
        }
        catch (Exception e){
            result = false;
        }
        return result;
    }
}
