package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryDetailData(
    @ColumnInfo(name = "country_region") val countryName: String,
    @ColumnInfo(name = "confirmed") val confirmedCases: Long,
    @ColumnInfo(name = "last_update") val lastUpdate: Long,
    @ColumnInfo(name = "dead_cases") val deadCases: Long,
    @ColumnInfo(name = "incident_rate") val incidentRate: Double,
    @ColumnInfo(name = "mortality_rate") val mortalityRate: Double,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "is03") val is03: String?,
    @ColumnInfo(name = "x") val x: Double?,
    @ColumnInfo(name = "y") val y: Double?
)
