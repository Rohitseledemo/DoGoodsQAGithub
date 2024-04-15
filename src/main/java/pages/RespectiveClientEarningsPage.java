package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.text.DecimalFormat;
import java.util.*;


public class RespectiveClientEarningsPage extends BasePage{
    DecimalFormat df;

    WebElement dateDropdownElement;
    WebElement accountDropdownElement;
    WebElement getEarningIcon;

    List<WebElement> dateOptions;
    List<WebElement> accountOptions;

    JavascriptExecutor js;
    int dateIndex;

    Map<String,String> map1;
    Map<String,String> map2;

    By clientTitle;
    By earningsIcons;
    By dates;
    By dateValues;
    By dateDropdown;
    By accountDropdown;
    By totalPrior;
    By totalCurrent;
    By totalSavings;


    public RespectiveClientEarningsPage(){
        this.clientTitle=By.xpath("//h3[@class='col-5']");
        this.earningsIcons=By.xpath("//tbody/tr/td//a[@class='earning']");
        this.dates=By.xpath("//tbody//tr//td[2]");
        this.dateValues=By.xpath("//tbody//tr//td[6]");
        this.dateDropdown =By.xpath("//select[@id='yearMonth']");
        this.accountDropdown=By.xpath("//select[@id='account_number']");
        this.totalPrior=By.cssSelector("div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h4:nth-child(1)");
        this.totalCurrent=By.cssSelector("div[id='total_currentinvoice'] h4[class='text-secondary']");
        this.totalSavings=By.cssSelector("h4[id='total_savings']");
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
        getEarningIcon = this.getBrowser().findElements(earningsIcons).get(dateIndex);
        js.executeScript("arguments[0].click()", getEarningIcon);
    }

    public void generateRandomIndex(){
        Random rand = new Random();
        this.dateIndex = rand.nextInt(3);
    }

    public void compareDates(){
        generateRandomIndex();
        String date = this.getBrowser().findElements(dates).get(dateIndex).getText();
        String dateValue = this.getBrowser().findElements(dateValues).get(dateIndex).getText();

        map1 =  new HashMap<String, String>();
        map1.put(date,dateValue);

        clickOnEarningsIcon();

        dateDropdownElement =this.getBrowser().findElement(dateDropdown);
        dateOptions = dateDropdownElement.findElements(By.tagName("option"));

        dateOptions.stream().filter(option -> option.getText().equals(date))
                .findFirst().ifPresent(WebElement::click);

        String internalDate= dateOptions.stream()
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


    public void totalSavingsCalculate(){
        float a = Float.parseFloat(this.getBrowser().findElement(totalPrior).getText().
                substring(1).replace(",", ""));
        float b = Float.parseFloat(this.getBrowser().findElement(totalCurrent).getText().
                substring(1).replace(",", ""));
        float actual = Float.parseFloat(this.getBrowser().findElement(totalSavings).getText().
                substring(1).replace(",", ""));
        float c= a-b;
        df = new DecimalFormat("#.##");
        float result = Float.parseFloat(df.format(c));
        Assert.assertEquals(actual,result);
    }

    public void changeMonthAndValidateTotalSavings(){
        dateDropdownElement = this.getBrowser().findElement(dateDropdown);
        dateOptions = dateDropdownElement.findElements(By.tagName("option"));
        int dateOptionsSize = dateOptions.size();
        for (int i=1;i<dateOptionsSize-2;i++){
            dateDropdownElement = this.getBrowser().findElement(dateDropdown);
            dateOptions = dateDropdownElement.findElements(By.tagName("option"));
            dateOptions.get(i).click();
            this.driver.waitForPresenceOfElement(6,totalSavings);
            totalSavingsCalculate();
        }
    }
    public void changeAccountAndValidateTotalSavings(){
        accountDropdownElement = this.getBrowser().findElement(accountDropdown);
        accountOptions = accountDropdownElement.findElements(By.tagName("option"));
        int accountOptionSize= accountOptions.size();
        for (int i=1;i<accountOptionSize;i++){
            accountDropdownElement = this.getBrowser().findElement(accountDropdown);
            accountOptions = accountDropdownElement.findElements(By.tagName("option"));
            accountOptions.get(i).click();
            this.driver.waitForPresenceOfElement(6,totalSavings);
            totalSavingsCalculate();
        }

    }

}

