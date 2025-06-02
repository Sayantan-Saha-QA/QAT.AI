import java.io.IOException;

import static base.DriverBase.getDr;
import static base.DriverBase.setUp;
import static commonutils.Asserts.*;
import static commonutils.CommonUtils.*;

import java.awt.Desktop;
import java.io.File;
import java.util.Set;

import org.reflections.Reflections;
import org.openqa.selenium.support.PageFactory;
import io.cucumber.testng.*;
import org.testng.annotations.*;
import base.*;
import pages.*;
import reporting.ExtentReportUtil;
import commonutils.*;

@CucumberOptions(
    plugin = {
        "pretty", // Makes the console output more readable
        "json:target/reports/json.json", // Generates JSON report
        "html:target/reports/html.html" // Generates HTML report
    },
    monochrome = true, // Makes console output more readable by removing ANSI colors
    features = "src/test/features", // Path to feature files
    glue = "steps", // Path to step definitions
    dryRun = false, // Set to true to check if all steps have definitions without executing them
    tags = "@Swag_Labs" // Executes scenarios with this tag
)
public class Runner extends AbstractTestNGCucumberTests {

    static {
        DriverManagerFactory.setBrowser("chrome", ChromeDriverManager.class);
        DriverManagerFactory.setBrowser("firefox", FirefoxDriverManager.class);
        // Register more browsers here, no need to edit setUp()!
    }

    @BeforeClass (alwaysRun = true, enabled = true)
    public void startReport() {
        setUp(); // Initialize WebDriver
        
        PageFactory.initElements(getDr(), LoginPage.class);
        PageFactory.initElements(getDr(), Sidebar.class);
        PageFactory.initElements(getDr(), ProductPage.class);
        
        // Initialize Extent Report
        ExtentReportUtil.initializeReport();
    }
    
    @AfterClass (alwaysRun = true, enabled = true)
    protected void cleanUp() {
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

            /*get the list of all classes in the package & 
            clear all static instances which are not final
            This is a workaround to clear static instances in the classes*/

            Reflections allClasses = new Reflections("base", "pages", 
                                                     "stepdefs", "commonutils", 
                                                     "reporting", "listeners", 
                                                     "steps", "datamodels");
            Set<Class<?>> classesToClear = allClasses.getSubTypesOf(Object.class);

            for (Class<?> clazz : classesToClear) {
                for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                    if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) &&
                        !java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
                        field.setAccessible(true); // Make the field accessible
                        try {
                            field.set(null, null);
                            System.out.println("Static field '" + field.getName() + "' in class '" + clazz.getName() + "' set to null."); // Set the static field to null
                        } catch (IllegalAccessException e) {
                            logger(e);
                        }
                    }
                }
            }
            System.out.println("All static instances cleared.");
        }
    }

    @Test
    public void dummyTest() {
    // This method exists only to make Gradle recognize this class as a test
    }
}