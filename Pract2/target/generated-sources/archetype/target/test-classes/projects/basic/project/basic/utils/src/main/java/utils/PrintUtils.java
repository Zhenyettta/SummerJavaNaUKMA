package it.pkg.utils;

import it.pkg.fasterxml.jackson.databind.JsonNode;
import it.pkg.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PrintUtils {
    public static void displayWeatherForecast(String weatherForecast) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(weatherForecast);

        if (root.has("location") && root.has("current") && root.has("forecast")) {
            JsonNode location = root.path("location");
            JsonNode current = root.path("current");
            JsonNode forecastNode = root.path("forecast").path("forecastday");

            if (forecastNode.isArray() && forecastNode.size() > 0) {
                JsonNode forecast = forecastNode.get(0).path("hour");

                String city = location.path("name").asText();
                String region = location.path("region").asText();
                String country = location.path("country").asText();
                String localtime = location.path("localtime").asText();

                System.out.println("Current Weather in " + city + ", " + region + ", " + country + ":");
                System.out.println("Local Time: " + localtime);
                System.out.println("Temperature: " + current.path("temp_c").asText() + "°C");
                System.out.println("Condition: " + current.path("condition").path("text").asText());

                System.out.println("\nForecast for the next 5 hours:");

                LocalDateTime now = LocalDateTime.parse(localtime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                List<JsonNode> nextHours = new ArrayList<>();
                for (JsonNode hourData : forecast) {
                    LocalDateTime hourTime = LocalDateTime.parse(hourData.path("time").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    if (!hourTime.isBefore(now)) {
                        nextHours.add(hourData);
                    }
                    if (nextHours.size() >= 5) {
                        break;
                    }
                }

                for (JsonNode hourData : nextHours) {
                    String time = hourData.path("time").asText();
                    String temp = hourData.path("temp_c").asText();
                    String condition = hourData.path("condition").path("text").asText();

                    System.out.println("Time: " + time);
                    System.out.println("Temperature: " + temp + "°C");
                    System.out.println("Condition: " + condition);
                    System.out.println("-----");
                }
            } else {
                System.out.println("Forecast data is not available.");
            }
        } else {
            System.out.println("Weather data is not available.");
        }
    }
}
