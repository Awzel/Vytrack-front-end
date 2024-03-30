package com.vytrack.utils;



import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GlobalData {
    private String pageNum;

    private String scenarioName;

    private Map<String,String> object;
    private Map<String,String> createMapFromLists;

    public Map<String, String> getCreateMapFromLists() {
        return createMapFromLists;
    }

    public void setCreateMapFromLists(List<String> keys,List<String>values) {
        Map<String,String> mappy = new LinkedHashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equalsIgnoreCase("chassis number")||keys.get(i).equalsIgnoreCase("last odometer")||keys.get(i).contains("power")){
                mappy.put(keys.get(i),values.get(i).replace(",",""));
                continue;
            }
            mappy.put(keys.get(i),values.get(i));
        }
        this.createMapFromLists = mappy;
    }

    public Map<String, String> getObject() {
        return object;
    }

    public void setObject(Map<String, String> object) {
        this.object = object;
    }

        public String getScenarioName () {
            return scenarioName;
        }

        public void setScenarioName (String scenarioName){
            this.scenarioName = scenarioName;
        }

        public String getPageNum () {
            return pageNum;
        }

        public void setPageNum (String pageNum){
            this.pageNum = pageNum;
        }
    }
