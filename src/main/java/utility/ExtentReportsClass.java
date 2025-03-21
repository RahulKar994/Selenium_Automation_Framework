package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsClass extends ScreenShot {
 
	public static ExtentReports createExtentReports() {
//	 ExtentTest, ExtentReporter
	String path =".//target//extentReports//index.html" ;
	 ExtentSparkReporter spark = new ExtentSparkReporter(path);
	 spark.config().setReportName("Tesing results");
	 spark.config().setDocumentTitle("Results");
	 ExtentReports report = new ExtentReports();
	 report.attachReporter(spark);
	 return report;
	 	 
 }
//	public void logInfo() {
//		ExtentTest test = createExtentReports().createTest(null);
//	}
}
