package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AppleHomePage;
import pages.Apple_Mac_Page;
import utils.ConfigReader;
import utils.DriverFactory;

public class MacPage_Tests {
	Apple_Mac_Page mac;
	
	@BeforeMethod
	public void setUp() {
		
		ConfigReader.loadProperties();
		DriverFactory.initDriver("chrome");	
		DriverFactory.getDriver().get("https://www.apple.com");
		mac = new Apple_Mac_Page();
	}

	@Test(retryAnalyzer = retry.RetryAnalyzer.class,priority=0, enabled=true)
	public void verifyMacLink_visibility() {
		Assert.assertTrue(mac.VerifyMacLink(), "Mac link is not visible");
	}
	@Test(retryAnalyzer = retry.RetryAnalyzer.class,priority=1, enabled=true)
	public void Verify_ExploreAllLink_VisibleAndClickable() {
		mac.clickOnExploreAllMac_Link();
		String actualTitle = DriverFactory.getDriver().getTitle();
		System.out.println("Title of the Page: "+actualTitle);
		String expectedTitle = "Mac - Apple";  
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
