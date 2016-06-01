package helper;

import java.io.IOException;
import java.util.Set;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import pages.BasePage;

public class TestListener implements ITestListener,IRetryAnalyzer {

	private int retryCount = 0;
    private int maxRetryCount = 5;
	
	
	/*
	 * It will keep listening every test. On any test failure this code will get trigger 
	 * and screenshot will be saved in specific folder
	 */
	public void onTestFailure(ITestResult result) {
		String testclassRaw = result.getTestClass().toString().trim();
		String testclass = testclassRaw.substring(32, testclassRaw.length() - 1); 
		String testname = result.getName().toString().trim();
		try {
			BasePage.getScreenshot(testclass, testname);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is from IRetryAnalyzer, If a test failed then it will rerun that test
	 * Refer: https://www.seleniumeasy.com/testng-tutorials/retry-listener-failed-tests-count-update
	 */
	public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount+1) + " time(s).");
            retryCount++;
            return true;
        }
        return false;
    }
    
    public String getResultStatusName(int status) {
    	String resultName = null;
    	if(status==1)
    		resultName = "SUCCESS";
    	if(status==2)
    		resultName = "FAILURE";
    	if(status==3)
    		resultName = "SKIP";
		return resultName;
    }
	

	public void onFinish(ITestContext context) {
//		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
//		for (ITestResult temp : failedTests) {
//			ITestNGMethod method = temp.getMethod();
//			if (context.getFailedTests().getResults(method).size() > 1) {
//				failedTests.remove(temp);
//			} else {
//				if (context.getPassedTests().getResults(method).size() > 0) {
//					failedTests.remove(temp);
//				}
//			}
//		}
	}
	
	
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}




	

}
