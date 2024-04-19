package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


import java.text.DecimalFormat;
import java.util.*;


public class RespectiveClientEarningsPage extends BasePage{
    DecimalFormat df;

    WebElement dateDropdownElement;

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
    By avgSavingsPerPkgBtn;
    By dataValueGroundBtn;
    By dataValueFedExReturnBtn;
    By lastBtn;
    By rateNegotiationBtn;
    By rateNegotiationAgreedDatePicker;
    By viewDetailsBtn;
    By downloadViewBtn;
    By showEntriesBtn;
    By lastCountOfInvoice;


    public RespectiveClientEarningsPage(){
        this.clientTitle=By.xpath("//h3[@class='col-5']");
        this.earningsIcons=By.xpath("//tbody/tr/td//a[@class='earning']");
        this.dates=By.xpath("//div[@id='earningDetails'] //tbody//tr//td[2]");
        this.dateValues=By.xpath("//div[@id='earningDetails'] //tbody//tr//td[6]");
        this.dateDropdown =By.xpath("//select[@id='yearMonth']");
        this.accountDropdown=By.xpath("//select[@id='account_number']");
        this.totalPrior=By.cssSelector("div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h4:nth-child(1)");
        this.totalCurrent=By.cssSelector("div[id='total_currentinvoice'] h4[class='text-secondary']");
        this.totalSavings=By.cssSelector("h4[id='total_savings']");
        this.avgSavingsPerPkgBtn=By.cssSelector("div[id='package_cost_savings'] h6");
        this.dataValueGroundBtn=By.xpath("//a[@data-val='Ground']");
        this.dataValueFedExReturnBtn=By.xpath("//a[@data-val='FedEx Returns']");
        this.lastBtn=By.xpath("(//li)[last()]");
        this.rateNegotiationBtn=By.xpath("//a[@data-bs-target='#ratenagotiation']");
        this.rateNegotiationAgreedDatePicker=By.id("agreedatepicker");
        this.viewDetailsBtn=By.id("viewDetails");
        this.showEntriesBtn=By.xpath("//select[@name='earnings_list_length']");
        this.downloadViewBtn=By.xpath("//button[@fdprocessedid='yfhjon']");
        this.lastCountOfInvoice=By.xpath("(//tbody//tr)[last()][1]");
    }
    public String verifyClientName(){
        return this.getBrowser().findElement(clientTitle).getText();
    }
    public void rateNegotiationBtnClick(){
        this.getBrowser().findElement(rateNegotiationBtn).click();
    }
    public void rateNegotiationDateParser(){

    }
    public void viewDetailsBtnVerify(){
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(viewDetailsBtn));
        this.driver.waitForPresenceOfElement(6,showEntriesBtn);
        Assert.assertTrue(this.getBrowser().findElement(showEntriesBtn).isDisplayed());
    }

    public String groundEntriesCount(){
        this.driver.waitForPresenceOfElement(6,dataValueGroundBtn);
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(dataValueGroundBtn));
        this.driver.waitForPresenceOfElement(4,lastBtn);
        Actions ac = new Actions(this.getBrowser());
        WebElement lastBtnElement = this.getBrowser().findElement(lastBtn);
        ac.click(lastBtnElement);
//        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(lastBtn));
        this.driver.waitForPresenceOfElement(4,lastCountOfInvoice);
        return this.getBrowser().findElement(lastCountOfInvoice).getText();
    }
    public void averageSavingsPkgDataCardClick(){
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(avgSavingsPerPkgBtn));
    }

    public void randomClickOnEarningsIcons(){
        List<WebElement> earningIconsList= this.getBrowser().findElements(earningsIcons);
        Random random = new Random();
        this.getBrowser().findElements(earningsIcons).get(random.nextInt(earningIconsList.size())).click();
    }

    public void generateEarningsBtnClick(){
        this.driver.waitForPresenceOfElement(4,generateEarningsBtn);
        this.getBrowser().findElement(generateEarningsBtn);
    }

    public void clickOnEarningsIcon(){
        WebElement getEarningIcon;
        js =(JavascriptExecutor)this.getBrowser();
        js.executeScript("window.scrollTo(0, 800);");
        this.driver.waitForPresenceOfElement(4,earningsIcons);
        getEarningIcon = this.getBrowser().findElements(earningsIcons).get(dateIndex);
        js.executeScript("arguments[0].click()", getEarningIcon);
    }
    public void generateEarningsValidate(int testCase) {
        switch (testCase) {
            case 1:
                System.out.println("No Data found in given period testCase 1");
                generateEarningsHandler(3, "2024");
                boolean noDataFoundText = this.getBrowser().findElement(By.xpath("//div[@class" +
                        "='callout callout-danger" + " text-danger']")).isDisplayed();
                Assert.assertTrue(noDataFoundText);
                break;

            case 2:
                System.out.println("regenerate pop-up testCase 2");
                generateEarningsHandler(2, "2024");
                boolean regenerate = this.getBrowser().findElement(regenerateConfirmBtn).isDisplayed();
                this.getBrowser().findElement(By.xpath("//button[@fdprocessedid='edqf6k']")).click();
                Assert.assertTrue(regenerate);
                break;

            case 3:
                System.out.println("Agreement date pop-up testCase3");
                generateEarningsHandler(2, "2019");
                String agreementExpected = "You cannot select date before agreement.";
                String agreementActual = this.getBrowser().switchTo().alert().getText();
                Assert.assertEquals(agreementActual, agreementExpected);
                break;

            case 4:
                System.out.println("generating earnings testCase 4");
                generateEarningsHandler(2, "2024");
                this.driver.waitForPresenceOfElement(4, earningsIcons);
                this.getBrowser().findElement(earningsIcons).click();
                this.driver.waitForPresenceOfElement(400, totalSavings);
                totalSavingsCalculate();
        }
    }
        public void generateEarningsTestCaseIterator(){
            for (int i=1;i<=4;i++){
                generateEarningsValidate(i);
            }
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
        WebElement accountDropdownElement;
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

