package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ClientInvoiceDetailsPage extends BasePage{
    By doGoodsInvoiceLink;
    By accountNumberColumn;
    By accountColumnCounts;

    public ClientInvoiceDetailsPage(){
        this.doGoodsInvoiceLink=By.cssSelector("span.text-sky");
        this.accountNumberColumn=By.xpath("//label[@class='label']//strong[text()='Account Number']");
        this.accountColumnCounts=By.xpath("//table[@class='mt-2 table table-bordered dogoods-fees']//tbody//tr//td[2]");
    }

    public void doGoodsInvoiceLinkClick(){
        this.getBrowser().findElement(doGoodsInvoiceLink).click();
    }

    public int getAccountNumberColumnCount(){
        int count;
        this.driver.waitForPresenceOfElement(4,accountColumnCounts);
//        WebElement accountNumberColumnElement = this.getBrowser().findElement(accountNumberColumn);
        this.driver.waitForVisibilityOfWebElement(4,this.getBrowser().findElement(accountNumberColumn));
        if(this.getBrowser().findElement(accountNumberColumn).isDisplayed()){
            return count = this.getBrowser().findElements(accountColumnCounts).size();
        }
        else {
            return count=0;
        }
    }

}

