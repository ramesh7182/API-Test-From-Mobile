package com.ramesh7182;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest extends TestDataProvider {
	public static Log LOG = LogFactory.getLog(BaseTest.class);
	public static AndroidDriver driver;

	@BeforeTest
	public void setup() throws MalformedURLException
	{
		LOG.info("Setting up driver");
		try {
			driver = AndroidCommonMethods.initDriver();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@DataProvider(name = "readTestData")
	public static Object[][] readData(Method method) {
		LOG.info("Reading Test Data before test case execution ");
		return TestDataProvider.readTestData(method);

	}
	public static void wait(int seconds)
	{
		try {
			LOG.info("Waiting for "+seconds +" second(s)..." );
		Thread.sleep(seconds*1000);
		}catch(Exception e) {}
	}
	
	public static void quit()
	{
			
		driver.closeApp();
		
	}
	public static void setWebViewContext()
	{
		AndroidCommonMethods.setContext("WEBVIEW_"+driver.getCapabilities().getCapability("appPackage").toString());
	}
	public static void setNativeContext()
	{
		AndroidCommonMethods.setContext("NATIVE_APP");
	}

	@AfterTest
	public void cleanup()
	{
		LOG.info("closing the driver");
		AndroidCommonMethods.quit();
	}
	
}
