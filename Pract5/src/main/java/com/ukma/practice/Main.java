package com.ukma.practice;

import java.io.IOException;

/**
 * Main class for the weather application.
 */
public class Main {

    /**
     * Main method to run the application.
     *
     * @param args the command line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main(final String[] args) throws IOException {
        ApiRequest apiRequest = new ApiRequest();
        String response = apiRequest.fetchWeatherData("Kyiv");
        PrintUtils.displayWeatherForecast(response);
    }
}
