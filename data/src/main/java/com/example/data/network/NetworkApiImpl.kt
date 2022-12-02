package com.example.data.network

import com.example.data.models.CountryData
import com.example.data.models.CountryDetailData
import com.example.data.utils.toCountryData
import com.example.data.utils.toCountryDetailData
import com.example.domain.models.*
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

private val BASE_URL: HttpUrl = HttpUrl.Builder()
    .scheme("https")
    .host("services1.arcgis.com")
    .addPathSegments("0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases2_v1/FeatureServer/2/")
    .build()
private const val SORT_CONFIRMED = "orderByFields=Confirmed"

class NetworkApiImpl : NetworkApi {

    private var mICountriesService: ICountriesService? = null

    init {
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        mICountriesService = retrofit.create(ICountriesService::class.java)
    }

    override suspend fun getAllCountries(sortType: SortType): ResultWrapper<List<CountryData>> {
        val queryUrl = "query?where=1%3D1&outFields=Country_Region,Last_Update," +
                "Confirmed,UID&outSR=4326&f=json" + getSort(sortType)
        return try {
            val result = mICountriesService?.getCountries(queryUrl)
            if (result != null) {
                ResultWrapper.Success(
                    result.countries.map { it.toCountryData() }
                )
            } else {
                throw Exception("Nullable data")
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.response()?.errorBody()?.string()
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }

    private fun getSort(sortType: SortType): String {
        return when(sortType) {
            is SortType.ConfirmedAsc -> "&$SORT_CONFIRMED"
            is SortType.Alphabet -> ""
        }
    }

    override suspend fun getCountryDetail(countryId: Int): ResultWrapper<CountryDetailData> {
        val queryUrl = "query?where=UID=${countryId}&outFields=Country_Region,ISO3,Last_Update," +
                "Confirmed,Deaths,Incident_Rate,Mortality_Rate,UID&outSR=4326&f=json"
        return try {
            val result = mICountriesService?.getCountryDetail(queryUrl)
            if (result != null) {
                ResultWrapper.Success(
                    result.countries.map { it.toCountryDetailData() }[0]
                )
            } else {
                throw Exception("Nullable data")
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.response()?.errorBody()?.string()
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}