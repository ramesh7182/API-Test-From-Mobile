package com.ramesh7182;
import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertTrue;

public class CommonMethods {
	public AndroidDriver driver;
	String inputURLLocByID = "org.mushare.httper:id/editTextUrl" ;
	String sendButtonByID = "org.mushare.httper:id/buttonSend" ;
	String viewGroupLocByID = "org.mushare.httper:id/toolbar";
	String statusCodeLocBYID = "org.mushare.httper:id/textViewStatusCode";
	String textForPreviewLocByText = "PREVIEW" ;
	String errorMessageLOCByAccID = "Page Not Found";
	public void initDriver() throws MalformedURLException
	{
		DesiredCapabilities caps = DesiredCapabilities.android();
		caps.setCapability("deviceName","Android Emulator");
		caps.setCapability("app",System.getProperty("user.dir")+"\\src\\main\\resources\\Http.apk");
		caps.setCapability("appPackage","org.mushare.httper");
		caps.setCapability("appActivity","org.mushare.httper.MainActivity");
		caps.setCapability("unicodeKeyboard", true);
		caps.setCapability("resetKeyboard", true);
		driver =  new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
	}
	public void closeDriver()
	{
	   driver.closeApp();
	}
	public void enterText(By be, String text)
	{
		waitForElementToBeVisible(be);
		driver.findElement(be).sendKeys(text);
		assertTrue(driver.findElement(be).getText().equalsIgnoreCase(text),"Entered text is not matched");
	}
	public void clickElement(By be)
	{
		waitForElementToBeVisible(be);
		driver.findElement(be).click();
	}
	public void clickElement(WebElement we)
	{
		waitForElementToBeVisible(we);
		we.click();
	}
	public void verifyText(By be, String text)
	{
		waitForElementToBeVisible(be);
		assertTrue(driver.findElement(be).isDisplayed(),"Element is not displayed");
		assertTrue (driver.findElement(be).getText().equalsIgnoreCase(text),"Text doesn't match");
	}
	public void verifyElementVisible(WebElement we)
	{
		waitForElementToBeVisible(we);
		assertTrue(we.isDisplayed(),"Element is not displayed");
	}
	public void waitForElementToBeVisible(By be)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(be));

	}

	public void waitForElementToBeVisible( WebElement we)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(we));
	}
	public void enterURL(String url)
	{
		By be = By.id(inputURLLocByID);
		enterText(be,url);
	}
	public void clickSend()
	{
		By be = By.id(sendButtonByID);
		clickElement(be);
	}
	public void clickPreview()
	{
		By viewGroupTabLoc = By.id(viewGroupLocByID);
		By statusCodeLoc = By.id(statusCodeLocBYID);
		clickElement(viewGroupTabLoc);
		waitForElementToBeVisible(statusCodeLoc);
	}
	public void verifyStatusCode(String statusCode)
	{
		By statusCodeLoc = By.id(statusCodeLocBYID);
		verifyText(statusCodeLoc,statusCode);
	}
	public void goBack()
	{
		driver.pressKeyCode(AndroidKeyCode.BACK);
	}
	public void verifyNoResourceError()
	{
		clickElement(driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+textForPreviewLocByText+"\")"));
		verifyElementVisible(driver.findElementByAccessibilityId(errorMessageLOCByAccID));
	}
}
