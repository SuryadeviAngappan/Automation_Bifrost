package GenericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class provides implementation for the IRetryAnalyser interface of TEstNG
 * @author fllap0570_maheshnarvane
 */
public class RetryAnalyserImplementation implements IRetryAnalyzer 
{
	
	int count = 0;
	int retryCount = 3;
	
	public boolean retry(ITestResult result) 
	{
		
		while(count<retryCount)
		{
			count++;
			return true;
		}	
		
		return false;
	}

}
