package com.example.domain.usecase

import com.example.domain.models.SortType
import com.example.domain.repository.CountriesRepository

class UpdateSortCountries(private val repository: CountriesRepository) {

    suspend fun execute(sortType: SortType): Boolean {
        return repository.updateSortType(sortType)
    }
}