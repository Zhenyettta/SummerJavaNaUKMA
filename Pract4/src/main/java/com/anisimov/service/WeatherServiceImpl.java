package com.anisimov.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.anisimov.Weather;
import com.anisimov.annotation.ValidateCity;
import com.anisimov.annotation.WeatherFactory;
import org.json.JSONObject;


@WeatherFactory
public class WeatherServiceImpl implements WeatherService {
    private static final String API_KEY = "addab5935ca14383b6e221323230411";
    private static final String API_URL = "https://api.weatherapi.com/v1/forecast.json?key=%s&q=%s&days=1";

    public Weather getWeather(@ValidateCity String city) throws IOException {
        String urlString = String.format(API_URL, API_KEY, city);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner scanner = new Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        JSONObject jsonObject = new JSONObject(response);
        String description = jsonObject.getJSONObject("current").getJSONObject("condition").getString("text");
        double temperature = jsonObject.getJSONObject("current").getDouble("temp_c");

        return new Weather(city, temperature, description);
    }
}
