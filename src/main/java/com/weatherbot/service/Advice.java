package com.weatherbot.service;

import org.springframework.stereotype.Component;

@Component
public class Advice {
    String weatherAdvice;
    public String giveAdvice(int temperature){

        if (temperature >0 && temperature<10){
            weatherAdvice = "take a winter jacket";
        }
        if (temperature >10 && temperature<15){
            weatherAdvice = "take a autumn jacket";
        }
        return  weatherAdvice;
    }
}