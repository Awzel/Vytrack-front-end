package com.vytrack.pages;

import com.vytrack.enums.Titles;
import com.vytrack.utils.Driver;
import org.openqa.selenium.support.PageFactory;

public class VehiclesPage extends CommonFeaturePage{
    public VehiclesPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @Override
    public String getTitle(){
        return Titles.VEHICLES.getValue();
    }

}
