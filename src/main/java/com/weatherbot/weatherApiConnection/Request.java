package com.weatherbot.weatherApiConnection;

import static com.weatherbot.weatherApiConnection.Connection.getUrlContent;

public class Request {
    public String infoFromApi(String requestCity) {
        return getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + requestCity + "&units=metric&appid=a672db0c6d0d307f27178aed498b3d0d");
    }
}