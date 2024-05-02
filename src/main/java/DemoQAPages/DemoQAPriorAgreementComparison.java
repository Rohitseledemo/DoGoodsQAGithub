package DemoQAPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import DoGoodsQAPages.ClientInvoiceDetailsPage;

public class DemoQAPriorAgreementComparison extends ClientInvoiceDetailsPage {
    By surchargeSavings;
    By transportationName;
    By dataCardValuesColor;
    By actualCreditRefundsName;

    public DemoQAPriorAgreementComparison(){
        this.totalSavings=By.xpath("//h4[@id='total_savings']//font");
        this.surchargeSavings=By.xpath("//h4[@id='surcharge_savings']//font");
        this.transportationName=By.xpath("//div[@class='card-body border-right" +
                "-sky']//h6[text()=' Transportation Savings  ']");
        this.dataCardValuesColor=By.xpath("//h4[@class='text-secondary']//font");
        this.avgSavingsPerPkgBtn= By.xpath("//div[@id='package_cost_savings']//font");
        this.actualCreditRefundsName=By.xpath("//div[@class='card-body border-right" +
                "-sky']//h6[text()='Actual Credits/Refunds']");
    }
    public String totalSavingsColourVerify(){
        return this.getBrowser().findElement(totalSavings).getAttribute("color");
    }
    public String surchargeSavingsColorVerify(){
        return this.getBrowser().findElement(totalSavings).getAttribute("color");
    }
    public String transportationSavingsColorVerify(){
        WebElement transportationNameWebElement =this.getBrowser().findElement(transportationName);
        return transportationNameWebElement.findElement(dataCardValuesColor).getAttribute("color");
    }
    public String averageSavingsPerPackageColorVerify(){
        return this.getBrowser().findElement(avgSavingsPerPkgBtn).getAttribute("color");
    }
    public String actualCreditRefundsColorVerify(){
        WebElement actualCreditRefundsWebElement=this.getBrowser().findElement(actualCreditRefundsName);
        return this.getBrowser().findElement(dataCardValuesColor).getAttribute("color");
    }





}
