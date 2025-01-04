package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {
	WebDriver driver;
	public WebElement getElement(String elementName) {
		
		return null;		
	}
	public List<WebElement> getElements(String elementsName) {
		
		return null;		
	}

	
	public CommonUtils(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void scrollTillEelementUsigJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void scrollTillElement(WebElement element) {
		Actions action = new Actions(driver);
		action.scrollToElement(element);
	}
	public void clickOnElement(WebElement element) {
		element.click();
	}
	public void senddKeysToElement(WebElement element, String text) {
		element.sendKeys(text);
	}
}
