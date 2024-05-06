package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoQAReportGenerator {
    public static ExtentReports getDemoQAReportObject(){
        //ExtentHtmlReporter type reporter
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String path = System.getProperty("user.dir")+"//reports//demoQaReport//DemoQAReport_"+timestamp+".html";
        ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setReportName("DemoQA Automation Results");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setEncoding("UTF-8");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Rohit Kumar");
        return extent;


    }
}
