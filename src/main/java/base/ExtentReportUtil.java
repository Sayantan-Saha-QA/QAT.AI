package base;

import java.io.IOException;

import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil {

    private static ExtentReports extent;
    private static ExtentTest test;

    // Initialize the Extent Report
    public static void initializeReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
        sparkReporter.config().setReportName("Automation Test Report");
        sparkReporter.config().setDocumentTitle("Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("SUT", "Swag Labs");
        extent.setSystemInfo("Environment", "Live");
        extent.setSystemInfo("Browser", "Firefox");
        extent.setSystemInfo("Tester", "Sayantan Saha");
    }

    // Create a test in the report
    public static ExtentTest createAndGetTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }
    // Flush the report
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
    
}