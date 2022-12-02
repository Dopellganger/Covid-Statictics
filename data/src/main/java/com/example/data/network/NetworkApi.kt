package com.example.data.network

import com.example.data.models.CountryData
import com.example.data.models.CountryDetailData
import com.example.domain.models.ResultWrapper
import com.example.domain.models.SortType

interface NetworkApi {
    suspend fun getAllCountries(sortType: SortType): ResultWrapper<List<CountryData>>
    suspend fun getCountryDetail(countryId: Int): ResultWrapper<CountryDetailData>
}