package com.demo.weather.service.impl

import com.demo.weather.model.City
import com.demo.weather.repository.CityRepository
import com.demo.weather.service.CityService
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CityServiceImpl(val cityRepository: CityRepository) : CityService {

    @Cacheable(value = ["citiesCache"])
    @PreAuthorize("hasAuthority('CITIES_READ')")
    override fun getAllCities(): Flux<City> {
        return cityRepository.findAll().sort { c1, c2 -> c1.city.compareTo(c2.city) }
    }
}