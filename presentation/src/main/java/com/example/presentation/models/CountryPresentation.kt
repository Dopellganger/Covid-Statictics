package com.example.presentation.models

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

data class CountryPresentation(
    val countryName: String,
    val confirmedCases: Long,
    val lastUpdate: Long,
    val id: Int,
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
