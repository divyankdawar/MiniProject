package myTest;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListeners extends Base implements ITestListener{
	
	public void onTestFailure(ITestResult result) {
		System.out.println("FAILED TEST");
		failed(result.getMethod().getMethodName());
	}
	

}
