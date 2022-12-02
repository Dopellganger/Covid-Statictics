package com.example.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.data.network.NetworkApi
import com.example.data.network.NetworkApiImpl
import com.example.data.repository.CountriesRepositoryImpl
import com.example.data.storage.*
import com.example.domain.repository.CountriesRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    fun provideDatabase(application: Application): CountriesDatabase {
        return Room.databaseBuilder(application, CountriesDatabase::class.java, "countries")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: CountriesDatabase): CountriesDao {
        return database.getCountriesDao()
    }

    single<CountriesDatabase> {
        provideDatabase(androidApplication())
    }

    single<CountriesDao> {
        provideCountriesDao(
            database = get()
        )
    }

    single<NetworkApi> {
        NetworkApiImpl()
    }

    single<SortStorage> {
        SharedPreferenceSortStorage(
            androidContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        )
    }

    single<CountriesRepository> {
        CountriesRepositoryImpl(
            networkApi = get(),
            storage = get(),
            preferenceSort = get()
        )
    }
}