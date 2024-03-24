package com.vytrack.utils;

import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtil {

    public static void sleep(int second){
        second *= 1000;
        try{
            Thread.sleep(second);
        }catch (InterruptedException e){

        }
    }

    public static void click(WebElement element){

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }catch (TimeoutException | ElementClickInterceptedException e){
            JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
            executor.executeScript("arguments[0].click();",element);
        }

    }

    public static void titleTobe(String url){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleIs(url));
    }

    public static void TitleVerification(String expected){
        titleTobe(expected);
        Assert.assertEquals(expected,getTitle());
    }

    public static String getTitle(){
        return Driver.getDriver().getTitle();
    }

    public static void send_key(WebElement element,String key){
        element.sendKeys(key);
    }

    public static byte[] takeScreenshotAsBytes(){
        return  ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static boolean isChecked(WebElement webElement){
        return webElement.isSelected();
    }

    public static String takeScreenshotAsBase64(){
        return  ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BASE64);
    }


}
