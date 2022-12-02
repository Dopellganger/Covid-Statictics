package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.SortType
import com.example.domain.usecase.GetCurrentSortType
import com.example.domain.usecase.UpdateSortCountries
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SortViewModel(
    private val updateSortCountries: UpdateSortCountries,
    getCurrentSortType: GetCurrentSortType
) : ViewModel(), CoroutineScope {

    private val sortTypeLiveData = MutableLiveData(getCurrentSortType.execute())
    fun getSortTypeLiveData(): LiveData<SortType> = sortTypeLiveData

    fun updateSort() {
        launch {
            sortTypeLiveData.value?.let { sortType ->
                val newType = SortType.nextSort(sortType.name)
                if (updateSortCountries.execute(newType)) {
                    sortTypeLiveData.value = newType
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}