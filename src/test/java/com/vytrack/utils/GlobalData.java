package com.vytrack.utils;


import java.util.Map;

public class GlobalData {
    private String pageNum;

    private String scenarioName;

    private Map<String,String> object;

    public Map<String, String> getObject() {
        return object;
    }

    public void setObject(Map<String, String> object) {
        this.object = object;
    }

        private String defaultPageNum;

        public String getDefaultPageNum () {
            return defaultPageNum;
        }

        public void setDefaultPageNum (String defaultPageNum){
            this.defaultPageNum = defaultPageNum;

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
