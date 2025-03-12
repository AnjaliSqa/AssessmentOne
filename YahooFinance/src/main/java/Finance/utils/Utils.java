package Finance.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	WebDriver driver;

	public Utils(WebDriver driver) {
		this.driver = driver;
	}

	// Wait for an element to be visible and return the WebElement with error
	// handling
	public WebElement waitForElement(By locator, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (TimeoutException e) {
			System.err.println("Error: Element not found within the specified timeout: " + locator);
			throw e; // Re-throw the exception after logging the error
		}
	}

	// Wait for an element to be clickable with error handling
	public WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (TimeoutException e) {
			System.err.println("Error: Element not clickable within the specified timeout: " + locator);
			throw e;
		}
	}

	// Wait for URL to contain a specific text with error handling
	public void waitForURLToContain(String partialUrl, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	//		wait.until(ExpectedConditions.urlContains(partialUrl));
		} catch (TimeoutException e) {
			System.err.println("Error: URL did not contain expected text within timeout: " + partialUrl);
			throw e;
		}
	}

	// General Click Action with error handling
	public void clickElement(By locator) {
		try {
			WebElement element = waitForElementToBeClickable(locator, 10);
			element.click();
		} catch (NoSuchElementException | TimeoutException e) {
			System.err.println("Error: Unable to click on element: " + locator);
			throw e;
		}
	}

	public void waitForElementToBeClickable(WebElement firstAutoSuggest, int timeoutInSeconds) {
		// TODO Auto-generated method stub
		
	}
}
