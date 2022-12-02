package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.ResultWrapper
import com.example.domain.models.SortType
import com.example.domain.usecase.GetCountries
import com.example.presentation.models.CountryPresentation
import com.example.presentation.utils.toPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AllCountriesViewModel(
    private val getCountries: GetCountries
) : ViewModel(), CoroutineScope {

    private var countriesLive = MutableLiveData<List<CountryPresentation>>()
    private var errorLive = SingleLiveEvent<ResultWrapper.GenericError>()
    private var networkErrorLive = SingleLiveEvent<ResultWrapper.NetworkError>()
    fun getCountriesLive(): LiveData<List<CountryPresentation>> = countriesLive
    fun getErrorLive(): SingleLiveEvent<ResultWrapper.GenericError> = errorLive
    fun getNetworkErrorLive(): SingleLiveEvent<ResultWrapper.NetworkError> = networkErrorLive
    private var sortType: SortType? = null

    fun setSortType(newSortType: SortType) {
        if (newSortType != sortType) {
            load()
            sortType = newSortType
        }
    }

    private fun load() {
        launch {
            if (sortType != null) {
                when (val resultWrapper = getCountries.execute(sortType!!)) {
                    is ResultWrapper.Success -> {
                        countriesLive.value = resultWrapper.value.map { it.toPresentationModel() }
                    }
                    is ResultWrapper.GenericError -> {
                        errorLive.value = resultWrapper
                    }
                    is ResultWrapper.NetworkError -> {
                        networkErrorLive.value = resultWrapper
                    }
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}