package com.example.data.network

import com.example.data.models.Result
import retrofit2.http.GET
import retrofit2.http.Url

interface ICountriesService {

    @GET
    suspend fun getCountries(
        @Url url: String
    ): Result

    @GET
    suspend fun getCountryDetail(
        @Url url: String
    ): Result
}