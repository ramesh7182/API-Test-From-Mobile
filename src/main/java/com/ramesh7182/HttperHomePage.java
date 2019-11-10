package com.ramesh7182;

import org.openqa.selenium.By;

public class HttperHomePage extends AndroidCommonMethods {
    String inputURLLocByID = "org.mushare.httper:id/editTextUrl" ;
    String sendButtonByID = "org.mushare.httper:id/buttonSend" ;

    public HttperHomePage enterURL(String url)
    {
        By be = By.id(inputURLLocByID);
        enterText(be,url);
        return this;
    }

    public ResponsePage clickSend()
    {
        By be = By.id(sendButtonByID);
        clickElement(be);
        return new ResponsePage();
    }


}
