package com.ukma.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequest {
    public String fetchWeatherData(String city) {
        String apiKey = "addab5935ca14383b6e221323230411";
        String urlString = "https://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + city;

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
