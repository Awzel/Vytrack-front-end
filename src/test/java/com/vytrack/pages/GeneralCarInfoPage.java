package com.vytrack.pages;

import com.vytrack.utils.Driver;
import com.vytrack.utils.GlobalData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralCarInfoPage {

    public GeneralCarInfoPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//h5/span[.='General Information']/../following-sibling::div/label")
    protected List<WebElement> keyElements;

    @FindBy(xpath = "//h5[.='General Information']/following-sibling::div/div/div")
    protected List<WebElement> valueElements;



    public List<String> keys(){
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
        List<String> keysLst = keys();
        List<String> valuesLst = values();
        for (int i = 0; i < keysLst.size(); i++) {
            actual.put(keysLst.get(i),valuesLst.get(i));
        }
        return actual;
    }

    public boolean isSameObject(){
        boolean isSame = true;
//        List<String> keys = globalData.getObject().keySet().stream().toList();
//        for (String s : globalData.getObject().keySet()) {
//            System.out.println(globalData.getObject().get(s));
//            System.out.println(actualObject().get(s));
//            if (!actualObject().get(s).equals(globalData.getObject().get(s))){
//                isSame=false;
//            }
//        }

        for (String s : actualObject().keySet()) {
            System.out.println(actualObject().get(s));
        }

        return isSame;
    }


}
