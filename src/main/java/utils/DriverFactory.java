package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String browser) {
        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }

    
    public static void quitDriver() {
        try {
            WebDriver webDriver = getDriver();
            if (webDriver != null) {
                webDriver.quit(); // closes all windows and ends session
                driver.remove();  // clears ThreadLocal reference
            }
        } catch (Exception e) {
            System.out.println("Error while quitting driver: " + e.getMessage());
        }
    }
}




