package com.vytrack.pages;

import com.vytrack.utils.Driver;
import com.vytrack.utils.GlobalData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralCarInfoPage {
    public GeneralCarInfoPage(GlobalData globalData){
        PageFactory.initElements(Driver.getDriver(), this);
        this.globalData = globalData;
    }

    @FindBy(xpath = "//h5/span[.='General Information']/../following-sibling::div/label")
    protected List<WebElement> keyElements;

    @FindBy(xpath = "//h5[.='General Information']/following-sibling::div/div/div")
    protected List<WebElement> valueElements;

    GlobalData globalData;


    public List<String> keys(){
        List<String> keys = new ArrayList<>();
      //  keyElements.stream().map(s->keys.add(s.getText())).limit(7).collect(Collectors.toSet());
        for (WebElement key : keyElements) {
            keys.add(key.getText().toLowerCase());
        }
        return keys;
    }

    public List<String> values(){
        List<String> values = new ArrayList<>();
      //  valueElements.stream().map(s->values.add(s.getText())).limit(7).collect(Collectors.toList());
        for (WebElement value : valueElements) {
            if (value.getText().equals("N/A")){
                values.add("");
                continue;
            }
            values.add(value.getText());
        }
        return values;
    }

    public Map<String,String> actualObject(){
        Map<String,String> actual = new LinkedHashMap<>();
        for (int i = 0; i < keys().size(); i++) {
                actual.put(keys().get(i),values().get(i));
        }
        return actual;
    }

    public boolean isSameObject(){
        boolean isSame = true;
            for (String s : globalData.getObject().keySet()) {
                if (actualObject().containsKey(s)){
                   isSame = globalData.getObject().get(s).equals(actualObject().get(s));
                }
            }
        return isSame;
    }


}
