package com.vytrack.utils;

import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
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
        expected = getTitle();
        Assert.assertEquals(expected,getTitle());
    }

    public static String getTitle(){
        return Driver.getDriver().getTitle();
    }

    public static void send_key(WebElement element,String key){
        element.sendKeys(key);
    }


}
