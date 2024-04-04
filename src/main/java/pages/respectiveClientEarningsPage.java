package pages;

import org.openqa.selenium.By;

public class respectiveClientEarningsPage extends BasePage{
    By clientTitle;
    public respectiveClientEarningsPage(){
        this.clientTitle=By.xpath("//h3[@class='col-5']");
    }
    public String verifyClientName(){
        return this.getBrowser().findElement(clientTitle).getText();
    }

    public void uploadClaimAmount(){

    }
}
