package com.anisimov;

import com.anisimov.processor.ValidateCityHandler;
import com.anisimov.service.WeatherService;
import com.anisimov.service.WeatherServiceImpl;
import com.anisimov.Weather;
import com.anisimov.service.WeatherServiceImplFactory;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class WeatherApp {
    public static void main(String[] args) {
        WeatherService weatherService = (WeatherService) Proxy.newProxyInstance(
                 WeatherServiceImplFactory.class.getClassLoader(),
                new Class<?>[]{WeatherService.class},
                new ValidateCityHandler(new WeatherServiceImpl())
        );

        try {
            // Test with a valid city
            Weather weather = weatherService.getWeather("Kyiv");
            System.out.println("Weather in " + weather.getCity() + ": " + weather.getTemperature() + "°C, " + weather.getDescription());
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            // Test with an invalid city
            Weather weather = weatherService.getWeather("Rivne");
            System.out.println("Weather in " + weather.getCity() + ": " + weather.getTemperature() + "°C, " + weather.getDescription());
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
