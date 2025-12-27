package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverFactory;

public class Apple_Mac_Page {
	
		public WebDriver driver;
		public WebDriverWait wait;
		public JavascriptExecutor js;
		public Actions actions;

		public Apple_Mac_Page() {
			this.driver = DriverFactory.getDriver();
			PageFactory.initElements(driver, this);
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			this.js = (JavascriptExecutor) driver;
			this.actions = new Actions(driver);
		}
		
		@FindBy(xpath = "//a[@aria-label=\"Mac\"]")
		private WebElement MacLink;
		@FindBy(xpath = "//li[a[normalize-space(text())='Explore All Mac']]")
		private WebElement ExploreAllMac_Link;
		@FindBy(xpath="//button[@aria-label=\"All products compare gallery\"]")
		private WebElement AllProduct_button;
		@FindBy(xpath="//button[@aria-label=\"Laptops compare gallery\"]")
		private WebElement Laptops_button;
		@FindBy(xpath="//button[@aria-label=\"Desktops compare gallery\"]")
		private WebElement Desktops_button;
		@FindBy(xpath="//button[@aria-label=\"Displays compare gallery\"]")
		private WebElement Displays_button;
		@FindBy(xpath="//div[@class=\"ProductTile_productTileProductId__MA6yX\"]")
		List<WebElement> ListOfAllProduct_Title;
		@FindBy(xpath="//p[@class=\"ProductTile_pricing__mJzcd typography_bodyCopySemibold__RqR50\"]")
		List<WebElement> ProductTile_pricingList;
		
		@FindBy(xpath="//span[normalize-space(text())='Mac Pro']/parent::span[@class=\"ChapterNav_chapternav-label__D2Juf\"]")
		WebElement MacPro_Link;
		
		@FindBy(xpath="//a[normalize-space(text())='Compare']")
		WebElement CompareLink;
		@FindBy(xpath="//select[@id=\"selector-0\"]/parent::form[@class=\"selector-element\"]")
		WebElement SelectDropDown1;
		@FindBy(xpath="//select[@id=\"selector-1\"]/parent::form[@class=\"selector-element\"]")
		WebElement SelectDropDown2;
		@FindBy(xpath="//select[@id=\"selector-2\"]/parent::form[@class=\"selector-element\"]")
		WebElement SelectDropDown3;
		
		@FindBy(xpath="(//button[contains(@class,'colornav-link')]//*[normalize-space(text())='Silver'])[1]")
		WebElement SilverRadioBtn;
		@FindBy(xpath="(//button[contains(@class,'colornav-link')]//*[normalize-space(text())='Space Black'])[1]")
		WebElement SpaceBlackRadioBtn;	
		@FindBy(xpath="(//button[contains(@class,'colornav-link')]//*[normalize-space(text())='Midnight'])[2]")
		WebElement MidnightRadioBtn;
		
		
		public boolean VerifyMacLink() {
			return MacLink.isDisplayed();
		}

		public void clickMacLink() {
			MacLink.click();
		}
		
		public void clickOnExploreAllMac_Link() {
			
	//	actions.moveToElement(MacLink).pause(500).click(ExploreAllMac_Link).build().perform();
		
			//wait.until(ExpectedConditions.visibilityOf(MacLink)); 			 
			actions.moveToElement(MacLink).build().perform();		
			wait.until(ExpectedConditions.elementToBeClickable(ExploreAllMac_Link)); 
			actions.moveToElement(ExploreAllMac_Link).click().perform();
		}
		
		
		
		
		
		
	}

