package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGenerator {

    public static ExtentReports getReportObject(){
        //ExtentHtmlReporter type reporter
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);

        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setReportName("DoGoods Automation Results");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setEncoding("UTF-8");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Rohit Kumar");
        return extent;


    }

}
