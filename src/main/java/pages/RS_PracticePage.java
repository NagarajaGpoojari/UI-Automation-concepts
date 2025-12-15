package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverFactory;

public class RS_PracticePage {
	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Actions actions;

	public RS_PracticePage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.js = (JavascriptExecutor) driver;
		this.actions = new Actions(driver);
	}

	@FindBy(xpath = "//span[@class=\"text-label\" and normalize-space()='Flights']")
	private WebElement Flights;
	@FindBy(xpath = "//table[@id=\"ctl00_mainContent_rbtnl_Trip\"]//tbody//input[@type=\"radio\" and @value=\"RoundTrip\"]")
	private WebElement TripOptions;
	// @FindBy(xpath = "//input[@id=\"ctl00_mainContent_ddl_originStation1_CTXT\"]")
	// private WebElement DepartureDropDown;
	@FindBy(xpath = "//input[@id=\"ctl00_mainContent_ddl_destinationStation1_CTXT\"]")
	private WebElement DestDropDown;
	@FindBy(xpath = "//input[@name=\"ctl00$mainContent$view_date1\"]")
	public WebElement DepartDate;

	@FindBy(xpath = "//input[@id=\"ctl00_mainContent_view_date2\"]")
	public WebElement ReturnDate;
	@FindBy(id = "divpaxinfo")
	private WebElement PassengersDropDown;

	@FindBy(xpath = "//span[@id=\"hrefIncAdt\"]")
	private WebElement adultPlusButton;
	@FindBy(xpath = "//span[@id=\"hrefIncChd\"]")
	private WebElement childPlusButton;

	// ------------------- Utility Methods -------------------

	public boolean isFlightsLinkDisplayed() {
		return Flights.isDisplayed();
	}

	public void clickFlightsLink() {
		Flights.click();
	}

	// Select Trip Type
	public void selectTripType() {
		actions.moveToElement(TripOptions).click().perform();
	}

	// Select Departure City
	public void selectDepartureCity(String cityName) {
		WebElement dropdown = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
		dropdown.click();
		List<WebElement> cityOptions = driver.findElements(By.xpath(".//*[@id=\"dropdownGroup1\"]//a"));

		for (WebElement option : cityOptions) {
			System.out.println("Departure  CitY List---->: " + option.getText().trim());
			if (option.getText().trim().equals(cityName)) {
				option.click();
				System.out.println("Selected Departure ---->: " + cityName);
				break;
			}
		}
		String selected = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).getAttribute("value");
		System.out.println("Final Selected Departure: " + selected);
	}

	// Select Destination City
	public void selectDestinationCity(String cityName) {
		actions.moveToElement(DestDropDown).click().perform();
		List<WebElement> destOptions = driver.findElements(By.xpath(".//*[@id=\"dropdownGroup1\"]//ul//li//a"));

		for (WebElement option : destOptions) {
			System.out.println("Destination City List ---->: " + option.getText().trim());
			if (option.getText().trim().equals(cityName)) {
				option.click();
				System.out.println("Selected Destination ---->: " + cityName);
				break;
			}
		}
		String selectedReturnDate = driver.findElement(By.id("ctl00_mainContent_view_date1")).getAttribute("value");
		System.out.println("Selected Date: " + selectedReturnDate);

	}

	// Select Date from Calendar
	public void selectDate(WebElement calendarElement, String targetDay, String month, String year) {
		actions.moveToElement(calendarElement).click().perform();

		while (true) {
			String displayedMonthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
			if (displayedMonthYear.contains(month) && displayedMonthYear.contains(year)) {
				break;
			} else {
				System.out.println("Msg:: Year navigation not implemented here");
				break;
			}
		}

		List<WebElement> days = driver
				.findElements(By.cssSelector(".ui-datepicker-calendar td[data-handler='selectDay']"));
		for (WebElement day : days) {
			if (day.getText().equals(targetDay)) {
				day.click();
				break;
			}
		}
	}

	public void selectPassengers(int adults, int children) throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(PassengersDropDown));
		actions.moveToElement(PassengersDropDown).click().perform();
		wait.until(ExpectedConditions.elementToBeClickable(adultPlusButton));
		for (int i = 0; i < adults; i++) { // Already 1 Adult, add 2 more
			adultPlusButton.click();
			Thread.sleep(500); // Optional for visual clarity
		}
		wait.until(ExpectedConditions.elementToBeClickable(childPlusButton));
		for (int i = 0; i < children; i++) {
			childPlusButton.click();
			Thread.sleep(500);
		}
		driver.findElement(By.id("btnclosepaxoption")).click();
	}

	public void HandleDropDown() throws InterruptedException {

		actions.moveToElement(TripOptions).click().perform();
		WebElement dropdown = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
		dropdown.click();

		// Step 2: Locate all city options
		List<WebElement> cityOptions = driver.findElements(By.xpath(".//*[@id=\"dropdownGroup1\"]//a"));

		// Step 3: Print all listed cities and select Adampur
		for (WebElement option : cityOptions) {
			String cityName = option.getText().trim();
			if (!cityName.isEmpty()) {
				System.out.println("Departure Station : " + cityName);

				if (cityName.equals("Mumbai (BOM)")) {
					option.click();
					System.out.println("Selected ---->: " + cityName);
					break;
				}
			}
		}

		// Optional: Validate selection
		String selected = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).getAttribute("value");
		System.out.println("Final Selected Departure: " + selected);

		actions.moveToElement(DestDropDown).click().perform();
		List<WebElement> DestcityOptions = driver.findElements(By.xpath(".//*[@id=\"dropdownGroup1\"]//ul//li//a"));

		// Step 3: Print all listed cities and select Adampur
		for (WebElement option : DestcityOptions) {
			String DestcityName = option.getText().trim();
			if (!DestcityName.isEmpty()) {
				System.out.println("Destination Cities : " + DestcityName);

				if (DestcityName.equals("Madurai (IXM)")) {
					option.click();
					System.out.println("Selected ---->: " + DestcityName);
					break;
				}
			}
		}
		System.out.println("Msg:: Handling Date pickers/ Dynamic Calender");

		actions.moveToElement(DepartDate).click().perform();
		// Target date
		String targetDay = "23";
		String targetMonth = "May";
		String targetYear = "2019";
		// Target Return date
		String targetedReturnDay = "20";
		

		// Loop until desired month and year are visible
		while (true) {
			String displayedMonthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
			if (displayedMonthYear.contains(targetMonth) && displayedMonthYear.contains(targetYear)) {
				break;
			} else {
				// driver.findElement(By.cssSelector(".ui-datepicker-next")).click(); // Click
				// next month
				System.out.println("Msg:: Selection of Year is not posible At this Moment");
			}
		}
		// Select the day
		List<WebElement> days = driver
				.findElements(By.cssSelector(".ui-datepicker-calendar td[data-handler='selectDay']"));
		for (WebElement day : days) {
			if (day.getText().equals(targetDay)) {
				day.click();
				break;
			}
		}

		// Optional: verify selection
		String selectedDate = driver.findElement(By.id("ctl00_mainContent_view_date1")).getAttribute("value");
		System.out.println("Selected Date: " + selectedDate);

		wait.until(ExpectedConditions.elementToBeClickable(ReturnDate));
		actions.moveToElement(ReturnDate).click().perform();

		List<WebElement> Rdays = driver
				.findElements(By.cssSelector(".ui-datepicker-calendar td[data-handler='selectDay']"));
		for (WebElement day : Rdays) {
			if (day.getText().equals(targetedReturnDay)) {
				day.click();
				break;
			}
		}

		// Optional: verify selection
		String selectedReturnDate = driver.findElement(By.id("ctl00_mainContent_view_date1")).getAttribute("value");
		System.out.println("Selected Date: " + selectedReturnDate);

		wait.until(ExpectedConditions.elementToBeClickable(PassengersDropDown));
		actions.moveToElement(PassengersDropDown).click().perform();
		wait.until(ExpectedConditions.elementToBeClickable(adultPlusButton));
		for (int i = 0; i < 2; i++) { // Already 1 Adult, add 2 more
			adultPlusButton.click();
			Thread.sleep(500); // Optional for visual clarity
		}
		wait.until(ExpectedConditions.elementToBeClickable(childPlusButton));
		for (int i = 0; i < 2; i++) {
			childPlusButton.click();
			Thread.sleep(500);
		}
		driver.findElement(By.id("btnclosepaxoption")).click();

	}

}
