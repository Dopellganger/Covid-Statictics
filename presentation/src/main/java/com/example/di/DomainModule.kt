package com.example.di

import com.example.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetCountries> {
        GetCountries(repository = get())
    }

    factory<GetCountriesFromDB> {
        GetCountriesFromDB(repository = get())
    }

    factory<GetCountryDetail> {
        GetCountryDetail(repository = get())
    }

    factory<GetCountryDetailFromDB> {
        GetCountryDetailFromDB(repository = get())
    }

    factory<SaveCountryToDB> {
        SaveCountryToDB(repository = get())
    }

    factory<DeleteCountryFromDB> {
        DeleteCountryFromDB(repository = get())
    }

    factory<UpdateSortCountries> {
        UpdateSortCountries(repository = get())
    }

    factory<GetCurrentSortType> {
        GetCurrentSortType(repository = get())
    }

    factory<OpenMap> {
        OpenMap()
    }
}