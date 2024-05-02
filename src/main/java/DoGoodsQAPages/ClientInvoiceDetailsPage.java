package DoGoodsQAPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.WebDriverWaits;

import java.text.DecimalFormat;
import java.util.List;

public class ClientInvoiceDetailsPage extends BasePage{
    DecimalFormat df;
    WebDriverWaits wait;

    WebElement dateDropdownElement;

    List<WebElement> dateOptions;
    List<WebElement> accountOptions;

    By dateDropdown;
    By accountDropdown;
    By totalPrior;
    By totalCurrent;
    protected By totalSavings;
    protected By avgSavingsPerPkgBtn;
    By dataValueGroundBtn;
    By dataValueFedExReturnBtn;
    By doGoodsInvoiceLink;
    By accountNumberColumn;
    By accountColumnCounts;
    By pageNavigationBtns;
    By viewDetailsBtn;
    By downloadViewBtn;
    By showEntriesBtn;
    By lastCountOfInvoice;
    By allServicesNames;
    By allServicesCount;
    By currentActiveClass;


    public ClientInvoiceDetailsPage(){
        this.dateDropdown =By.xpath("//select[@id='yearMonth']");
        this.accountDropdown=By.xpath("//select[@id='account_number']");
        this.totalPrior=By.cssSelector("div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h4:nth-child(1)");
        this.totalCurrent=By.cssSelector("div[id='total_currentinvoice'] h4[class='text-secondary']");
        this.totalSavings=By.cssSelector("h4[id='total_savings']");
        this.avgSavingsPerPkgBtn=By.cssSelector("div[id='package_cost_savings'] h6");
        this.dataValueGroundBtn=By.xpath("//a[@data-val='Ground']");
        this.dataValueFedExReturnBtn=By.xpath("//a[@data-val='FedEx Returns']");
        this.doGoodsInvoiceLink=By.cssSelector("span.text-sky");
        this.accountNumberColumn=By.xpath("//label[@class='label']//strong[text()='Account Number']");
        this.accountColumnCounts=By.xpath("//table[@class='mt-2 table table-bordered dogoods-fees']//tbody//tr//td[2]");
        this.pageNavigationBtns =By.xpath("//nav[@aria-label='Page navigation']//li//a");
        this.viewDetailsBtn=By.id("viewDetails");
        this.showEntriesBtn=By.xpath("//select[@name='earnings_list_length']");
        this.downloadViewBtn=By.xpath("//button[@fdprocessedid='yfhjon']");
        this.lastCountOfInvoice=By.xpath("//tbody//tr// td[@valign='top'][1]");
        this.allServicesNames=By.xpath("//table[@id='avgerning']//tbody//tr//td[1]//a[@class='surcharge_service']");
        this.allServicesCount=By.xpath("//table[@id='avgerning']//tbody//tr//td[2]");
        this.currentActiveClass=By.xpath("//li[(@class='active')]//a");
    }

    public boolean viewDetailsBtnVerify(){
        wait = new WebDriverWaits(this.getBrowser());

        WebElement viewDetailsBtnElement = this.getBrowser().findElement(viewDetailsBtn);
        wait.waitForVisibilityOfWebElement(4,viewDetailsBtnElement);
        jsExecutor.executeScript("arguments[0].click()",viewDetailsBtnElement);
        wait.waitForPresenceOfElement(10,showEntriesBtn);
        return this.getBrowser().findElement(showEntriesBtn).isDisplayed();
    }

    public void pageNavigation(int pageNumber){
        List<WebElement> pageNavigationBtnElements= this.getBrowser().findElements(pageNavigationBtns);
    }


    public int getServiceIndexByName(String serviceName){
        wait = new WebDriverWaits(this.getBrowser());

        int index=0;
        wait.waitForPresenceOfElement(4,dataValueGroundBtn);
        WebElement visibilityEle =this.getBrowser().findElement(dataValueGroundBtn);
        wait.waitForVisibilityOfWebElement(4,visibilityEle);
        wait.waitForPresenceOfElement(4,allServicesNames);
        List<WebElement> allServicesElements = this.getBrowser().findElements(allServicesNames);
        for (int i=0;i<allServicesElements.size();i++){
            String serviceText =allServicesElements.get(i).getText();
            if (serviceName.equalsIgnoreCase(serviceText)){
                index = i;
                break;
            }
        }
        return index;
    }

    public String getService_FedExReturnsCount(){
        int index = getServiceIndexByName("FedEx Returns");
        List<WebElement> allServicesCountElements = this.getBrowser().findElements(allServicesCount);
        return allServicesCountElements.get(index).getText();
    }
    //this function returns the service count
    public String getServiceCount(int index){
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4,allServicesCount);
        List<WebElement> allServicesCountElements = this.getBrowser().findElements(allServicesCount);
        System.out.println("expected value - "+allServicesCountElements.get(index).getText());
        return allServicesCountElements.get(index).getText();
    }
    //this function clicks on the service
    public String clickOnService(String serviceName){
        wait = new WebDriverWaits(this.getBrowser());

        String serviceClicked = null;
        int index=0;
        wait.waitForPresenceOfElement(4,dataValueGroundBtn);
        WebElement visibilityEle =this.getBrowser().findElement(dataValueGroundBtn);
        wait.waitForVisibilityOfWebElement(4,visibilityEle);
        wait.waitForPresenceOfElement(4,allServicesNames);
        List<WebElement> allServicesElements = this.getBrowser().findElements(allServicesNames);
        for (int i=0;i<allServicesElements.size();i++){
            String serviceText =allServicesElements.get(i).getText();
            if (serviceName.equalsIgnoreCase(serviceText)){
                serviceClicked = this.getBrowser().findElements(allServicesNames).get(i).getText();
                this.getBrowser().findElements(allServicesNames).get(i).click();
                break;
            }
        }
        return serviceClicked;
    }

    public void averageSavingsPkgDataCardClick(){
        wait = new WebDriverWaits(this.getBrowser());

        wait.waitForPresenceOfElement(4,avgSavingsPerPkgBtn);
        jsExecutor.executeScript("arguments[0].click()",this.getBrowser().findElement(avgSavingsPerPkgBtn));
    }

    public boolean totalSavingsCalculate(){
        boolean finalResult=false;
        float a = Float.parseFloat(this.getBrowser().findElement(totalPrior).getText().
                substring(1).replace(",", ""));
        float b = Float.parseFloat(this.getBrowser().findElement(totalCurrent).getText().
                substring(1).replace(",", ""));
        float actual = Float.parseFloat(this.getBrowser().findElement(totalSavings).getText().
                substring(1).replace(",", ""));
        float c= a-b;
        df = new DecimalFormat("#.##");
        float result = Float.parseFloat(df.format(c));
        if(actual==result && actual>=0){
             finalResult = true;
        }
        return finalResult;
    }

    public void changeMonthsByDropDown() {
        wait = new WebDriverWaits(this.getBrowser());

        boolean result = false;
        dateDropdownElement = this.getBrowser().findElement(dateDropdown);
        dateOptions = dateDropdownElement.findElements(By.tagName("option"));
        int dateOptionsSize = dateOptions.size();
        for (int i = 1; i < dateOptionsSize - 2; i++) {
            dateDropdownElement = this.getBrowser().findElement(dateDropdown);
            dateOptions = dateDropdownElement.findElements(By.tagName("option"));
            dateOptions.get(i).click();
            wait.waitForPresenceOfElement(6, totalSavings);
        }
    }


    public void changeAccountsByDropDown(){
        wait = new WebDriverWaits(this.getBrowser());

        boolean result= false;
        WebElement accountDropdownElement;
        accountDropdownElement = this.getBrowser().findElement(accountDropdown);
        accountOptions = accountDropdownElement.findElements(By.tagName("option"));
        int accountOptionSize= accountOptions.size();
        for (int i=1;i<accountOptionSize;i++) {
            accountDropdownElement = this.getBrowser().findElement(accountDropdown);
            accountOptions = accountDropdownElement.findElements(By.tagName("option"));
            accountOptions.get(i).click();
            wait.waitForPresenceOfElement(6, totalSavings);
        }
    }


    public void doGoodsInvoiceLinkClick(){
        this.getBrowser().findElement(doGoodsInvoiceLink).click();
    }

    public int getAccountNumberColumnCount(){
        wait = new WebDriverWaits(this.getBrowser());

        int count;
        wait.waitForPresenceOfElement(4,accountColumnCounts);
        wait.waitForVisibilityOfWebElement(4,this.getBrowser().findElement(accountNumberColumn));
        if(this.getBrowser().findElement(accountNumberColumn).isDisplayed()){
            return count = this.getBrowser().findElements(accountColumnCounts).size();
        }
        else {
            return count=0;
        }
    }

    public String groundEntriesCount() {
         wait = new WebDriverWaits(this.getBrowser());

        String actualCount = null;
        wait.waitForPresenceOfElement(4, currentActiveClass);

        WebElement currentActiveClassWebElement = this.getBrowser().findElement(currentActiveClass);
        String currentPageNo = currentActiveClassWebElement.getText();
        System.out.println("currentPageNo - " + currentPageNo);

            wait.waitForVisibilityOfWebElement(4, currentActiveClassWebElement);
            List<WebElement> lastBtnWebElement = this.getBrowser().findElements(pageNavigationBtns);
            int indexEle = lastBtnWebElement.size() - 1;
            WebElement ele = lastBtnWebElement.get(indexEle);
            jsExecutor.executeScript("arguments[0].click()", ele);
            wait.waitForStalenessOfWebElement(4,ele);

            WebElement updatedActiveClassWebElement = this.getBrowser().findElement(currentActiveClass);
            String updatedPageNo = updatedActiveClassWebElement.getText();//stale element reference exception
            System.out.println("updatedPageNo - " + updatedActiveClassWebElement.getText());

        if (!updatedPageNo.equalsIgnoreCase(currentPageNo)){
            wait.waitForVisibilityOfWebElement(6, updatedActiveClassWebElement);
            List<WebElement> lastCountOfInvoiceElementsList = this.getBrowser().findElements(lastCountOfInvoice);
            int invoiceIndex = lastCountOfInvoiceElementsList.size() - 1;
            WebElement lastCountOfInvoiceElement = this.getBrowser().findElements(lastCountOfInvoice).get(invoiceIndex);
            System.out.println("actual value - " + lastCountOfInvoiceElement.getText());
            actualCount = lastCountOfInvoiceElement.getText();
        }

            return actualCount;

    }

}

