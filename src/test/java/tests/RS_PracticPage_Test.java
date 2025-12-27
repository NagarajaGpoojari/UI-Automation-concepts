package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.RS_PracticePage;
import utils.ConfigReader;
import utils.DriverFactory;
@Epic("Rahul Shetty Site Tests") 
@Feature("Home Page")
public class RS_PracticPage_Test {

	RS_PracticePage page;

	@BeforeMethod
	public void setUp() {
		
		ConfigReader.loadProperties();
		 DriverFactory.initDriver("firefox");
		 DriverFactory.getDriver().get("https://rahulshettyacademy.com/dropdownsPractise/");
		 page = new RS_PracticePage();
	}
	
	@Story("Verify  Store Linke in homepage") 	
	
	@Test(retryAnalyzer = retry.RetryAnalyzer.class,priority=0, enabled=true)
	public void verifyStoreLink() {
		Assert.assertTrue(page.isFlightsLinkDisplayed(), "Flight link is not visible");
	}

	@Test(retryAnalyzer = retry.RetryAnalyzer.class,priority=1, enabled=true)
	public void SearchFligthsBookings() throws InterruptedException {
		page.HandleDropDown();

	}
	@Story("Verify Booking Flow")
	@Description("Verify's the Booking Flow of the Customers on The RS Page")
	
	@Test(retryAnalyzer = retry.RetryAnalyzer.class,priority=2, enabled=true)
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
