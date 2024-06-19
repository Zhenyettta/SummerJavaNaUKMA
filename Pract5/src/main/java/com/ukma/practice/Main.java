package com.ukma.practice;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApiRequest apiRequest = new ApiRequest();
        String response = apiRequest.fetchWeatherData("Kyiv");
        PrintUtils.displayWeatherForecast(response);
    }
}
