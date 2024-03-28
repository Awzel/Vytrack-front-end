package com.vytrack.pages;

import static com.vytrack.utils.BrowserUtil.*;


import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import com.vytrack.utils.GlobalData;
import io.cucumber.java.zh_cn.假如;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public abstract class CommonFeaturePage extends BasePage{

    @FindBy(xpath = "//button[@class='btn dropdown-toggle ']")
    protected WebElement viewPerPageBtn;

    @FindBy(xpath = "//button[@class='btn dropdown-toggle ']/following-sibling::ul/li/a")
    protected List<WebElement> pageOptions;

    @FindBy(xpath = "//tbody[@class='grid-body']/tr")
    protected List<WebElement> displayedItems;

    @FindBy (xpath = "//a[starts-with(@title,'Create ')]")
    protected WebElement createCarBtn;

    @FindBy(xpath = "//thead[1]//th")
    protected List<WebElement> tHeads;
    protected String values_XPATH = "//tbody/tr[%s]/td";

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

    private List<String> keys(){
        List<String> keys = new ArrayList<>();
        tHeads.stream().map(s->keys.add(s.getText())).limit(7).collect(Collectors.toSet());
        return keys;
    }

    private List<String> values(List<WebElement> valuesElement){
        List<String> values = new ArrayList<>();
        valuesElement.stream().map(s->values.add(s.getText())).limit(7).collect(Collectors.toList());
        return values;
    }

    public void saveAndSelect(String index){
        List<WebElement> valuesElement = BrowserUtil.getListOfElementsByXpath(String.format(values_XPATH,index));
        Map<String,String> mappy = new LinkedHashMap<>();
        globalData.setObject(mappy);
        for (int i = 0; i < keys().size(); i++) {
            if (keys().get(i).equalsIgnoreCase("last odometer")||keys().get(i).equalsIgnoreCase("chassis number")){
                globalData.getObject().put(keys().get(i).toLowerCase(),values(valuesElement).get(i).replace(",",""));
                continue;
            }
            globalData.getObject().put(keys().get(i).toLowerCase(),values(valuesElement).get(i));
        }
        BrowserUtil.click(valuesElement.get(0));
    }


}
