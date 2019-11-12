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
		caps.setCapability("unicodeKeyboard", true);
		caps.setCapability("resetKeyboard", true);
		
		driver =  new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		return driver;
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
			LOG.info("Context :   " +f);
	    }
		driver.context(contextName);
		LOG.info(driver.context(contextName).getCurrentUrl());

	}

	public static void enterText(By be, String text)
	{
		waitForElementToBeVisible(be);
		driver.findElement(be).sendKeys(text);
		assertTrue(driver.findElement(be).getText().equalsIgnoreCase(text),"Entered text is not matched");
	}
	public static void clickElement(By be)
	{
		waitForElementToBeVisible(be);
		driver.findElement(be).click();
	}
	public static void clickElement(WebElement we)
	{
		waitForElementToBeVisible(we);
		we.click();
	}
	public static void navigateBack()
	{
		driver.pressKeyCode(AndroidKeyCode.BACK);
	}
	public static void navigateHome()
	{
		driver.pressKeyCode(AndroidKeyCode.HOME);
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
		waitForElementToBeVisible(we);
		assertTrue(we.isDisplayed(),"Element is not displayed");
	}
	public static void startActivity(String appPackage, String activityName)
	{
		driver.startActivity(appPackage, activityName);
	}
	public static void waitForElementToBeVisible(By be)
	{
		try {
			WebElement element = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.elementToBeClickable(be));
		}catch (TimeoutException te)
		{
			LOG.info("Element not found in 10 seconds.");
		}
		catch (ElementNotVisibleException e)
		{
			LOG.info("Element not found in 10 seconds.");
		}
	}

	public static void waitForElementToBeVisible( WebElement we)
	{
		try {
			WebElement element = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.elementToBeClickable(we));
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
