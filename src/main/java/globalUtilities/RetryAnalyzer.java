package globalUtilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class RetryAnalyzer implements IRetryAnalyzer, IAnnotationTransformer {
	private int rerunCount = 0;
	private int maxRetryCount = 1;

	public boolean retry(ITestResult result) {

		if (rerunCount < maxRetryCount && Boolean.parseBoolean(System.getProperty("reftryFTC","false"))) {
			rerunCount++;
			return true;
		}
		return false;
	}

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}