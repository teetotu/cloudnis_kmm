package com.example.kmmapplication.cache

import com.example.kmmapplication.entity.CountryInfoCard

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.removeAllCountryInfoCards()
    }

    internal fun getAllcountryInfoCards(): List<CountryInfoCard> {
        return dbQuery.selectAllCountryInfoCards(::mapCountryInfoCard).executeAsList()
    }

    private fun mapCountryInfoCard(
        name: String, 
        capital: String,
        region: String,
        population: Long
    ): CountryInfoCard {
        return CountryInfoCard(
            name = name,
            capital = capital,
            region = region,
            population = population.toInt()
        )
    }

    internal fun createCountryInfoCards(countryInfoCards: List<CountryInfoCard>) {
        dbQuery.transaction {
            countryInfoCards.forEach { countryInfoCard ->
                insertCountryInfoCard(countryInfoCard)
            }
        }
    }

    private fun insertCountryInfoCard(countryInfoCard: CountryInfoCard) {
        dbQuery.insertCountryInfoCard(
            name = countryInfoCard.name,
            capital = countryInfoCard.capital,
            region = countryInfoCard.region,
            population = countryInfoCard.population.toLong()
        )
    }
}