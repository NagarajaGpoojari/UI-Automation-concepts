package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverFactory;

public class RahulShetty_PracticPage {

		public WebDriver driver;
		public WebDriverWait wait;
		public JavascriptExecutor js;
		public Actions actions;

		public RahulShetty_PracticPage() {
			this.driver = DriverFactory.getDriver();
			PageFactory.initElements(driver, this);
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			this.js = (JavascriptExecutor) driver;
			this.actions = new Actions(driver);
		}


	@FindBy(xpath="//input[@type=\"radio\"]")
	private List<WebElement> RadioButton; 
	
	@FindBy(xpath="//input[@id=\"autocomplete\"]")
	private WebElement SuggessionBox;
	
	@FindBy(id="dropdown-class-example")
	private WebElement SelectDropDown;
	
	@FindBy(xpath="//*[@type=\"checkbox\"]")
	private  List<WebElement>  CheckBox;
}
