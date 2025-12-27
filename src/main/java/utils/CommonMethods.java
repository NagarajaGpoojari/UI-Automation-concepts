package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CommonMethods {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public JavascriptExecutor js;
	public Actions actions;

	public CommonMethods() {
		CommonMethods.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
		CommonMethods.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.js = (JavascriptExecutor) driver;
		this.actions = new Actions(driver);
	}

	public static void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public static void type(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text);
	}

	public static String getText(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element)).getText();
	}

	public static boolean isDisplayed(WebElement element) {
		try {
			return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public static void hover(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		new Actions(driver).moveToElement(element).perform();
		System.out.println("Hovered over element: " + element);
	}

	public static void hoverAndClick(WebElement parent, WebElement child) {
		try {
			wait.until(ExpectedConditions.visibilityOf(parent));
			Actions actions = new Actions(driver);
			actions.moveToElement(parent).perform();
			System.out.println("Hovered over parent element: " + parent);

			wait.until(ExpectedConditions.elementToBeClickable(child));
			actions.click(child).perform();
			System.out.println("Clicked child element: " + child);
		} catch (Exception e) {
			System.out.println("Hover and click failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void doubleClick(WebElement element) {
		new Actions(driver).doubleClick(wait.until(ExpectedConditions.elementToBeClickable(element))).perform();
	}

	public static void rightClick(WebElement element) {
		new Actions(driver).contextClick(wait.until(ExpectedConditions.elementToBeClickable(element))).perform();
	}

	public static void pressKey(Keys key) {
		new Actions(driver).sendKeys(key).perform();
	}

	public static void clickUsingJS(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public static void waitForPageLoad() {
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}

	public static void waitForVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForClickability(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForText(WebElement element, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public static void switchToFrame(WebElement frameElement) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public static void switchToWindow(String windowHandle) {
		driver.switchTo().window(windowHandle);
	}

	public static void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollByPixels(int x, int y) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}

	public static List<WebElement> getElements(By locator) {
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public static int getElementCount(By locator) {
		return getElements(locator).size();
	}

}
