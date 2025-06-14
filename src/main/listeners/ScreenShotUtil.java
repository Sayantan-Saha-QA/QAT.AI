package listeners;
import static base.DriverBase.getDr;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShotUtil {

    private ScreenShotUtil() {
    }

    public static void takeScreenShot(String stepName) {
        try {
            File srcFile = ((TakesScreenshot) getDr()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File("screenshots/" + stepName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
