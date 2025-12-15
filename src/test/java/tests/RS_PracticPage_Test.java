package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RS_PracticePage;
import utils.ConfigReader;
import utils.DriverFactory;

public class RS_PracticPage_Test {

	RS_PracticePage page;

	@BeforeMethod
	public void setUp() {
		// Initialize browser (you can pass "chrome" or "firefox" based on your config)
		ConfigReader.loadProperties();
		 DriverFactory.initDriver("chrome");
		 DriverFactory.getDriver().get("https://rahulshettyacademy.com/dropdownsPractise/");
		 page = new RS_PracticePage();
	}

	@Test(retryAnalyzer = retry.RetryAnalyzer.class, enabled=false)
	public void verifyStoreLink() {
		Assert.assertTrue(page.isFlightsLinkDisplayed(), "Flight link is not visible");
	}

	@Test(priority=2)
	public void SearchFligthsBookings() throws InterruptedException {
		page.HandleDropDown();

	}
	 @Test(priority=1)
	    public void testBookingFlow() throws InterruptedException {
	        page = new RS_PracticePage();
	        
	        page.clickFlightsLink();
	        page.selectTripType();
	        page.selectDepartureCity("Mumbai (BOM)");
	        page.selectDestinationCity("Madurai (IXM)");
	        page.selectDate(page.DepartDate, "13", "May", "2019");
	        page.selectDate(page.ReturnDate, "20", "May", "2019");
	        page.selectPassengers(3, 2);
	    }

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
