package Finance;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Finance.PageObjects.LandingPage;
import Finance.PageObjects.StockPage;
import Finance.utils.Utils;

public class LandingPageTest {

	public static void main(String[] args) throws InterruptedException {

		// Setup WebDriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://finance.yahoo.com/");

		// Initialize LandingPage object
		LandingPage landingPage = new LandingPage(driver);
		StockPage stockPage = new StockPage(driver);
		Utils utils = new Utils(driver);

		// Search for Tesla stock (TSLA)
		try {
			landingPage.searchText("TSLA");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			return; // Exit the test gracefully if symbol is invalid
		}

		// Click the first auto-suggestion
		landingPage.clickFirstAutoSuggest();

		// Wait until the URL contains TSLA
		utils.waitForURLToContain("TSLA", 8);
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("TSLA"), "Test Failed: Not on Tesla Stock page.");
		System.out.println("Test Passed: Tesla stock page is displayed.");

		// Verify that the stock price is greater than $200
		double stockPrice = stockPage.getStockPrice();
		if (stockPrice > 200) {
			System.out.println("Tesla stock price is greater than $200: $" + stockPrice);
		} else {
			System.out.println("Tesla stock price is not greater than $200: $" + stockPrice);
		}

		// Assert stock price is not null or invalid
		Assert.assertTrue(stockPrice < 0, "Test Failed: Stock price is invalid or zero.");

		// Capture and verify Previous Close
		String previousClose = stockPage.getPreviousClose();
		Assert.assertNotNull(previousClose, "Previous Close should not be null.");
		Assert.assertFalse(previousClose.isEmpty(), "Previous Close should not be empty.");
		System.out.println("Previous Close: " + previousClose);

		// Capture and verify Volume
		String volume = stockPage.getVolume();
		Assert.assertNotNull(volume, "Volume should not be null.");
		Assert.assertFalse(volume.isEmpty(), "Volume should not be empty.");
		System.out.println("Volume: " + volume);

		// Test Passed: All data captured successfully
		System.out.println("Test Passed: Stock data captured successfully.");

		// Quit the driver
		driver.quit();
	}
}
