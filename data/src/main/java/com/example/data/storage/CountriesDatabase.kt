package com.example.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.CountryDetailData

@Database(entities = [CountryDetailData::class], version = 1)
abstract class CountriesDatabase : RoomDatabase() {
    abstract fun getCountriesDao(): CountriesDao
}