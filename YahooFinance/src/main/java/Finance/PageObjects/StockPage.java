package Finance.PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Finance.utils.Utils;

public class StockPage {
	WebDriver driver;
	Utils utils;

	public StockPage(WebDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//section[@data-testid='quote-price']/div[1]/section[1]/div[1]/div[1]/span[1]")
	WebElement stockPriceElement;

	@FindBy(xpath = "//fin-streamer[@data-field='regularMarketPreviousClose']")
	WebElement previousCloseElement;

	@FindBy(xpath = "//fin-streamer[@data-field='regularMarketVolume']")
	WebElement volumeElement;

	// Get the stock price with error handling
	public double getStockPrice() {
		try {
			String stockPriceText = stockPriceElement.getText();
			return Double.parseDouble(stockPriceText.replace(",", "").replace("$", ""));
		} catch (NumberFormatException e) {
			System.err.println("Error: Invalid stock price format.");
			throw e;
		} catch (NoSuchElementException e) {
			System.err.println("Error: Stock price element not found.");
			throw e;
		}
	}

	// Get the previous close price with error handling
	public String getPreviousClose() {
		try {
			return previousCloseElement.getText();
		} catch (NoSuchElementException e) {
			System.err.println("Error: Previous Close element not found.");
			throw e;
		}
	}

	public String getVolume() {
		try {
			return volumeElement.getText();
		} catch (NoSuchElementException e) {
			System.err.println("Error: Volume element not found.");
			throw e;
		}
	}

}
