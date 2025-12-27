package listeners;

import io.qameta.allure.Allure;
import utils.AllureReportLauncher;
import utils.DriverFactory;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestListener implements ITestListener {

    private void attachLogs(String title) {
        File logFile = new File("logs/automation.log");
        if (!logFile.exists()) {
            try {
                logFile.getParentFile().mkdirs();
                logFile.createNewFile();
                System.out.println(" Created new log file: " + logFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println(" Failed to create log file: " + logFile.getAbsolutePath());
                e.printStackTrace();
                return;
            }
        }
        try (FileInputStream fis = new FileInputStream(logFile)) {
            Allure.addAttachment(title, fis);
        } catch (IOException e) {
            System.out.println(" Failed to attach log file: " + logFile.getAbsolutePath());
            e.printStackTrace();
        }
    }

    private void attachScreenshot(WebDriver driver, String title) {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // ✅ Explicit MIME type and extension so Allure renders it correctly
            Allure.addAttachment(title, "image/png", new ByteArrayInputStream(screenshot), ".png");
            System.out.println(" Screenshot attached: " + title);
        } else {
            System.out.println(" WebDriver is null. Screenshot not captured.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(" Test Failed: " + result.getName());

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            Allure.addAttachment("Failure Reason", throwable.toString());
        }

        attachLogs("Logs for Failed Test: " + result.getName());

        WebDriver driver = DriverFactory.getDriver(); //  ThreadLocal driver
        attachScreenshot(driver, "Screenshot on Failure: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(" Test Passed: " + result.getName());
        Allure.addAttachment("Test Passed", result.getName());
        attachLogs("Logs for Passed Test: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(" Test Skipped: " + result.getName());
        Allure.addAttachment("Test Skipped", result.getName());
        attachLogs("Logs for Skipped Test: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println(" Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(" Suite Finished: " + context.getName());
        attachLogs("Final Suite Logs: " + context.getName());
        AllureReportLauncher.generateAndOpenReport(); //  auto launch  Allure report
    }
}
