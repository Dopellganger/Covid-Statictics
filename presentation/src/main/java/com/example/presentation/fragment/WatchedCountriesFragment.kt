package com.example.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.adapter.CountriesAdapter
import com.example.presentation.databinding.FragmentWatchedCountriesBinding
import com.example.presentation.viewmodel.SortViewModel
import com.example.presentation.viewmodel.WatchedCountriesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchedCountriesFragment : Fragment() {

    private var _binding: FragmentWatchedCountriesBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModel<WatchedCountriesViewModel>()
    private val sortVm by sharedViewModel<SortViewModel>()
    private var adapter: CountriesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchedCountriesBinding.inflate(layoutInflater)
        adapter = CountriesAdapter()
        adapter?.setUploadingDataFrommDB()
        binding.recyclerViewWatched.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        vm.load()
    }

    private fun observeLiveData() {
        vm.getCountriesLive().observe(viewLifecycleOwner, { countries ->
            adapter?.updateCountries(countries)
        })
        sortVm.getSortTypeLiveData().observe(viewLifecycleOwner, { sortType ->
            vm.setSortType(sortType)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        _binding = null
    }
}