package com.example.domain.models

data class CountryDomain(
    val countryName: String,
    val confirmedCases: Long,
    val lastUpdate: Long,
    val id: Int,
)
