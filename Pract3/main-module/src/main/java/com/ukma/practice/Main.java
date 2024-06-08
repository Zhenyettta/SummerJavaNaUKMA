package com.ukma.practice;

import com.ukma.practice.ApiService;
import com.ukma.practice.Formatter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApiService apiService = new ApiService();

        String data = apiService.getData();
        Formatter.displayWeatherForecast(data);
    }
}
