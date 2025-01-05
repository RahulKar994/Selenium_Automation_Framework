package utility;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseTest.BaseClass;

public class CommonUtils extends BaseClass {
	protected static WebDriver driver = BaseClass.driver;
	private static final String AES = "AES";

	public WebElement getElement(String elementName) {

		return null;
	}

	public List<WebElement> getElements(String elementsName) {

		return null;
	}
	
//	Verifying you are human. This may take a few seconds.
	public boolean isElementPresent(String xpath) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        return elements.size() > 0; // Returns true if the size is greater than 0, meaning the element exists
    }
	public static String generateRandomEmail() {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int usernameLength = 10, domainLength = 5;
		String[] domains = { "com", "org", "net", "io", "tech" };

		Random random = new Random();
		StringBuilder username = new StringBuilder();
		for (int i = 0; i < usernameLength; i++) {
			username.append(characters.charAt(random.nextInt(characters.length())));
		}
		StringBuilder domain = new StringBuilder();
		for (int i = 0; i < domainLength; i++) {
			domain.append(characters.charAt(random.nextInt(characters.length())));
		}
		String topLevelDomain = domains[random.nextInt(domains.length)];
		return username + "@" + domain + "." + topLevelDomain;
	}

	public static String generateRandomPassword(int length) {
		String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:,.<>?";
		SecureRandom random = new SecureRandom();

		// Build the password
		return random.ints(length, 0, allCharacters.length()).mapToObj(allCharacters::charAt)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	// Method to encrypt a password
	public static String encryptPassword(String password, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance(AES);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encrypted = cipher.doFinal(password.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}

	// Method to decrypt a password
	public static String decryptPassword(String encryptedPassword, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance(AES);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
		return new String(decrypted);
	}

	// Method to generate a SecretKey for AES
	public static SecretKey generateSecretKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
		keyGenerator.init(128); // AES key size (128 bits)
		return keyGenerator.generateKey();
	}

	public static void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForPageReloadToComplete() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}

	public static void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForElementToBeClickableLongWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void scrollTillEelementUsigJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollTillElement(WebElement element) {
		Actions action = new Actions(driver);
		action.scrollToElement(element);
	}
	public static Boolean checkBoxSelected(WebElement element) {
		return element.isSelected();
	}

	public static void senddKeysToElement(WebElement element, String text) {
		element.sendKeys(text);
	}
}
