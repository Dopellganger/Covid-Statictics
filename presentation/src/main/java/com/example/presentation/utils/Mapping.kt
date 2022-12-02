package com.example.presentation.utils

import com.example.domain.models.CountryDetailDomain
import com.example.domain.models.CountryDomain
import com.example.presentation.models.CountryDetailPresentation
import com.example.presentation.models.CountryPresentation

fun CountryDomain.toPresentationModel() =
    CountryPresentation(
        countryName = this.countryName,
        confirmedCases = this.confirmedCases,
        lastUpdate = this.lastUpdate,
        id = this.id,
    )

fun CountryPresentation.toDomainModel() =
    CountryDomain(
        countryName = this.countryName,
        confirmedCases = this.confirmedCases,
        lastUpdate = this.lastUpdate,
        id = this.id,
    )

fun CountryDetailPresentation.toDomainModel() =
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

fun CountryDetailDomain.toPresentationModel() =
    CountryDetailPresentation(
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

fun CountryDetailPresentation.toCountryDomain() =
    CountryDomain(
        countryName = this.countryName,
        confirmedCases = this.confirmedCases,
        lastUpdate = this.lastUpdate,
        id = this.id,
    )