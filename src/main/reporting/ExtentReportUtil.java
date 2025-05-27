package reporting;

import static base.DriverBase.getDr;

import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.aventstack.extentreports.MediaEntityBuilder;

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

    // Get the current test
    public static ExtentTest getTest() {
        return test;
    }

    public static void snap(String stepName) {
    try {
        // Capture the screenshot
        File srcFile = ((TakesScreenshot) getDr()).getScreenshotAs(OutputType.FILE);
        String destPath = "screenshots/" + stepName + ".png";
        File destFile = new File(destPath);
        FileUtils.copyFile(srcFile, destFile, StandardCopyOption.REPLACE_EXISTING);

        // Attach the screenshot to the Extent Report
        ExtentReportUtil.getTest().info("Screenshot for step: " + stepName,
            MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
    } catch (IOException e) {
        ExtentReportUtil.getTest().info("Failed to capture screenshot for step: " + stepName);
        e.printStackTrace();
    }
}
    // Flush the report
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }


    
}