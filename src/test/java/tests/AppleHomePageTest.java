package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AppleHomePage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.Log;

@Listeners(listeners.TestListener.class)
public class AppleHomePageTest extends BaseTest {

	AppleHomePage home;
	
	@BeforeMethod
	public void setUp() {
		
		ConfigReader.loadProperties();
	//	DriverFactory.initDriver("chrome");	
	//	DriverFactory.getDriver().get("https://www.apple.com");
		home = new AppleHomePage();
	}

	@Test(retryAnalyzer = retry.RetryAnalyzer.class,priority=0, enabled=true)
	public void verifyStoreLink() {
		Assert.assertTrue(home.isStoreLinkDisplayed(), "Store link is not visible");
	}

	@Test(retryAnalyzer = retry.RetryAnalyzer.class,priority=1, enabled=true)
	
	public void userSignInTest() {
		Log.info("Starting login test");
		String userName = "UserName";
		String password = "Password";
		home.userSignIn(userName, password);
		
	}
	
	@Test(retryAnalyzer = retry.RetryAnalyzer.class, priority=2, enabled=true)
	public void SearchProducts() throws InterruptedException {
		String ProductName="SearchProduct";
		home.SearchItems(ProductName);
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
