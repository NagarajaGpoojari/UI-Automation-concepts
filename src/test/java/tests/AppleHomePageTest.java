package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AppleHomePage;
import utils.ConfigReader;
import utils.DriverFactory;

public class AppleHomePageTest extends BaseTest {

	AppleHomePage home;

	@BeforeMethod
	public void setUp() {
		// Initialize browser (you can pass "chrome" or "firefox" based on your config)
		ConfigReader.loadProperties();
	//	DriverFactory.initDriver("chrome");	
	//	DriverFactory.getDriver().get("https://www.apple.com");
		home = new AppleHomePage();
	}

	@Test(retryAnalyzer = retry.RetryAnalyzer.class)
	public void verifyStoreLink() {
		Assert.assertTrue(home.isStoreLinkDisplayed(), "Store link is not visible");
	}

	@Test()
	public void userSignInTest() {
		String userName = "UserName";
		String password = "Password";
		home.userSignIn(userName, password);
		
	}
	
	@Test()
	public void SearchProducts() throws InterruptedException {
		String ProductName="SearchProduct";
		home.SearchItems(ProductName);
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
