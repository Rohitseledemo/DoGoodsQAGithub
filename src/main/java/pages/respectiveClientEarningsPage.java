package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class respectiveClientEarningsPage extends BasePage{
    By clientTitle;
    By earningsIcon;
    public respectiveClientEarningsPage(){
        this.clientTitle=By.xpath("//h3[@class='col-5']");
        this.earningsIcon=By.xpath("//tbody/tr/td//a[@class='earning']");
    }
    public String verifyClientName(){
        return this.getBrowser().findElement(clientTitle).getText();
    }
    public void randomClickOnEarningsIcons(){
        List<WebElement> earningIconsList= this.getBrowser().findElements(earningsIcon);
        Random random = new Random();
        this.getBrowser().findElements(earningsIcon).get(random.nextInt(earningIconsList.size())).click();
    }


}
