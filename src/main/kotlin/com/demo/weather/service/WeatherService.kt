package com.demo.weather.service

import com.demo.weather.dto.GeoCoordinates
import com.demo.weather.dto.WeatherResponse
import reactor.core.publisher.Mono

interface WeatherService {
    fun getWeatherByGeoCoordinates(geoCoordinates: GeoCoordinates): Mono<WeatherResponse?>
}