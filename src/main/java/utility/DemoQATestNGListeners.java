package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import doGoodsQaPages.BasePage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static utility.DemoQAReportGenerator.getDemoQAReportObject;

public class DemoQATestNGListeners extends BasePage implements ITestListener {
        ExtentReports extent = DemoQAReportGenerator.getDemoQAReportObject();
        ThreadLocal<ExtentTest> extentTest =  new ThreadLocal<ExtentTest>();//Making an object Thread Safe
        ExtentTest test;



        @Override
        public void onTestStart(ITestResult result) {
            getDemoQAReportObject();
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
