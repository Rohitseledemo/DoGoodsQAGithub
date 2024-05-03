package utility;

import DoGoodsQAPages.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static utility.ReportGenerator.getReportObject;

public class TestNGListeners extends BasePage implements ITestListener {
    WebDriver driver;
    ExtentReports extent = ReportGenerator.getReportObject();
    ThreadLocal<ExtentTest> extentTest =  new ThreadLocal<ExtentTest>();//Making an object Thread Safe
    ExtentTest test;



    @Override
    public void onTestStart(ITestResult result) {
        getReportObject();
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);// gives unique thread id

    }
    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "This is passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
//        try {
//            driver = (WebDriver) result.getTestClass().getRealClass().getField("getBrowser()")
//                    .get(result.getInstance());
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        String filePath;
        try {
            filePath= getScreenshot(result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        // not implemented
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not implemented
    }
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // not implemented
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
