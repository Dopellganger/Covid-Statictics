package com.example.domain.usecase

import com.example.domain.models.SortType
import com.example.domain.repository.CountriesRepository

class GetCurrentSortType(private val repository: CountriesRepository) {

    fun execute(): SortType {
        return repository.getCurrentSortType()
    }
}