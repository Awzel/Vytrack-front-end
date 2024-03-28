package com.vytrack.pages;

import com.vytrack.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralInformationPage {
    public GeneralInformationPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//h5[.='General Information']/following-sibling::div/label")
    protected List<WebElement> keyElements;

    @FindBy(xpath = "//h5[.='General Information']/following-sibling::div/div/div")
    protected List<WebElement> valueElements;



    private List<String> keys(){
        List<String> keys = new ArrayList<>();
        keyElements.stream().map(s->keys.add(s.getText())).limit(7).collect(Collectors.toSet());
        return keys;
    }

    private List<String> values(){
        List<String> values = new ArrayList<>();
        valueElements.stream().map(s->values.add(s.getText())).limit(7).collect(Collectors.toList());
        return values;
    }

    public Map<String,String> actualObject(){
        Map<String,String> actual = new LinkedHashMap<>();
        for (int i = 0; i < keys().size(); i++) {
                actual.put(keys().get(i).toLowerCase(),values().get(i));
        }
        return actual;
    }




}
