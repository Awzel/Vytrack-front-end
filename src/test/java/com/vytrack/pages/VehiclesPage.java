package com.vytrack.pages;

import com.vytrack.enums.Titles;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import com.vytrack.utils.GlobalData;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.vytrack.utils.BrowserUtil.click;

public class VehiclesPage extends CommonFeaturePage{
    public VehiclesPage(GlobalData data) {
        super(data);
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @Override
    public String getTitle(){
        return Titles.VEHICLES.getValue();
    }

}
