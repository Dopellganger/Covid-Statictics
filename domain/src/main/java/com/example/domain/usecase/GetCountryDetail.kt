package com.example.domain.usecase

import com.example.domain.models.CountryDetailDomain
import com.example.domain.models.ResultWrapper
import com.example.domain.repository.CountriesRepository

class GetCountryDetail(private val repository: CountriesRepository) {

    suspend fun execute(countryId: Int): ResultWrapper<CountryDetailDomain> {
        return repository.getCountryDetail(countryId)
    }
}