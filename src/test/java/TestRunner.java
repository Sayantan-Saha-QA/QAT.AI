import java.io.IOException;

import static base.DriverBase.getDr;
import static base.DriverBase.setUp;
import static commonutils.Asserts.*;
import static commonutils.CommonUtils.*;

import java.awt.Desktop;
import java.io.File;

import base.DriverManagerFactory;
import base.ChromeDriverManager;
import base.FirefoxDriverManager;


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

    static {
        DriverManagerFactory.register("chrome", ChromeDriverManager.class);
        DriverManagerFactory.register("firefox", FirefoxDriverManager.class);
        // Register more browsers here, no need to edit setUp()!
    }

    @BeforeClass (alwaysRun = true, enabled = true)
    public void startReport() {
        setUp(); // Initialize WebDriver
        // Initialize PageFactory elements for all page classes
        PageFactory.initElements(getDr(), LoginPage.class);
        PageFactory.initElements(getDr(), Sidebar.class);
        PageFactory.initElements(getDr(), ProductPage.class);
        // Initialize Extent Report
        ExtentReportUtil.initializeReport();
    }
    
    @AfterClass (alwaysRun = true, enabled = true)
    private void cleanUp() {
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            // Log soft assertion failure in Extent Report
            ExtentReportUtil.createAndGetTest("Soft Assertion Failure").fail("Soft assertion failed: " + e.getMessage());
            throw e;
        } finally {
            CommonUtils.tearDown();
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
}