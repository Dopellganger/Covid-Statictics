package com.example.domain.usecase

import com.example.domain.models.CountryDomain
import com.example.domain.models.SortType
import com.example.domain.repository.CountriesRepository

class GetCountriesFromDB(private val repository: CountriesRepository) {

    suspend fun execute(sortType: SortType): List<CountryDomain> {
        return repository.getCountriesFromStorage(sortType)
    }
}