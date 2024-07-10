package com.demo.weather.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "weather-api")
data class WeatherApiProperties(
    val baseUrl: String,
    val windSpeedUnit: String
)