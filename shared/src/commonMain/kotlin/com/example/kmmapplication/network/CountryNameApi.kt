package com.example.kmmapplication.network

import com.example.kmmapplication.entity.CountryInfoCard
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class CountryNameApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
    }

    companion object {
        private const val COUNTRIES_INFO_ENDPOINT =
            "https://restcountries.com/v2/all?fields=name,capital,region,population"
    }

    suspend fun getAllCountryInfoCards(): List<CountryInfoCard> {
        return httpClient.get(COUNTRIES_INFO_ENDPOINT)
    }
}

