package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utility.WebDriverWaits;

import java.util.*;


public class RespectiveClientEarningsPage extends BasePage{
    WebDriverWaits wait;

    WebElement dateDropdownElement;
    WebElement regenerateConfirmBtnElement;

    List<WebElement> dateOptions;
    List<WebElement> accountOptions;

    int dateIndex;

    Map<String,String> map1;
    Map<String,String> map2;

    By earningsIcons;
    By dates;
    By dateValues;
    By dateDropdown;
    By accountDropdown;
    By totalPrior;
    By totalCurrent;
    By totalSavings;
    By rateNegotiationBtn;
    By rateNegotiationAgreedDatePicker;
    By accountNumberBtn;
    By accountNamesTextBox;
    By invoiceByAccountCheckbox;
    By accountBtnSubmitBtn;


    public RespectiveClientEarningsPage(){
        this.earningsIcons=By.xpath("//tbody/tr/td//a[@class='earning']");
        this.dates=By.xpath("//div[@id='earningDetails'] //tbody//tr//td[2]");
        this.dateValues=By.xpath("//div[@id='earningDetails'] //tbody//tr//td[6]");
        this.dateDropdown =By.xpath("//select[@id='yearMonth']");
        this.accountDropdown=By.xpath("//select[@id='account_number']");
        this.totalPrior=By.cssSelector("div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h4:nth-child(1)");
        this.totalCurrent=By.cssSelector("div[id='total_currentinvoice'] h4[class='text-secondary']");
        this.totalSavings=By.cssSelector("h4[id='total_savings']");
        this.rateNegotiationBtn=By.xpath("//a[@data-bs-target='#ratenagotiation']");
        this.rateNegotiationAgreedDatePicker=By.id("agreedatepicker");
        this.accountNumberBtn=By.xpath("//div[@class='pull-right']//a[text()='Account Number']");
        this.accountNamesTextBox=By.xpath("//th[@class='text-center']//input[@type='text']");
        this.invoiceByAccountCheckbox=By.xpath("//input[@id='invoice_by_account']");
        this.accountBtnSubmitBtn=By.xpath("//button[@type='Submit']");

    }
    public String verifyClientName(){
        return this.getBrowser().findElement(clientTitle).getText();
    }
    public void rateNegotiationBtnClick(){
        this.getBrowser().findElement(rateNegotiationBtn).click();
    }
    public void rateNegotiationDateParser(){

    }

    public void clickOnAccountNumberBtnOnRight(){
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4,accountNumberBtn);
        this.getBrowser().findElement(accountNumberBtn).click();
    }
    public void clickAccountBtnSubmitBtn(){
        WebElement accountBtnSubmitBtnElement = this.getBrowser().findElement(accountBtnSubmitBtn);
        jsExecutor.executeScript("arguments[0].click()",accountBtnSubmitBtnElement);
    }

    public int countAccountBtnAccountNames(){
        wait.waitForPresenceOfElement(4,accountNamesTextBox);

        int nameCount = 0;
        List<WebElement> accountNames=this.getBrowser().findElements(accountNamesTextBox);
        for (WebElement accountName : accountNames) {
            String val = accountName.getAttribute("value");
            if (val != null) {
                nameCount++;
            }
        }
        return nameCount;

    }
    public void clickInvoiceByAccountCheckbox(){
        WebElement invoiceByAccountCheckboxElement = this.getBrowser().findElement(invoiceByAccountCheckbox);
        if (!invoiceByAccountCheckboxElement.isSelected()) {
            jsExecutor.executeScript("arguments[0].click()", invoiceByAccountCheckboxElement);
        }
    }

    public void randomClickOnEarningsIcons(){
        List<WebElement> earningIconsList= this.getBrowser().findElements(earningsIcons);
        Random random = new Random();
        this.getBrowser().findElements(earningsIcons).get(random.nextInt(earningIconsList.size())).click();
    }

    public void generateEarningsBtnClick(){
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4,generateEarningsBtn);
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(generateEarningsBtn));
    }

    public void datePickerHandler(String dateValue, String yearValue){
        wait = new WebDriverWaits(this.getBrowser());

        WebElement datePickerElement = this.getBrowser().findElement(datePicker);
        wait.waitForVisibilityOfWebElement(4,datePickerElement);

        Actions ac = new Actions(this.getBrowser());
        ac.moveToElement(datePickerElement).click().build().perform();

        wait.waitForPresenceOfElement(2,monthSelectDropdown);
        monthSelectDropdownElement = this.getBrowser().findElement(monthSelectDropdown);
        Select monthSelect = new Select(monthSelectDropdownElement);
        monthSelect.selectByVisibleText(dateValue);

        yearSelectDropdownElement = this.getBrowser().findElement(yearSelectDropdown);
        Select yearSelect = new Select(yearSelectDropdownElement);
        yearSelect.selectByVisibleText(yearValue);

        WebElement doneBtnElement = this.getBrowser().findElement(doneBtn);
        jsExecutor.executeScript("arguments[0].click()", doneBtnElement);

        WebElement goBtnElement = this.getBrowser().findElement(goBtn);
        jsExecutor.executeScript("arguments[0].click()", goBtnElement);
    }

    public void clickOnEarningsIcon(){
        wait = new WebDriverWaits(this.getBrowser());

        WebElement getEarningIcon;
        jsExecutor.executeScript("window.scrollTo(0, 800);");
        wait.waitForPresenceOfElement(4,earningsIcons);
        getEarningIcon = this.getBrowser().findElements(earningsIcons).get(dateIndex);
        jsExecutor.executeScript("arguments[0].click()", getEarningIcon);
    }
    public void clickOnEarningsIcon(int index){
        wait = new WebDriverWaits(this.getBrowser());

        WebElement getEarningIcon;
        jsExecutor.executeScript("window.scrollTo(0, 800);");
        wait.waitForPresenceOfElement(4,earningsIcons);
        getEarningIcon = this.getBrowser().findElements(earningsIcons).get(index);
        jsExecutor.executeScript("arguments[0].click()", getEarningIcon);
    }

    public boolean generateEarningsNoDataFound(){
        wait = new WebDriverWaits(this.getBrowser());

        WebElement noDataFoundTxtWebElement = this.getBrowser().findElement(noDataFoundTxt);
        wait.waitForVisibilityOfWebElement(4,noDataFoundTxtWebElement);
        return noDataFoundTxtWebElement.isDisplayed();
    }
    public boolean generateEarningsRegeneratePopup(){
        wait = new WebDriverWaits(this.getBrowser());

        WebElement regenerateConfirmBtnElement = this.getBrowser().findElement
                (By.xpath("//button[@class='btn btn-primary confirm_btn']"));
        wait.waitForVisibilityOfWebElement(4, regenerateConfirmBtnElement);
        return regenerateConfirmBtnElement.isDisplayed();
    }
    public boolean generateEarningsAgreementDatePopup(){
        try {
             this.getBrowser().switchTo().alert().accept();
             return true;
        }
        catch (NoAlertPresentException e) {
            System.out.println("No alert present on the webpage.");
            return false;
        }
    }
    public boolean generateEarningsSuccessfully(){
        wait = new WebDriverWaits(this.getBrowser());

        try{
            wait.waitForVisibilityOfWebElement(4,regenerateConfirmBtnElement);
            this.getBrowser().findElement(regenerateConfirmBtn).click();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public void generateRandomIndex(){
        Random rand = new Random();
        this.dateIndex = rand.nextInt(2);
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


}

