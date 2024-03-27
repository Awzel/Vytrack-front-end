package com.vytrack.pages;

import com.vytrack.enums.Titles;
import com.vytrack.utils.Driver;
import com.vytrack.utils.GlobalData;
import org.openqa.selenium.support.PageFactory;

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
