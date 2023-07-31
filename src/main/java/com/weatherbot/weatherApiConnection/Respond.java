package com.weatherbot.weatherApiConnection;

import org.json.JSONObject;

public class Respond {
    public double getAnswerFromApi(String output) {

        double temp_info=0;

        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            temp_info = obj.getJSONObject("main").getDouble("temp");
            // double temp_feelsLike_info = obj.getJSONObject("main").getDouble("feels_like");
           // temp_feels.setText ("Feels: +obj .getJSONObject ("main").getDouble ("feels_like"))
           // temp_max. setText ("Max: " + obj.get]SONObject ("main").getDouble("temp_max")) ;
           // temp_min.setText ("Min: " + obj-getJSONObject ("main").getDouble("temp_min"));
            // pressure. setText ("Pressure: " + obj.getJSONObject ("main") .getDouble ("pressure")) ;
        }
        return temp_info;
    }
}