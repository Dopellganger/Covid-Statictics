package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class CountryData(
    @ColumnInfo(name = "country_region") val countryName: String,
    @ColumnInfo(name = "confirmed") val confirmedCases: Long,
    @ColumnInfo(name = "last_update") val lastUpdate: Long,
    @PrimaryKey val id: Int
)