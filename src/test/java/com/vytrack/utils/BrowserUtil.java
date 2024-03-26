package com.vytrack.utils;

import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(2));
            waitUntilVisible(element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }catch (TimeoutException | ElementClickInterceptedException e){
            JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
            executor.executeScript("arguments[0].click();",element);
            System.out.println("Script click");
        }

    }

    public static void titleTobe(String title){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static void TitleVerification(String expected){
        titleTobe(expected);
        Assert.assertEquals(expected,getTitle());
    }

    public static String getTitle(){
        return Driver.getDriver().getTitle();
    }

    public static void send_key(WebElement element,String key){
        waitUntilVisible(element);
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

    public static WebElement findByXpath(String xpathExpression){
        return Driver.getDriver().findElement(By.xpath(xpathExpression));
    }

    public static void hoverOverTo(WebElement element){
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void waitUntilVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilTextTobe(String text,WebElement e){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(e,text));
    }


}
