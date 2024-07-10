package com.demo.weather.service

import com.demo.weather.model.City
import reactor.core.publisher.Flux

interface CityService {
    fun getAllCities() : Flux<City>;
}