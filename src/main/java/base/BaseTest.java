package base;

import org.testng.annotations.*;

import utils.ConfigReader;
import utils.DriverFactory;

public abstract class BaseTest {

	@Parameters({ "browser", "url" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, @Optional("https://www.apple.com") String url) {
		DriverFactory.initDriver(browser);
		DriverFactory.getDriver().get(url);
		log("Launching browser: " + browser + " | URL: " + url);
		ConfigReader.loadProperties();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		log("Closing browser");
		DriverFactory.quitDriver();
	}

	protected void log(String message) {
		System.out.println("[LOG] " + message);
		
	}

	
	@BeforeClass(alwaysRun = true)
	public void beforeClassHook() {
		log("Starting test class: " + this.getClass().getSimpleName());
	}

	@AfterClass(alwaysRun = true)
	public void afterClassHook() {
		log("Finished test class: " + this.getClass().getSimpleName());
	}
}
