package baseTest;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSession {
	// ThreadLocal for WebDriver
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	public static WebDriver getWebDriverSession() {
		if (driverThreadLocal.get() == null) {
			if (SystemProperties.browserName.contains("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				options.setAcceptInsecureCerts(true);
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("profile.default_content_setting_values.geolocation", 2);
				options.setExperimentalOption("prefs", prefs);
	            driverThreadLocal.set(new ChromeDriver(options));
			}
        }
        return driverThreadLocal.get();		
	}
	
	// Quit the WebDriver instance
    public static void quitDriverSession() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
