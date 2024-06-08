package com.app;


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
