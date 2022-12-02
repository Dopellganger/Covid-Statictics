package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.CountryItemBinding
import com.example.presentation.fragment.COUNTRY_DETAIL_KEY_BOOLEAN
import com.example.presentation.fragment.COUNTRY_DETAIL_KEY_INT
import com.example.presentation.holder.CountriesHolder
import com.example.presentation.models.CountryPresentation
import com.example.presentation.utils.CountriesDiffUtil

class CountriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var countries: List<CountryPresentation> = listOf()
    private var isDataFromDB: Boolean = false

    fun setUploadingDataFrommDB() {
        isDataFromDB = true
    }

    fun updateCountries(countries: List<CountryPresentation>) {
        val diffCallback = CountriesDiffUtil(this.countries, countries)
        val diff = DiffUtil.calculateDiff(diffCallback)
        diff.dispatchUpdatesTo(this)
        this.countries = countries
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CountriesHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CountriesHolder).bind(countries[position])
        holder.itemView.setOnClickListener {
            val bundle = bundleOf(
                COUNTRY_DETAIL_KEY_INT to countries[position].id,
                COUNTRY_DETAIL_KEY_BOOLEAN to isDataFromDB
            )
            findNavController(it).navigate(
                R.id.action_mainFragment_to_countryDetailFragment,
                bundle
            )
        }
    }

    override fun getItemCount() = countries.size
}