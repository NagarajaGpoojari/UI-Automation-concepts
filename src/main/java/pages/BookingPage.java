package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public class BookingPage {
    WebDriver driver;

    public BookingPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath= "//span[contains(text(),'Search')]") // Booking.com search box
    private WebElement searchBox;

    public boolean isSearchBoxDisplayed() {
        return searchBox.isDisplayed();
    }
}

