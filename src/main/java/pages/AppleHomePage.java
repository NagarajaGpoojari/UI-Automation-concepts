package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ConfigReader;
import utils.DriverFactory;


public class AppleHomePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Actions actions;

	public AppleHomePage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.js = (JavascriptExecutor) driver;
		this.actions = new Actions(driver);
	}

	@FindBy(xpath = "//a[@id=\"globalnav-menubutton-link-bag\"]")
	private WebElement ShoppingBag;
	@FindBy(xpath = "//a[@data-analytics-title=\"signIn\"]")
	private WebElement SignInLink;

	@FindBy(xpath = "//iframe[@title=\"Sign In to your Apple Account\"]")
	private WebElement signInFrame;
	@FindBy(id = "account_name_text_field")
	private WebElement NameField;
	@FindBy(xpath = "//button[@id=\"sign-in\"]")
	private WebElement continueBtn;
	@FindBy(id = "password_text_field")
	private WebElement PasswordField;
	@FindBy(xpath = "//p[@id=\"errMsg\"]")
	private WebElement errMsg;

	@FindBy(id = "globalnav-menubutton-link-search")
	private WebElement SearchBtn;
	@FindBy(xpath = "//input[@aria-label=\"Search apple.com\"]")
	private WebElement SearchInputField;
	@FindBy(xpath = "//div[@class=\"form-textbox-lefticon\"]")
	private WebElement SearchSubmitBtn;

	@FindBy(xpath = "//input[@name=\"search\"]")
	private WebElement SearchInputField2;
	@FindBy(xpath = "//button[@class=\"form-icons form-icons-search15\"]")
	private WebElement SearchSubmitBtn2;
	@FindBy(xpath = "//button[@data-autom=\"accessories\"]")
	private WebElement accessories;

	@FindBy(xpath = "//span[@class=\"rc-accordion-title large-10 typography-callout\" and text()='Brand']")
	private WebElement BrandFilter;

	@FindBy(xpath = "//label[starts-with(@for,'facet-') and text()='Apple']")
	private WebElement SelectAppleBrand;

	@FindBy(xpath = "//div[@data-autom=\"product-tile\"]")
	private List<WebElement> ListOfIPhones;

	@FindBy(xpath = "//span[text()='Store']")
	private WebElement Store_Link;
//	@FindBy(xpath = "//a[@class='rf-productnav-card-title'][normalize-space()='Apple Watch']")
//	private WebElement AppleWatch_Link;
	@FindBy(className = "rf-cards-scroller-itemview")
	private List<WebElement> AppleWatch_List;
	@FindBy(xpath = "//a[normalize-space()='Explore all Apple Watch bands.']")
	private WebElement AppleWatchBands_link;
	@FindBy(xpath = "//li[@class=\"rf-wuipselect-collection-tile rs-wuipselect-grid-tile column\"]")
	private List<WebElement> productCards;
	@FindAll({
			@FindBy(xpath = "//div[@class='rf-cards-scroller-itemview']//parent::div//*[contains(text(),'Apple Watch')]"),
			@FindBy(xpath = "//a[@class='rf-productnav-card-title'][normalize-space()='Apple Watch']") })
	private WebElement AppleWatch_Link;
	@FindAll({ @FindBy(css = ".product-tile"), 
		       @FindBy(xpath = "//div[contains(@class,'item-tile')]") })
	private List<WebElement> productList;

	public boolean isStoreLinkDisplayed() {
		return Store_Link.isDisplayed();
	}

	public void clickStoreLink() {
		Store_Link.click();
	}

	public void userSignIn(String userName, String password) {
		try {
			ShoppingBag.click();
			wait.until(ExpectedConditions.elementToBeClickable(SignInLink)).click();
			
			// Switch to sign-in iframe
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(signInFrame));
			// Enter credentials
			wait.until(ExpectedConditions.visibilityOf(NameField)).sendKeys(ConfigReader.getProperty(userName),
					Keys.ENTER);
			continueBtn.click();
			wait.until(ExpectedConditions.visibilityOf(PasswordField)).sendKeys(ConfigReader.getProperty(password),
					Keys.ENTER);

		} catch (Exception e) {
			System.out.println("Login failed: " + e.getMessage());
		} finally {
			driver.switchTo().defaultContent();// Always return to main content
		}

	}

	public void SearchItems(String Product) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(SearchBtn));
		SearchBtn.click();
		SearchInputField.click();
		SearchInputField.sendKeys(ConfigReader.getProperty(Product), Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(SearchInputField2));
		SearchInputField2.sendKeys(ConfigReader.getProperty(Product), Keys.ENTER);
		actions.moveToElement(accessories).click().build().perform();
	

		try {
			actions.moveToElement(BrandFilter).click().build().perform();
		} catch (Exception e) {
			js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", BrandFilter);
		}

	
		wait.until(ExpectedConditions.elementToBeClickable(SelectAppleBrand)).click();
	

		Map<String, Integer> iPhoneList = new LinkedHashMap<>();
		for (WebElement product : ListOfIPhones) {
			String name = product.findElement(By.xpath(".//h2[@class=\"rf-producttile-name\"]")).getText();
			String priceText = product.findElement(By.xpath(".//span[@class=\"rf-producttile-pricecurrent\"]"))
					.getText();
			int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
			iPhoneList.put(name, price);
		}
		
		iPhoneList.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEach(entry -> System.out.println("Name: " + entry.getKey() + " | Price: " + entry.getValue()));

		System.out.println(" Printing the iPhoneList in Descending order-->");
		iPhoneList.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.forEach(entry -> System.out.println("Name: " + entry.getKey() + " | Price: " + entry.getValue()));

	}

}
