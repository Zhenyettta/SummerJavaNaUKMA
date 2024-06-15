package com.anisimov.service;

import com.anisimov.Weather;
import com.anisimov.annotation.ValidateCity;

import java.io.IOException;

public interface WeatherService {
    Weather getWeather(@ValidateCity String city) throws IOException;
}
