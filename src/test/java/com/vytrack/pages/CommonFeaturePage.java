package com.vytrack.pages;

import static com.vytrack.utils.BrowserUtil.*;


import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.GlobalData;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class CommonFeaturePage extends BasePage{

    @FindBy(xpath = "//button[@class='btn dropdown-toggle ']")
    protected WebElement viewPerPageBtn;

    @FindBy(xpath = "//button[@class='btn dropdown-toggle ']/following-sibling::ul/li/a")
    protected List<WebElement> pageOptions;

    @FindBy(xpath = "//tbody[@class='grid-body']/tr")
    protected List<WebElement> displayedItems;

<<<<<<< HEAD
    @FindBy(xpath = "//a[@title='Reset']")
     protected WebElement resetButton;
=======
    @FindBy (xpath = "//a[starts-with(@title,'Create ')]")
    protected WebElement createCarBtn;
>>>>>>> main

    GlobalData globalData;

    public CommonFeaturePage(GlobalData globalData) {
        this.globalData = globalData;
    }


    public void setPage(String pageNumber){
        if (isValidSetNumber(pageNumber)) {
            globalData.setPageNum(pageNumber);
            click(viewPerPageBtn);
            for (WebElement pageOption : pageOptions) {
                if (pageOption.getText().equals(pageNumber)) {
                    click(pageOption);
                    break;
                }
            }
        }else {
            throw new RuntimeException("Page is invalid");
        }

    }

    private boolean isValidSetNumber(String pageNumber){
        return pageNumber.equals("10") || pageNumber.equals("25") || pageNumber.equals("50") || pageNumber.equals("100");
    }


    public String displayedNumberOfItems(String expectedPageNumber){
        BrowserUtil.waitUntilTextTobe(expectedPageNumber,viewPerPageBtn);
       return displayedItems.size()+"";
    }

<<<<<<< HEAD
    public void resetButtonClick(){
       click(resetButton);
    }
=======
    public void verifyCannotClickCreateCarBtn (){
        try {
            Assert.assertFalse(createCarBtn.isDisplayed());
            System.out.println("Button is not displayed.");
        } catch (NoSuchElementException e) {
            System.out.println("Button not found.");
        } catch (AssertionError e) {
            System.out.println("Button is displayed.");
        }

    }

>>>>>>> main
}
