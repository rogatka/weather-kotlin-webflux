package com.demo.weather.web

data class CityWeatherResponse(
    val address: String?,
    val cityName: String?,
    val countryName: String?,
    val time: String?,
    val temperatureCelsius: Double?,
    val relativeHumidityPercentage: Int?,
    val isDay: Boolean?,
    val precipitationMillimeters: Int?,
    val rainMillimeters: Int?,
    val windSpeedMetersPerSecond: Double?
)