package com.example.kmmapplication.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryInfoCard(
    @SerialName("name")
    val name: String,
    @SerialName("capital")
    val capital: String,
    @SerialName("region")
    val region: String,
    @SerialName("population")
    val population: Int
)