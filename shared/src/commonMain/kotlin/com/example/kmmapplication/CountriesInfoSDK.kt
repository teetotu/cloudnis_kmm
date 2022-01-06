package com.example.kmmapplication

import com.example.kmmapplication.cache.Database
import com.example.kmmapplication.cache.DatabaseDriverFactory
import com.example.kmmapplication.entity.CountryInfoCard
import com.example.kmmapplication.network.CountryNameApi

class CountriesInfoSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = CountryNameApi()

    @Throws(Exception::class)
    suspend fun getcountryInfoCards(forceReload: Boolean): List<CountryInfoCard> {
        val cachedCountryInfoCards: List<CountryInfoCard> = database.getAllcountryInfoCards()
        return if (cachedCountryInfoCards.isNotEmpty() && !forceReload) {
            cachedCountryInfoCards
        } else {
            api.getAllCountryInfoCards().also {
                database.clearDatabase()
                database.createCountryInfoCards(it)
            }
        }
    }
}