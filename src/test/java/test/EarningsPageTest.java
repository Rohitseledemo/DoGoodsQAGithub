package test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.EarningsPage;

public class EarningsPageTest {
    public EarningsPageTest(){
    }
    @BeforeTest
    EarningsPage earningsPage;
    @Parameters({"URL","Email","Password"})
    @Test
    public void claimsAmountTest(String URL, String Email, String Password){
        earningsPage = new EarningsPage();
        earningsPage.getIntoApplication(URL, Email, Password);
    }
}
