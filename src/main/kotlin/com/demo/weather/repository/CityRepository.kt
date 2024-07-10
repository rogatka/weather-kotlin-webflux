package com.demo.weather.repository

import com.demo.weather.model.City
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository : ReactiveMongoRepository<City, String> {
}