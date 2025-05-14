import java.io.IOException;

import static commonutils.Asserts.*;
import static commonutils.CommonUtils.*;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.support.PageFactory;

import pages.*;
import stepdefs.StepDef;
import base.DriverBase;
import base.ExtentReportUtil;
import commonutils.Asserts;
import commonutils.CommonUtils;
import commonutils.Waits;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

@CucumberOptions(
    plugin = {
        "pretty", // Makes the console output more readable
        "json:target/reports/json.json", // Generates JSON report
        "html:target/reports/html.html" // Generates HTML report
    },
    monochrome = true, // Makes console output more readable by removing ANSI colors
    features = "src/test/java/resources", // Path to feature files
    glue = "steps", // Path to step definitions
    dryRun = false, // Set to true to check if all steps have definitions without executing them
    tags = "@Swag_Labs" // Executes scenarios with this tag
)
public class TestRunner extends AbstractTestNGCucumberTests {

    {
        // Call startup method before tests
        CommonUtils.startUp();

        // Initialize PageFactory elements for all page classes
        PageFactory.initElements(CommonUtils.getDr(), LoginPage.class);
        PageFactory.initElements(CommonUtils.getDr(), Sidebar.class);
        PageFactory.initElements(CommonUtils.getDr(), ProductPage.class);

        // Add a shutdown hook to ensure teardown is called after tests
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.cleanUp();
        }));
    }

    /**
     * Clears all static instances after test execution.
     */
    private void cleanUp() {
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            // Log soft assertion failure in Extent Report
            ExtentReportUtil.createAndGetTest("Soft Assertion Failure").fail("Soft assertion failed: " + e.getMessage());
            throw e;
        } finally {
            tearDown();
            // Flush Extent Report
            ExtentReportUtil.flushReport();

            // Open the Extent Report in the default browser
            try {
                File reportFile = new File("target/ExtentReport.html");
                if (reportFile.exists()) {
                    Desktop.getDesktop().browse(reportFile.toURI());
                    logger.info("Extent Report opened in the default browser.");
                } else {
                    logger.info("Extent Report file not found: " + reportFile.getAbsolutePath());
                }
            } catch (IOException e) {
                logger(e);
            }

            Class<?>[] classesToClear = {
                LoginPage.class,
                Sidebar.class,
                ProductPage.class,
                StepDef.class,
                Waits.class,
                Asserts.class,
                DriverBase.class,
                CommonUtils.class
            };
            for (Class<?> clazz : classesToClear) {
                for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                    if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) &&
                        !java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
                        field.setAccessible(true); // Make the field accessible
                        try {
                            field.set(null, null); // Set the static field to null
                        } catch (IllegalAccessException e) {
                            logger(e);
                        }
                    }
                }
            }
            logger.info("All static instances have been cleared.");
        }
    }
    /**
     * This method is executed before the test class is run.
     * It initializes the Extent Report.
     */

    @BeforeClass
    public void startReport() {
        // Initialize Extent Report
        ExtentReportUtil.initializeReport();
    }
}