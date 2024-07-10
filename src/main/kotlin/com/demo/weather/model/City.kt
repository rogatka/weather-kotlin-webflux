package com.demo.weather.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable

@Document("cities")
data class City (
    @Id
    val id: String?,
    val address: String?,
    @Field("postal_code")
    val postalCode: Int?,
    val country: String,
    @Field("federal_district")
    val federalDistrict: String?,
    @Field("region_type")
    val regionType: String?,
    val region: String?,
    @Field("area_type")
    val areaType: String?,
    val area: String?,
    @Field("city_type")
    val cityType: String?,
    val city: String,
    @Field("settlement_type")
    val settlementType: String?,
    val settlement: String?,
    @Field("kladr_id")
    val kladrId: String?,
    @Field("fias_id")
    val fiasId: String?,
    @Field("fias_level")
    val fiasLevel: Int?,
    @Field("capital_marker")
    val capitalMarker: Int?,
    val okato: Long?,
    val oktmo: Long?,
    @Field("tax_office")
    val taxOffice: String?,
    val timezone: String?,
    @Field("geo_lat")
    val geoLat: Double,
    @Field("geo_lon")
    val geoLon: Double,
    val population: Int?,
    @Field("foundation_year")
    val foundationYear: Int?
) : Serializable