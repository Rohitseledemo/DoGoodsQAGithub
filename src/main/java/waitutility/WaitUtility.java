package waitutility;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base_Library.Base_Class;

public class WaitUtility extends Base_Class
{
public static final int WAIT = 5;
	
	public static void impliciwait(int time)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		
	}
	public static void waitForVisibility(WebElement ele)
	 {
	    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(WAIT));
	    wait.until(ExpectedConditions.visibilityOf(ele));
	 }

	 public static void alertpresent()
	 {
	    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(WAIT));
	    wait.until(ExpectedConditions.alertIsPresent());
	 }
	
	 public static void click(WebElement ele)
	 {
	 	waitForVisibility(ele);
	 	ele.click();
	 }
	 public static void sendkeys(WebElement ele, String text)
	 {
	 	waitForVisibility(ele);
	 	ele.sendKeys(text); 	
	 }
	 public static void clear(WebElement ele)
	 {
	 	waitForVisibility(ele);
	 	ele.clear();
	 }

	
	
	
	
	
}
