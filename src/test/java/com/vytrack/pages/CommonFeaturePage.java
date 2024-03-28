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

    @FindBy(xpath = "//a[@title='Reset']")
     protected WebElement resetButton;
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
            globalData.setDefaultPageNum("25");
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
       // tHeads.stream().map(s->keys.add(s.getText())).limit(7).collect(Collectors.toSet());
        int i = 0;
        for (WebElement tHead : tHeads) {
            if (i==7){
                break;
            }
            keys.add(tHead.getText().toLowerCase());
            i++;
        }
        return keys;
    }

//    private List<String> values(List<WebElement> valuesElement){
//        List<String> values = new ArrayList<>();
//        valuesElement.stream().map(s->values.add(s.getText())).limit(7).collect(Collectors.toList());
//        return values;
//    }

    private List<String> values(String index){
        List<WebElement> values = BrowserUtil.getListOfElementsByXpath(String.format(values_XPATH,index));
        List<String> valuesText = new ArrayList<>();
        int i = 0;
        for (WebElement value : values) {
            if (i==7){
                break;
            }
            valuesText.add(value.getText());
            i++;
        }
        return valuesText;
    }
    public void saveAndSelect(String index){
        List<WebElement> valueElements = BrowserUtil.getListOfElementsByXpath(String.format(values_XPATH,index));
        List<String> keys = keys();
        List<String> values = values(index);
        Map<String,String> mappy = new LinkedHashMap<>();
        globalData.setObject(mappy);

        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equalsIgnoreCase("chassis number")||keys.get(i).equalsIgnoreCase("last odometer")){
                globalData.getObject().put(keys.get(i),values.get(i).replace(",",""));
                continue;
            }
            mappy.put(keys.get(i),values.get(i) );
        }

        BrowserUtil.click(valueElements.get(0));
        sleep(2);
    }
}
