package Finance.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Finance.utils.Utils;

public class LandingPage extends Utils {

	WebDriver driver;
	Utils utils;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "ybar-sbq")
	WebElement searchBar;

	@FindBy(xpath = "//li[@title='Tesla, Inc.']")
	public WebElement firstAutoSuggest;

	public void searchText(String text) {
		searchBar.sendKeys(text);
	}

	// Click on the first suggestion with error handling
	public void clickFirstAutoSuggest() {
		try {
			utils.clickElement(By.xpath("//li[@title='Tesla, Inc.']"));
		} catch (NoSuchElementException e) {
			System.err.println("Error: First suggestion not found.");
			throw e;
		}
	}

}
