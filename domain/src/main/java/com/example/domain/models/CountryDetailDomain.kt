package com.example.domain.models

data class CountryDetailDomain(
    val countryName: String,
    val confirmedCases: Long,
    val lastUpdate: Long,
    val deadCases: Long,
    val incidentRate: Double,
    val mortalityRate: Double,
    val id: Int,
    val is03: String?,
    val x: Double?,
    val y: Double?
)