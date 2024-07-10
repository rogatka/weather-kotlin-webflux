package com.demo.weather.controller

import com.demo.weather.dto.GeoCoordinates
import com.demo.weather.service.CityService
import com.demo.weather.service.WeatherService
import com.demo.weather.web.CityWeatherResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
@RequestMapping("/api/v1/weather")
class WeatherController(
    val log: Logger = LoggerFactory.getLogger(WeatherController::class.java),
    val cityService: CityService,
    val weatherService: WeatherService
) {

    @GetMapping("/all", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getAllCitiesWeather(): Flux<CityWeatherResponse> {
        return cityService.getAllCities()
            // delay to avoid 429 error
            .delayElements(Duration.ofMillis(200L))
            .flatMap { city ->
                log.debug("city {}", city)
                weatherService.getWeatherByGeoCoordinates(GeoCoordinates(city.geoLat, city.geoLon))
                    .map { weather ->
                        log.debug("weather {}", weather)
                        CityWeatherResponse(
                            city.address,
                            city.city,
                            city.country,
                            weather?.current?.time,
                            weather?.current?.temperature,
                            weather?.current?.relativeHumidity,
                            weather?.current?.isDay,
                            weather?.current?.precipitation,
                            weather?.current?.rain,
                            weather?.current?.windSpeed
                        )
                    }
            }
    }
}