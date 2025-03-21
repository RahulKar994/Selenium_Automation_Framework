package utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import baseTest.BaseClass;

public class ScreenShot extends BaseClass{
 
	public String takeScreenshotAtFailure(String testCaseName) throws IOException {
	 
		Date date = new Date();
		String uniqueScreenshotName = date.toString().replace(" ", "-").replace(":", "-");
		
		File screenShotFileName = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File screenshotLocation = new File(System.getProperty("user.dir")+"//OutputResults//"+testCaseName+" "+uniqueScreenshotName+".png");
		FileUtils.copyFile(screenShotFileName, screenshotLocation);
		String screenShot_Path_For_ExtentReports = screenshotLocation.toString();
		System.out.println(screenShot_Path_For_ExtentReports);
		return screenShot_Path_For_ExtentReports;
 }
}
