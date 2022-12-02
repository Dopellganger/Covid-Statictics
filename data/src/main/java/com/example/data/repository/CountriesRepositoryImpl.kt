package com.example.data.repository

import com.example.data.network.NetworkApi
import com.example.data.storage.CountriesDao
import com.example.data.storage.SortStorage
import com.example.data.utils.toDataModel
import com.example.data.utils.toDomainModel
import com.example.data.utils.toDomainResultWrapper
import com.example.domain.models.*
import com.example.domain.repository.CountriesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CountriesRepositoryImpl(
    private val networkApi: NetworkApi,
    private val storage: CountriesDao,
    private val preferenceSort: SortStorage
) : CountriesRepository, CoroutineScope {

    override suspend fun getCountriesFromInternet(
        sortType: SortType
    ): ResultWrapper<List<CountryDomain>> {
        return withContext(coroutineContext) {
            networkApi.getAllCountries(sortType).toDomainResultWrapper()
        }
    }

    override suspend fun getCountriesFromStorage(sortType: SortType): List<CountryDomain> {
        return withContext(coroutineContext) {
            when (sortType) {
                is SortType.Alphabet -> {
                    storage.getAllByAlphabet().map {
                        it.toDomainModel()
                    }
                }
                is SortType.ConfirmedAsc -> {
                    storage.getAllByConfirmed().map {
                        it.toDomainModel()
                    }
                }
            }
        }
    }

    override suspend fun saveCountry(countryDomain: CountryDetailDomain): Boolean {
        return withContext(coroutineContext) {
            storage.insert(countryDomain.toDataModel()) > 0
        }
    }

    override suspend fun deleteCountry(countryDomain: CountryDetailDomain): Boolean {
        return withContext(coroutineContext) {
            storage.delete(countryDomain.toDataModel()) == 1
        }
    }

    override suspend fun getCountryDetail(countryId: Int): ResultWrapper<CountryDetailDomain> {
        return withContext(coroutineContext) {
            networkApi.getCountryDetail(countryId).toDomainResultWrapper()
        }
    }

    override suspend fun getCountryDetailFromDB(countryId: Int): CountryDetailDomain {
        return withContext(coroutineContext) {
            storage.getCountryDetail(countryId).toDomainModel()
        }
    }

    override fun getCurrentSortType(): SortType {
        return preferenceSort.get()
    }

    override suspend fun updateSortType(sortType: SortType): Boolean {
        return withContext(coroutineContext) {
            preferenceSort.update(sortType)
        }
    }

    override val coroutineContext: CoroutineContext = Dispatchers.IO
}