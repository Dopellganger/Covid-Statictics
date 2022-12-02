package com.example.domain.usecase

import com.example.domain.models.CountryDetailDomain
import com.example.domain.repository.CountriesRepository

class SaveCountryToDB(private val repository: CountriesRepository) {

    suspend fun execute(countryDomain: CountryDetailDomain): Boolean {
        return repository.saveCountry(countryDomain)
    }
}