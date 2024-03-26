package com.vytrack.pages;

import com.vytrack.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

    public ForgotPasswordPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="prependedInput")
    public WebElement usernameOrEmailInput;

    @FindBy( xpath = "//button[@type='submit']")
    public WebElement requestButton;

    @FindBy(xpath = "//a[.='Forgot your password?']")
    public WebElement resetPasswordLink;


    @FindBy(xpath = "//div[@class='alert alert-warn']")
    public WebElement forgotPasswordSuccessMsg;

    @FindBy(xpath = "//div[@class='alert alert-error']")
    public WebElement forgotPasswordFailedMsg;

}
