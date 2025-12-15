package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    
	public static void captureScreenshot(String testName) {
       
        try {
        	File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            File destDir = new File("screenshots");
            if (destDir.exists()) {
                FileUtils.cleanDirectory(destDir); // Deletes all files inside
            } else {
                destDir.mkdirs(); // Create directory if missing
            }
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File destFile = new File(destDir, testName + "_" + timestamp + ".png");
            FileHandler.copy(srcFile, destFile);
            System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
