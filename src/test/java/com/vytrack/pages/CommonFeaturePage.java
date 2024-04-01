package com.vytrack.pages;

import static com.vytrack.utils.BrowserUtil.*;


import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.GlobalData;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public abstract class CommonFeaturePage extends BasePage{

    @FindBy(xpath = "//button[@class='btn dropdown-toggle ']")
    protected WebElement viewPerPageBtn;

    @FindBy(xpath = "//button[@class='btn dropdown-toggle ']/following-sibling::ul/li/a")
    protected List<WebElement> pageOptions;

    @FindBy(xpath = "//tbody[@class='grid-body']/tr")
    protected List<WebElement> displayedItems;

    @FindBy(xpath = "//a[@title='Reset']")
     protected WebElement resetButton;
    @FindBy (xpath = "//a[starts-with(@title,'Create ')]")
    protected WebElement createBtn;

    @FindBy(xpath = "//thead[1]//th[starts-with(@class,'grid-cell')]")
    protected List<WebElement> tHeads;
    protected String values_XPATH = "//tbody/tr[%s]/td[not(contains(@class,'action-cell'))]";


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

    public void resetButtonClick(){
       click(resetButton);
    }
    public void verifyCannotClickCreateCarBtn (){
        try {
            Assert.assertFalse(createBtn.isDisplayed());
            System.out.println("Button is not displayed.");
        } catch (NoSuchElementException e) {
            System.out.println("Button not found.");
        } catch (AssertionError e) {
            System.out.println("Button is displayed.");
        }

    }

    private List<String> values(String index){
        List<WebElement> values = BrowserUtil.getListOfElementsByXpath(String.format(values_XPATH,index));
        return BrowserUtil.getTextsFromElementListIgnoreCase(values);
    }
    public void saveAndSelect(String index){
        List<WebElement> valueElements = BrowserUtil.getListOfElementsByXpath(String.format(values_XPATH,index));
        List<String> keys = BrowserUtil.getTextsFromElementListIgnoreCase(tHeads);;
        List<String> values = values(index);
        globalData.setCreateMapFromLists(keys,values);
        globalData.setObject(globalData.getCreateMapFromLists());
        BrowserUtil.click(valueElements.get(0));
    }

    public String getDefaultVpp(){
        return viewPerPageBtn.getText();
    }
}
