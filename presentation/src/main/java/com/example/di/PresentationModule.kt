package com.example.di

import com.example.presentation.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<SortViewModel> {
        SortViewModel(
            updateSortCountries = get(),
            getCurrentSortType = get()
        )
    }

    viewModel<AllCountriesViewModel> {
        AllCountriesViewModel(
            getCountries = get()
        )
    }

    viewModel<WatchedCountriesViewModel> {
        WatchedCountriesViewModel(
            getCountriesFromDB = get()
        )
    }

    viewModel<CountryDetailViewModel> {
        CountryDetailViewModel(
            openMap = get(),
            getCountryDetailFromDB = get(),
            getCountryDetail = get(),
            saveCountryToDB = get(),
            deleteCountryFromWatched = get()
        )
    }
}

