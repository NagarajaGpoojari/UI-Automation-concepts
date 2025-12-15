package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

public class RetryAnalyzer implements IRetryAnalyzer {
    
	private int count = 0;
    private static final int maxTry = 3;

    public boolean retry(ITestResult result) {
        if (count < maxTry) {
            count++;
            ScreenshotUtil.captureScreenshot(result.getName());
            return true;
        }
        return false;
    }
}

