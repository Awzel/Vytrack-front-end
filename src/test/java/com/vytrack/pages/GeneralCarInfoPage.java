package com.vytrack.pages;

import com.vytrack.utils.Driver;
import com.vytrack.utils.GlobalData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralCarInfoPage {

    GlobalData globalData;
    public GeneralCarInfoPage(GlobalData globalData) {
        this.globalData = globalData;
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//h5/span[.='General Information']/../following-sibling::div/label")
    protected List<WebElement> keyElements;

    @FindBy(xpath = "//h5[.='General Information']/following-sibling::div/div/div")
    protected List<WebElement> valueElements;



    private List<String> keys(){
        List<String> keys = new ArrayList<>();
      //  keyElements.stream().map(s->keys.add(s.getText())).limit(7).collect(Collectors.toSet());
        for (WebElement key : keyElements) {
            if (key.getText().equals("Catalog Value (VAT Incl.)")){
                keys.add("cvvi");
                continue;
            }
            keys.add(key.getText().toLowerCase());
        }
        return keys;
    }

    private List<String> values(){
        List<String> values = new ArrayList<>();
      //  valueElements.stream().map(s->values.add(s.getText())).limit(7).collect(Collectors.toList());
        for (WebElement value : valueElements) {
            if (value.getText().equals("N/A")){
                values.add("");
                continue;
            }
            values.add(value.getText().toLowerCase());
        }
        return values;
    }

    private Map<String, String> actualObject(){
        List<String> keysLst = keys();
        List<String> valuesLst = values();
        globalData.setCreateMapFromLists(keysLst,valuesLst);
        return globalData.getCreateMapFromLists();
    }

    public boolean isSameObject(){
        boolean isSame = true;
        Map<String,String> mappy = actualObject();
        for (String s : globalData.getObject().keySet()) {
           if (!globalData.getObject().get(s).equals(mappy.get(s))){
               System.out.println(globalData.getObject().get(s));
               System.out.println("HERE");
               isSame = false;
           }
        }

        return isSame;
    }


}
