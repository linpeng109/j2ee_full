package com.cn.json;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class JSONHelper {

    public JSONArray getJSONArray(String jsonString) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonString);
        return jsonArray;
    }

    public JSONObject getJSONObject(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }

    public String getJSONString(JSONObject jsonObject, String key)
            throws JSONException {
        String jsonString = jsonObject.getString(key);
        return jsonString;
    }
}
