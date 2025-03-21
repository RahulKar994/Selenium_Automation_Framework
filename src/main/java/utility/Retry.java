package utility;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int maxRetry = 2; int count = 0;	
	public boolean retry(ITestResult result) {
			if(count < maxRetry) {
			count++;
			System.out.println("Failed name " +result.getName());
			return true;
		}
		return false;
	}
}

