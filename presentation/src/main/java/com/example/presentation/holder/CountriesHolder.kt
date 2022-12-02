package com.example.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.CountryItemBinding
import com.example.presentation.models.CountryPresentation
class CountriesHolder(
    private val binding: CountryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(country: CountryPresentation) {
        binding.countryName.text = country.countryName
        binding.confirmedCases.text = country.confirmedCases.toString()
        binding.lastUpdate.text = country.getFormattedDate()
    }
}