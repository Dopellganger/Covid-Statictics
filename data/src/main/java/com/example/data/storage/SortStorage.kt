package com.example.data.storage

import com.example.domain.models.SortType

interface SortStorage {
    fun update(sortType: SortType): Boolean
    fun get(): SortType
}