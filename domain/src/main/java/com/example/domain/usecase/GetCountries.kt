package com.example.domain.usecase

import com.example.domain.models.CountryDomain
import com.example.domain.models.ResultWrapper
import com.example.domain.models.SortType
import com.example.domain.repository.CountriesRepository

class GetCountries(private val repository: CountriesRepository) {

    suspend fun execute(sortType: SortType): ResultWrapper<List<CountryDomain>> {
        return repository.getCountriesFromInternet(sortType)
    }
}
