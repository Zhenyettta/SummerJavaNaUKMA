#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.${artifactId};


import com.service.WeatherService;

import java.io.IOException;

import static com.utils.PrintUtils.displayWeatherForecast;

public class Main {
    public static void main(String[] args) {
        WeatherService weatherService = new WeatherService();
        try {
            String weather = weatherService.getWeatherForecast();
            displayWeatherForecast(weather);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
