package com.example.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.presentation.models.CountryPresentation

class CountriesDiffUtil(
    private val oldCountries: List<CountryPresentation>,
    private val newCountries: List<CountryPresentation>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldCountries.size

    override fun getNewListSize() = newCountries.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldCountries == newCountries

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldCountries[oldItemPosition].countryName == newCountries[newItemPosition].countryName &&
                oldCountries[oldItemPosition].confirmedCases == newCountries[newItemPosition].confirmedCases &&
                oldCountries[oldItemPosition].lastUpdate == newCountries[newItemPosition].lastUpdate
}