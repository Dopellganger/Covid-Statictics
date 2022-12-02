package com.example.domain.repository

import com.example.domain.models.*

interface CountriesRepository {

    suspend fun getCountriesFromInternet(sortType: SortType): ResultWrapper<List<CountryDomain>>
    suspend fun getCountriesFromStorage(sortType: SortType): List<CountryDomain>
    suspend fun saveCountry(countryDomain: CountryDetailDomain): Boolean
    suspend fun deleteCountry(countryDomain: CountryDetailDomain): Boolean
    suspend fun getCountryDetail(countryId: Int): ResultWrapper<CountryDetailDomain>
    suspend fun getCountryDetailFromDB(countryId: Int): CountryDetailDomain
    fun getCurrentSortType(): SortType
    suspend fun updateSortType(sortType: SortType): Boolean
}