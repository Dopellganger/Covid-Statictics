package com.example.data.storage

import androidx.room.*
import com.example.data.models.CountryData
import com.example.data.models.CountryDetailData

@Dao
interface CountriesDao {
    @Query("SELECT country_region, confirmed, last_update, id FROM countrydetaildata ORDER BY country_region")
    fun getAllByAlphabet(): List<CountryData>

    @Query("SELECT country_region, confirmed, last_update, id FROM countrydetaildata ORDER BY confirmed")
    fun getAllByConfirmed(): List<CountryData>

    @Query("SELECT * FROM countrydetaildata WHERE id = :id")
    fun getCountryDetail(id: Int): CountryDetailData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: CountryDetailData): Long

    @Delete
    fun delete(country: CountryDetailData): Int
}