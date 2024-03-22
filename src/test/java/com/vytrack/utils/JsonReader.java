package com.vytrack.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    private static final JSONParser jsonParser = new JSONParser();

    public static String getSingleString(String key) {
        String name = "";

        try {
            FileReader reader = new FileReader("src/test/resources/env/" + ConfigReader.getProperty("env") + ".json");
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            name = (String) obj.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public static JSONArray getJsonArray(String user, String dataType) {
        JSONArray jsonArray = new JSONArray();
        try {
            FileReader reader = new FileReader("src/test/resources/env/" + ConfigReader.getProperty("env") + ".json");
            JSONObject jsonObject = (JSONObject) ((JSONObject) jsonParser.parse(reader)).get(dataType);
            jsonArray = (JSONArray) jsonObject.get(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static List<String> getValuesFromJsonArray(String user, String key, String dataType) {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < getJsonArray(user, dataType).size(); i++) {
            JSONObject obj = (JSONObject) getJsonArray(user, dataType).get(i);
            values.add((String) obj.get(key));
        }
        return values;
    }

    //TODO needs more clarification
    public static String getSingleString(String user, String key, String dataType) {
        try {
            getValuesFromJsonArray(user, key, dataType).get(0);
        }catch (Exception e) {
            System.out.println(user);
            e.printStackTrace();
        }
        return getValuesFromJsonArray(user, key, dataType).get(0);
    }
}
