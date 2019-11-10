package com.ramesh7182;

import org.openqa.selenium.By;

public class ResponsePage  extends  AndroidCommonMethods{
    String viewGroupLocByID = "org.mushare.httper:id/toolbar";
    String statusCodeLocBYID = "org.mushare.httper:id/textViewStatusCode";
    String textForPreviewLocByText = "PREVIEW" ;
    String errorMessageLOCByAccID = "Page Not Found";

    public ResponsePage clickPreview()
    {
        By viewGroupTabLoc = By.id(viewGroupLocByID);
        By statusCodeLoc = By.id(statusCodeLocBYID);
        clickElement(viewGroupTabLoc);
        waitForElementToBeVisible(statusCodeLoc);
        return this;
    }

    public ResponsePage verifyStatusCode(String statusCode)
    {
        By statusCodeLoc = By.id(statusCodeLocBYID);
        verifyText(statusCodeLoc,statusCode);
        return this;
    }
    public ResponsePage goBack()
    {
        navigateBack();
        return this;
    }
    public ResponsePage verifyNoResourceError()
    {
        clickElement(driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+textForPreviewLocByText+"\")"));
        verifyElementVisible(driver.findElementByAccessibilityId(errorMessageLOCByAccID));
        return this;
    }

}
