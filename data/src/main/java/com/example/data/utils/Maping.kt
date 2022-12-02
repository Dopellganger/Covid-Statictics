package com.example.data.utils

import com.example.data.models.CountryData
import com.example.data.models.CountryDetailData
import com.example.data.models.Features
import com.example.domain.models.CountryDetailDomain
import com.example.domain.models.CountryDomain
import com.example.domain.models.ResultWrapper

fun Features.toCountryData() = CountryData(
    countryName = this.attributes.countryName,
    confirmedCases = this.attributes.confirmedCases,
    lastUpdate = this.attributes.lastUpdate,
    id = this.attributes.id,
)

fun Features.toCountryDetailData(): CountryDetailData {
    var x: Double? = null
    var y: Double? = null
    if (this.geometry != null) {
        x = geometry["x"]
        y = geometry["y"]
    }
    return CountryDetailData(
        countryName = this.attributes.countryName,
        confirmedCases = this.attributes.confirmedCases,
        lastUpdate = this.attributes.lastUpdate,
        deadCases = this.attributes.deadCases,
        incidentRate = this.attributes.incidentRate,
        mortalityRate = this.attributes.mortalityRate,
        id = this.attributes.id,
        is03 = this.attributes.is03,
        x = x,
        y = y,
    )
}

@JvmName("toDomainResultWrapperCountryData")
fun ResultWrapper<List<CountryData>>.toDomainResultWrapper(): ResultWrapper<List<CountryDomain>> {
    return when (this) {
        is ResultWrapper.Success<List<CountryData>> -> ResultWrapper.Success(
            this.value.map { it.toDomainModel() }
        )
        is ResultWrapper.GenericError -> this
        is ResultWrapper.NetworkError -> this
    }
}

fun ResultWrapper<CountryDetailData>.toDomainResultWrapper(): ResultWrapper<CountryDetailDomain> {
    return when (this) {
        is ResultWrapper.Success<CountryDetailData> -> ResultWrapper.Success(
            this.value.toDomainModel()
        )
        is ResultWrapper.GenericError -> this
        is ResultWrapper.NetworkError -> this
    }
}

fun CountryData.toDomainModel() =
    CountryDomain(
        countryName = this.countryName,
        confirmedCases = this.confirmedCases,
        lastUpdate = this.lastUpdate,
        id = this.id,
    )


fun CountryDetailData.toDomainModel() =
    CountryDetailDomain(
        countryName = this.countryName,
        confirmedCases = this.confirmedCases,
        lastUpdate = this.lastUpdate,
        deadCases = this.deadCases,
        incidentRate = this.incidentRate,
        mortalityRate = this.mortalityRate,
        id = this.id,
        is03 = this.is03,
        x = this.x,
        y = this.y,
    )

fun CountryDetailDomain.toDataModel() =
    CountryDetailData(
        countryName = this.countryName,
        confirmedCases = this.confirmedCases,
        lastUpdate = this.lastUpdate,
        deadCases = this.deadCases,
        incidentRate = this.incidentRate,
        mortalityRate = this.mortalityRate,
        id = this.id,
        is03 = this.is03,
        x = this.x,
        y = this.y,
    )