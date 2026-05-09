package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int currentRetry = 0;

    private static final int maxRetry = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (currentRetry < maxRetry) {

            currentRetry++;

            System.out.println("Retrying Test: "
                    + result.getName()
                    + " | Retry Count: "
                    + currentRetry);

            return true;
        }

        return false;
    }
}