package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.ResultWrapper
import com.example.domain.usecase.*
import com.example.presentation.models.CountryDetailPresentation
import com.example.presentation.utils.toDomainModel
import com.example.presentation.utils.toPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CountryDetailViewModel(
    private val openMap: OpenMap,
    private val getCountryDetail: GetCountryDetail,
    private val getCountryDetailFromDB: GetCountryDetailFromDB,
    private val saveCountryToDB: SaveCountryToDB,
    private val deleteCountryFromWatched: DeleteCountryFromDB
) : ViewModel(), CoroutineScope {

    var isLoaded = true
    private var countryDetailLive = MutableLiveData<CountryDetailPresentation>()
    private var errorLive = SingleLiveEvent<ResultWrapper.GenericError>()
    private var networkErrorLive = SingleLiveEvent<ResultWrapper.NetworkError>()
    fun getCountryDetailLive(): LiveData<CountryDetailPresentation> = countryDetailLive
    fun getErrorLive(): SingleLiveEvent<ResultWrapper.GenericError> = errorLive
    fun getNetworkErrorLive(): SingleLiveEvent<ResultWrapper.NetworkError> =
        networkErrorLive

    fun save() {
        launch {
            countryDetailLive.value?.let {
                saveCountryToDB.execute(it.toDomainModel())
            }
        }
    }

    fun delete() {
        launch {
            countryDetailLive.value?.let {
                deleteCountryFromWatched.execute(it.toDomainModel())
            }
        }
    }

    fun load(countryId: Int, loadFromDB: Boolean) {
        launch {
            if (loadFromDB) {
                countryDetailLive.value =
                    getCountryDetailFromDB.execute(countryId).toPresentationModel()
            } else {
                when (val resultWrapper = getCountryDetail.execute(countryId)) {
                    is ResultWrapper.Success -> {
                        countryDetailLive.value = resultWrapper.value.toPresentationModel()
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

    fun openMap(openingMap: (Double, Double) -> Unit) {
        launch {
            if (countryDetailLive.value != null) {
                openMap.execute {
                    openingMap(countryDetailLive.value?.y!!, countryDetailLive.value?.x!!)
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}