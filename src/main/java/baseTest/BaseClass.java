package baseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	public static WebDriver driver = null;

	public static Properties getValueFrom_ProertyFile() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
		System.getProperty("user.dir") + "\\src\\main\\resources\\System.properties");
		prop.load(fis);
		return prop;
	}

	@BeforeTest
	public void openUrl() throws IOException {
		SystemProperties.getSystemProperties();
		driver = WebDriverSession.getWebDriverSession();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(SystemProperties.URL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}

	@AfterTest
	public void tearDown() {
		if (SystemProperties.browserAfterTest.equalsIgnoreCase("quit")) {
			WebDriverSession.quitDriverSession();
		}
	}
}
