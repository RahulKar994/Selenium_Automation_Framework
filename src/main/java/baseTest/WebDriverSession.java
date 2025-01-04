package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSession {
	// ThreadLocal for WebDriver
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	public static WebDriver getWebDriverSession() {
		if (driverThreadLocal.get() == null) {
			if (SystemProperties.browserName.contains("chrome")) {
				WebDriverManager.chromedriver().setup();
	            driverThreadLocal.set(new ChromeDriver());
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
