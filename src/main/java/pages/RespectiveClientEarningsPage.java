package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


import java.text.DecimalFormat;
import java.util.*;


public class RespectiveClientEarningsPage extends BasePage{
    DecimalFormat df;

    WebElement dateDropdownElement;
    WebElement regenerateConfirmBtnElement;

    List<WebElement> dateOptions;
    List<WebElement> accountOptions;

    JavascriptExecutor js;
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
    By avgSavingsPerPkgBtn;
    By dataValueGroundBtn;
    By dataValueFedExReturnBtn;
    By pageNavigationBtns;
    By rateNegotiationBtn;
    By rateNegotiationAgreedDatePicker;
    By viewDetailsBtn;
    By downloadViewBtn;
    By showEntriesBtn;
    By lastCountOfInvoice;
    By allServicesNames;
    By allServicesCount;
    By currentActiveClass;
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
        this.avgSavingsPerPkgBtn=By.cssSelector("div[id='package_cost_savings'] h6");
        this.dataValueGroundBtn=By.xpath("//a[@data-val='Ground']");
        this.dataValueFedExReturnBtn=By.xpath("//a[@data-val='FedEx Returns']");
        this.pageNavigationBtns =By.xpath("//nav[@aria-label='Page navigation']//li//a");
        this.rateNegotiationBtn=By.xpath("//a[@data-bs-target='#ratenagotiation']");
        this.rateNegotiationAgreedDatePicker=By.id("agreedatepicker");
        this.viewDetailsBtn=By.id("viewDetails");
        this.showEntriesBtn=By.xpath("//select[@name='earnings_list_length']");
        this.downloadViewBtn=By.xpath("//button[@fdprocessedid='yfhjon']");
        this.lastCountOfInvoice=By.xpath("//tbody//tr// td[@valign='top'][1]");
        this.allServicesNames=By.xpath("//table[@id='avgerning']//tbody//tr//td[1]");
        this.allServicesCount=By.xpath("//table[@id='avgerning']//tbody//tr//td[2]");
        this.currentActiveClass=By.xpath("//li[(@class='active')]//a");
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
    public boolean viewDetailsBtnVerify(){
        WebElement viewDetailsBtnElement = this.getBrowser().findElement(viewDetailsBtn);
        this.driver.waitForVisibilityOfWebElement(4,viewDetailsBtnElement);
        jsExecutor.executeScript("arguments[0].click()",viewDetailsBtnElement);
        this.driver.waitForPresenceOfElement(10,showEntriesBtn);
        return this.getBrowser().findElement(showEntriesBtn).isDisplayed();
    }

    public void pageNavigation(int pageNumber){
        List<WebElement> pageNavigationBtnElements= this.getBrowser().findElements(pageNavigationBtns);
    }

    public void clickOnAccountNumberBtnOnRight(){
        this.driver.waitForPresenceOfElement(4,accountNumberBtn);
        this.getBrowser().findElement(accountNumberBtn).click();
    }
    public void clickAccountBtnSubmitBtn(){
        WebElement accountBtnSubmitBtnElement = this.getBrowser().findElement(accountBtnSubmitBtn);
        jsExecutor.executeScript("arguments[0].click()",accountBtnSubmitBtnElement);
    }

    public int countAccountBtnAccountNames(){
        this.driver.waitForPresenceOfElement(4,accountNamesTextBox);
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



    public void groundEntriesCount() {
        String actualCount = null;
        this.driver.waitForPresenceOfElement(6, dataValueGroundBtn);
        jsExecutor.executeScript("arguments[0].click()", this.getBrowser().findElement(dataValueGroundBtn));
        this.driver.waitForPresenceOfElement(4, currentActiveClass);

        WebElement currentActiveClassWebElement = this.getBrowser().findElement(currentActiveClass);
        String currentPageNo = currentActiveClassWebElement.getText();
        System.out.println("currentPageNo - " + currentActiveClassWebElement.getText());

            if (currentPageNo.equalsIgnoreCase("1")) {
                this.driver.waitForVisibilityOfWebElement(4, currentActiveClassWebElement);
                List<WebElement> lastBtnWebElement = this.getBrowser().findElements(pageNavigationBtns);
                int indexEle = lastBtnWebElement.size() - 1;
                WebElement ele = lastBtnWebElement.get(indexEle);
                jsExecutor.executeScript("arguments[0].click()", ele);
        }
            WebElement updatedActiveClassWebElement = this.getBrowser().findElement(By.xpath("//li[(@class='active')]//a"));
            String updatedPageNo = updatedActiveClassWebElement.getText();
            System.out.println("updatedPageNo"+ updatedActiveClassWebElement.getText());

             if (!currentPageNo.equalsIgnoreCase(updatedPageNo)){
                this.driver.waitForVisibilityOfWebElement(6, updatedActiveClassWebElement);
                List<WebElement> lastCountOfInvoiceElementsList = this.getBrowser().findElements(lastCountOfInvoice);
                int invoiceIndex = lastCountOfInvoiceElementsList.size() - 1;
                WebElement lastCountOfInvoiceElement = this.getBrowser().findElements(lastCountOfInvoice).get(invoiceIndex);
                System.out.println("actual value - " + lastCountOfInvoiceElement.getText());
                actualCount = lastCountOfInvoiceElement.getText();
                 }

//            return actualCount;

    }

    public int getServiceIndexByName(String serviceName){
        int index=0;
        this.driver.waitForPresenceOfElement(4,dataValueGroundBtn);
        WebElement visibilityEle =this.getBrowser().findElement(dataValueGroundBtn);
        this.driver.waitForVisibilityOfWebElement(4,visibilityEle);
        this.driver.waitForPresenceOfElement(4,allServicesNames);
        List<WebElement> allServicesElements = this.getBrowser().findElements(allServicesNames);
        for (int i=0;i<allServicesElements.size();i++){
            String serviceText =allServicesElements.get(i).getText();
            if (serviceName.equalsIgnoreCase(serviceText)){
                  index = i;
            }
        }
        return index;
    }
    public String getService_FedExReturnsCount(){
        int index = getServiceIndexByName("FedEx Returns");
        List<WebElement> allServicesCountElements = this.getBrowser().findElements(allServicesCount);
        return allServicesCountElements.get(index).getText();
    }
    public String getService_GroundCount(int index){
        this.driver.waitForPresenceOfElement(4,allServicesCount);
        List<WebElement> allServicesCountElements = this.getBrowser().findElements(allServicesCount);
        System.out.println("expected value - "+allServicesCountElements.get(index).getText());
        return allServicesCountElements.get(index).getText();
    }


    public void averageSavingsPkgDataCardClick(){
        this.driver.waitForPresenceOfElement(4,avgSavingsPerPkgBtn);
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(avgSavingsPerPkgBtn));
    }

    public void randomClickOnEarningsIcons(){
        List<WebElement> earningIconsList= this.getBrowser().findElements(earningsIcons);
        Random random = new Random();
        this.getBrowser().findElements(earningsIcons).get(random.nextInt(earningIconsList.size())).click();
    }

    public void generateEarningsBtnClick(){
        this.driver.waitForPresenceOfElement(4,generateEarningsBtn);
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(generateEarningsBtn));
    }

    public void datePickerHandler(String dateValue, String yearValue){
        WebElement datePickerElement = this.getBrowser().findElement(datePicker);
        this.driver.waitForVisibilityOfWebElement(4,datePickerElement);

        Actions ac = new Actions(this.getBrowser());
        ac.moveToElement(datePickerElement).click().build().perform();

        this.driver.waitForPresenceOfElement(2,monthSelectDropdown);
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
        WebElement getEarningIcon;
        jsExecutor.executeScript("window.scrollTo(0, 800);");
        this.driver.waitForPresenceOfElement(4,earningsIcons);
        getEarningIcon = this.getBrowser().findElements(earningsIcons).get(dateIndex);
        jsExecutor.executeScript("arguments[0].click()", getEarningIcon);
    }
    public void clickOnEarningsIcon(int index){
        WebElement getEarningIcon;
        jsExecutor.executeScript("window.scrollTo(0, 800);");
        this.driver.waitForPresenceOfElement(4,earningsIcons);
        getEarningIcon = this.getBrowser().findElements(earningsIcons).get(index);
        jsExecutor.executeScript("arguments[0].click()", getEarningIcon);
    }

    public boolean generateEarningsNoDataFound(){
        WebElement noDataFoundTxtWebElement = this.getBrowser().findElement(noDataFoundTxt);
        this.driver.waitForVisibilityOfWebElement(4,noDataFoundTxtWebElement);
        return noDataFoundTxtWebElement.isDisplayed();
    }
    public boolean generateEarningsRegeneratePopup(){
        WebElement regenerateConfirmBtnElement = this.getBrowser().findElement
                (By.xpath("//button[@class='btn btn-primary confirm_btn']"));
        this.driver.waitForVisibilityOfWebElement(4, regenerateConfirmBtnElement);
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
        try{
            this.driver.waitForVisibilityOfWebElement(4,regenerateConfirmBtnElement);
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

