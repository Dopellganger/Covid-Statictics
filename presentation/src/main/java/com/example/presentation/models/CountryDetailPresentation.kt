package com.example.presentation.models

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

data class CountryDetailPresentation(
    val countryName: String,
    val confirmedCases: Long = 0,
    val lastUpdate: Long = 0,
    val deadCases: Long = 0,
    val incidentRate: Double = 0.0,
    val mortalityRate: Double = 0.0,
    val id: Int = 0,
    val is03: String? = null,
    val x: Double?,
    val y: Double?
) {

    @SuppressLint("SimpleDateFormat")
    fun getFormattedDate(): String {
        val defaultTimeZone = TimeZone.getDefault()
        val pattern = "dd MMM yyyy hh:mm"
        val simpleDateFormat = SimpleDateFormat(pattern)
        simpleDateFormat.timeZone = defaultTimeZone
        return simpleDateFormat.format(Date(lastUpdate))
    }
}