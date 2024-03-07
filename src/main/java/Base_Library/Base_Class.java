package Base_Library;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import waitutility.WaitUtility;

public class Base_Class {
	
	public static WebDriver driver = null;

	String username = System.getenv("BROWSERSTACK_USERNAME");
	String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

	MutableCapabilities capabilities = new MutableCapabilities();
	capabilities.setCapability("browserName", "Chrome");
	HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
	browserstackOptions.put("os", "Windows");
	capabilities.setCapability("bstack:options", browserstackOptions);

	WebDriver driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"), capabilities);

	
	@BeforeTest
	@Parameters(value = {"browser"})
	public void initializeBrowser(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			System.out.println("Chrome is Launched ");

		}

		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			System.out.println("Firebox is Launched ");

		}

		else if (browser.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			System.out.println("Edge Browser is Launched ");

		}
		driver.get("https://qa-admin.dogoodsinc.com/admin/");
		driver.manage().window().maximize();
		WaitUtility.impliciwait(2000);
	}
	
	//@AfterTest
	//public void tearDown() {
		//driver.quit();
	//}
    
	

}
