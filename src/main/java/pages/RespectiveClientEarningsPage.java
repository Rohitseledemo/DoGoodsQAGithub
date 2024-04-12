package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.apache.commons.collections4.MapUtils;
import org.testng.Assert;


import java.util.*;


public class RespectiveClientEarningsPage extends BasePage{
    WebElement getEarningIcon;
    JavascriptExecutor js;
    Map<String,String> map1;
    Map<String,String> map2;
    int index = 0;

    By clientTitle;
    By earningsIcons;
    By dates;
    By dateValues;
    By selectDropdown;
    By totalSavings;


    public RespectiveClientEarningsPage(){
        this.clientTitle=By.xpath("//h3[@class='col-5']");
        this.earningsIcons=By.xpath("//tbody/tr/td//a[@class='earning']");
        this.dates=By.xpath("//tbody//tr//td[2]");
        this.dateValues=By.xpath("//tbody//tr//td[6]");
        this.selectDropdown=By.id("yearMonth");
        this.totalSavings=By.id("total_savings");
    }
    public String verifyClientName(){
        return this.getBrowser().findElement(clientTitle).getText();
    }
    public void randomClickOnEarningsIcons(){
        List<WebElement> earningIconsList= this.getBrowser().findElements(earningsIcons);
        Random random = new Random();
        this.getBrowser().findElements(earningsIcons).get(random.nextInt(earningIconsList.size())).click();
    }
    public void clickOnEarningsIcon(){
        js =(JavascriptExecutor)this.getBrowser();
        js.executeScript("window.scrollTo(0, 800);");
        this.driver.waitForPresenceOfElement(4,earningsIcons);
        getEarningIcon = this.getBrowser().findElements(earningsIcons).get(index);
        js.executeScript("arguments[0].click()", getEarningIcon);
    }
    public void compareValues(){
        String date = this.getBrowser().findElements(dates).get(index).getText();
        String dateValue = this.getBrowser().findElements(dateValues).get(index).getText();

        map1 =  new HashMap<String, String>();
        map1.put(date,dateValue);

        clickOnEarningsIcon();

        WebElement dropdownElements=this.getBrowser().findElement(selectDropdown);
        List<WebElement> options = dropdownElements.findElements(By.tagName("option"));

        options.stream().filter(option -> option.getText().equals(date))
                .findFirst().ifPresent(WebElement::click);

        String internalDate= options.stream()
                .filter(option -> option.getText().equals(date))
                .findFirst().get().getText();

        String internalDateValue= this.getBrowser().findElement(totalSavings).getText();
        map2 = new HashMap<String, String>();
        map2.put(internalDate,internalDateValue);

        // Comparing keys
        boolean keysEqual = map1.keySet().equals(map2.keySet());
        Assert.assertTrue(keysEqual);

        // Comparing values
        boolean valuesEqual = map1.entrySet().stream()
                .allMatch(e -> e.getValue().equals(map2.get(e.getKey())));
        Assert.assertTrue(valuesEqual);
    }


}
