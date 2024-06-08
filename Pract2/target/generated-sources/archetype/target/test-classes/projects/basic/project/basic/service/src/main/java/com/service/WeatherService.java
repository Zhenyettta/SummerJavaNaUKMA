package com.service;


import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WeatherService {
    private static final String API_KEY_PATH = "app/target/resources/api_key.txt";

    private String getApiKey() throws IOException {
        Path apiKeyPath = Paths.get(System.getProperty("user.dir")).resolve(API_KEY_PATH).normalize();
        return new String(Files.readAllBytes(apiKeyPath)).trim();
    }

    public String getWeatherForecast() throws IOException {
        String apiKey = getApiKey();
        String apiUrl = String.format("https://api.weatherapi.com/v1/forecast.json?key=%s&q=Kyiv&days=1", apiKey);
        String response = Request.Get(apiUrl).execute().returnContent().asString();
        return response;
    }
}
