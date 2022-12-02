package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.SortType
import com.example.domain.usecase.GetCountriesFromDB
import com.example.presentation.models.CountryPresentation
import com.example.presentation.utils.toPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WatchedCountriesViewModel(
    private val getCountriesFromDB: GetCountriesFromDB
) : ViewModel(), CoroutineScope {

    private var countriesLive = MutableLiveData<List<CountryPresentation>>()
    fun getCountriesLive(): LiveData<List<CountryPresentation>> = countriesLive
    private var sortType: SortType? = null

    fun setSortType(newSortType: SortType) {
        if (newSortType != sortType) {
            load()
            sortType = newSortType
        }
    }

    fun load() {
        launch {
            if (sortType != null) {
                countriesLive.value = getCountriesFromDB.execute(sortType!!).map {
                    it.toPresentationModel()
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}