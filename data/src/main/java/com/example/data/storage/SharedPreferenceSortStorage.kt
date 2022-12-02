package com.example.data.storage

import android.content.SharedPreferences
import com.example.domain.models.SortType

const val PREFERENCE_NAME = "Sort preference"
private const val SORT_KEY = "SORT_KEY"

class SharedPreferenceSortStorage(
    private val sharedPreferences: SharedPreferences
) : SortStorage {

    override fun update(sortType: SortType): Boolean {
        return sharedPreferences.edit().apply {
            putString(SORT_KEY, sortType.name)
        }.commit()
    }

    override fun get(): SortType {
        val name = sharedPreferences.getString(SORT_KEY, SortType.Alphabet.name)!!
        return SortType.valueOf(name)
    }
}