package it.pkg.app;


import it.pkg.service.WeatherService;

import java.io.IOException;

import static it.pkg.utils.PrintUtils.displayWeatherForecast;

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
