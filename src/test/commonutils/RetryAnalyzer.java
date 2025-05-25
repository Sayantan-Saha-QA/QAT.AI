package commonutils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import java.lang.reflect.Method;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetry = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount == 0) { // Only fetch annotation once per test instance
            Method method = result.getMethod().getConstructorOrMethod().getMethod();
            if (method.isAnnotationPresent(RetryCount.class)) {
                maxRetry = method.getAnnotation(RetryCount.class).value();
            }
        }
        if (retryCount < maxRetry) {
            retryCount++;
            return true;
        }
        return false;
    }
}