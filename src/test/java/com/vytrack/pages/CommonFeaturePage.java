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
            Assert.assertFalse(createBtn.isDisplayed());
            System.out.println("Button is not displayed.");
        } catch (NoSuchElementException e) {
            System.out.println("Button not found.");
        } catch (AssertionError e) {
            System.out.println("Button is displayed.");
        }

    }


    public List<String> keys(){
        List<String> keys = new ArrayList<>();
       // tHeads.stream().map(s->keys.add(s.getText())).limit(7).collect(Collectors.toSet());
        for (WebElement tHead : tHeads) {
            keys.add(tHead.getText().toLowerCase());
        }
        return keys;
    }

//    private List<String> values(List<WebElement> valuesElement){
//        List<String> values = new ArrayList<>();
//        valuesElement.stream().map(s->values.add(s.getText())).limit(7).collect(Collectors.toList());
//        return values;
//    }

    public List<String> values(String index){
        List<WebElement> values = BrowserUtil.getListOfElementsByXpath(String.format(values_XPATH,index));
        List<String> valuesText = new ArrayList<>();
        for (WebElement value : values) {
            valuesText.add(value.getText());
        }
        return valuesText;
    }
    public void saveAndSelect(String index){
        List<WebElement> valueElements = BrowserUtil.getListOfElementsByXpath(String.format(values_XPATH,index));
        List<String> keys = keys();
        List<String> values = values(index);
        Map<String,String> mappy = new LinkedHashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equalsIgnoreCase("chassis number")||keys.get(i).equalsIgnoreCase("last odometer")){
                mappy.put(keys.get(i),values.get(i).replace(",",""));
                continue;
            }
            mappy.put(keys.get(i),values.get(i) );
        }
        globalData.setObject(mappy);
        BrowserUtil.click(valueElements.get(0));
        sleep(1);
    }
}
