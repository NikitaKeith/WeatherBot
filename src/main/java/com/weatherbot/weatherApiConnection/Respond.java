package com.weatherbot.weatherApiConnection;

import org.json.JSONObject;

public class Respond {

    public double getAntwortFromApi(String output) {

        double temp_info=0;

        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            temp_info = obj.getJSONObject("main").getDouble("temp");
            // double temp_feelsLike_info = obj.getJSONObject("main").getDouble("feels_like");
        }
        return temp_info;
    }
}