package baseTest;

import java.io.IOException;

public class SystemProperties extends BaseClass{
public static String URL,browserName,userName,password,browserAfterTest;
public static Boolean headlessMode, incognito;
	
	public static void getSystemProperties() throws IOException {
		String browserName = getValueFrom_ProertyFile().getProperty("browserName");
		SystemProperties.browserName = browserName;
		String URL = getValueFrom_ProertyFile().getProperty("URL");
		String browserAfterTest = getValueFrom_ProertyFile().getProperty("browserAfterTest");
//		Boolean headlessMode = (Boolean) getValueFrom_ProertyFile().getProperty("headless");
		SystemProperties.URL = URL;
		SystemProperties.browserAfterTest = browserAfterTest;
	}
}
