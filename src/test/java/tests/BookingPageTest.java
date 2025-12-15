package tests;


import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingPage;

public class BookingPageTest extends BaseTest {

    @Test
    public void verifySearchBoxPresence() {
        BookingPage booking = new BookingPage();
        Assert.assertTrue(booking.isSearchBoxDisplayed(), "Search box not visible");
    }
}

