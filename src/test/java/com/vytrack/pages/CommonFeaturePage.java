package com.vytrack.pages;

import static com.vytrack.utils.BrowserUtil.*;


import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.GlobalData;
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
}
