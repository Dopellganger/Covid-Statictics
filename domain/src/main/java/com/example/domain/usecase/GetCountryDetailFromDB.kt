package com.example.domain.usecase

import com.example.domain.models.CountryDetailDomain
import com.example.domain.repository.CountriesRepository

class GetCountryDetailFromDB(private val repository: CountriesRepository) {

    suspend fun execute(countryId: Int) : CountryDetailDomain {
        return repository.getCountryDetailFromDB(countryId)
    }
}