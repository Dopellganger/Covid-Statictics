package com.example.data.models

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("features") val countries: List<Features>
)

data class Features(
    @SerializedName("attributes") val attributes: CountryAttributes,
    @SerializedName("geometry") val geometry: HashMap<String, Double>?,
)

data class CountryAttributes(
    @SerializedName("Country_Region") val countryName: String,
    @SerializedName("Confirmed") val confirmedCases: Long,
    @SerializedName("Deaths") val deadCases: Long = 0,
    @SerializedName("Incident_Rate") val incidentRate: Double = 0.0,
    @SerializedName("Mortality_Rate") val mortalityRate: Double = 0.0,
    @SerializedName("ISO3") val is03: String = "",
    @SerializedName("UID") val id: Int = 0,
    @SerializedName("Last_Update") val lastUpdate: Long,
)