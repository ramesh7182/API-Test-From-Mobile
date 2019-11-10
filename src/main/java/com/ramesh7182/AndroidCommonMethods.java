package com.ramesh7182;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class AndroidCommonMethods {

	public static AndroidDriver driver;

	public static Log LOG = LogFactory.getLog(AndroidCommonMethods.class);

	public static AndroidDriver initDriver() throws MalformedURLException, InterruptedException
	{
		
		DesiredCapabilities caps = DesiredCapabilities.android();
		caps.setCapability("deviceName","Nexis 6 Emulator");
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("platformVersion","7.1.1");
		caps.setCapability("platformName","Android");
		caps.setCapability("app",System.getProperty("user.dir")+"\\src\\main\\resources\\Http.apk");
		caps.setCapability("appPackage","org.mushare.httper");
		caps.setCapability("appActivity","org.mushare.httper.MainActivity");
		
		driver =  new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		return driver;
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
		setContext("WEBVIEW_"+driver.getCapabilities().getCapability("appPackage").toString());
	}
	public static void setNativeContext()
	{
		setContext("NATIVE_APP");
	}
		public static void setContext(String contextName)
	{
		LOG.info("Context To set "+contextName);
			
		Set<String> contextNames = driver.getContextHandles();
		for (Iterator<String> it = contextNames.iterator(); it.hasNext(); ) {
	        String f = it.next();
	        System.out.println("Context :   " +f);
	    }
		driver.context(contextName);
		LOG.info(driver.context(contextName).getCurrentUrl());
		wait(2);

	}

	public static void enterText(By be, String text)
	{
		waitForElementToBeVisible(be);
		driver.findElement(be).sendKeys(text);
	}
	public static void clickElement(By be)
	{
		waitForElementToBeVisible(be);
		driver.findElement(be).click();
	}
	public static void clickElement(WebElement we)
	{
		wait(5);
		we.click();
	}
	public static void navigateBack()
	{
		driver.pressKeyCode(AndroidKeyCode.BACK);
		wait(5);
	}
	public static void verifyText(By be, String text)
	{
		waitForElementToBeVisible(be);
		assertTrue(driver.findElement(be).isDisplayed(),"Element is not displayed");
		assertTrue (driver.findElement(be).getText().equalsIgnoreCase(text),"Text doesn't match");
	}
	public static void verifyElementVisible(By be)
	{
		waitForElementToBeVisible(be);
		assertTrue(driver.findElement(be).isDisplayed(),"Element is not displayed");
	}
	public static void verifyElementVisible(WebElement we)
	{
		wait(5);
		assertTrue(we.isDisplayed(),"Element is not displayed");
	}
	public static void waitForElementToBeVisible(By be)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(
					ExpectedConditions.visibilityOfElementLocated(be));
		}catch (TimeoutException te)
		{
			LOG.info("Element not found in 10 seconds.");
		}
		catch (ElementNotVisibleException e)
		{
			LOG.info("Element not found in 10 seconds.");
		}
	}

}
