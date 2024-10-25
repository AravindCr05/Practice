package Testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	int a = 0;
	int MaxRetry = 2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(a<MaxRetry) {
			a++;
			return true;//will run this method only if return true
		}
		return false;
	}

}
