package com.ukma.practice;

import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApiService {
   private static final String API_KEY_PATH = "api-module/src/main/resources/api-key.txt";

    private String getApiKey() throws IOException {
        Path apiKeyPath = Paths.get(System.getProperty("user.dir")).resolve(API_KEY_PATH).normalize();
        return new String(Files.readAllBytes(apiKeyPath)).trim();
    }

    public String getData() throws IOException {
        String apiKey = getApiKey();
        String apiUrl = String.format("https://api.weatherapi.com/v1/forecast.json?key=%s&q=Kyiv&days=1", apiKey);
        return Request.Get(apiUrl).execute().returnContent().asString();
    }
}
