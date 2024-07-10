package com.demo.weather.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    @JsonProperty("current_units")
    val currentUnits: CurrentUnits,
    val current: Current
) : Serializable

data class CurrentUnits(
    val time: String,
    val interval: String,
    @JsonProperty("temperature_2m")
    val temperature: String,
    @JsonProperty("relative_humidity_2m")
    val relativeHumidity: String,
    @JsonProperty("is_day")
    val isDay: String,
    val precipitation: String,
    val rain: String,
    @JsonProperty("wind_speed_10m")
    val windSpeed: String
) : Serializable

data class Current(
    val time: String,
    val interval: Int,
    @JsonProperty("temperature_2m")
    val temperature: Double,
    @JsonProperty("relative_humidity_2m")
    val relativeHumidity: Int,
    @JsonProperty("is_day")
    val isDay: Boolean,
    val precipitation: Int,
    val rain: Int,
    @JsonProperty("wind_speed_10m")
    val windSpeed: Double
) : Serializable