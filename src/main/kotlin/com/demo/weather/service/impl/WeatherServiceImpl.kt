package com.demo.weather.service.impl

import com.demo.weather.configuration.properties.WeatherApiProperties
import com.demo.weather.dto.GeoCoordinates
import com.demo.weather.dto.WeatherResponse
import com.demo.weather.service.WeatherService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

private const val FORECAST_DAYS = 1

private const val WEATHER_UNITS = "temperature_2m,relative_humidity_2m,is_day,precipitation,rain,wind_speed_10m"

@Service
class WeatherServiceImpl(
    val log: Logger = LoggerFactory.getLogger(WeatherServiceImpl::class.java),
    val weatherApiProperties: WeatherApiProperties,
    val webClient: WebClient
) : WeatherService {

    @Cacheable(value = ["weatherCache"])
    override fun getWeatherByGeoCoordinates(geoCoordinates: GeoCoordinates): Mono<WeatherResponse?> {
        // example
        // https://api.open-meteo.com/v1/forecast?latitude=56.3287&longitude=44.002&current=temperature_2m,relative_humidity_2m,is_day,precipitation,rain,wind_speed_10m&wind_speed_unit=ms&forecast_days=1
        val weatherResponseMono = webClient
            .get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/v1/forecast")
                    .queryParam("latitude", geoCoordinates.latitude)
                    .queryParam("longitude", geoCoordinates.longitude)
                    .queryParam("current", WEATHER_UNITS)
                    .queryParam("wind_speed_unit", weatherApiProperties.windSpeedUnit)
                    .queryParam("forecast_days", FORECAST_DAYS)
                    .build()
            }
            .retrieve()
            .bodyToMono(WeatherResponse::class.java)
        return weatherResponseMono;
    }
}