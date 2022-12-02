package com.example.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.adapter.CountriesAdapter
import com.example.presentation.databinding.FragmentCountriesAllBinding
import com.example.presentation.viewmodel.AllCountriesViewModel
import com.example.presentation.viewmodel.SortViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCountriesFragment : Fragment() {

    private var _binding: FragmentCountriesAllBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModel<AllCountriesViewModel>()
    private val sortVm by sharedViewModel<SortViewModel>()
    private var adapter: CountriesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesAllBinding.inflate(layoutInflater)
        adapter = CountriesAdapter()
        binding.recyclerViewAll.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {
        vm.getErrorLive().observe(viewLifecycleOwner, { error ->
            showErrorDialog("${error.code}: ${error.error}")
        })
        vm.getNetworkErrorLive().observe(viewLifecycleOwner, {
            showErrorDialog(NETWORK_ERROR_MESSAGE)
        })
        vm.getCountriesLive().observe(viewLifecycleOwner, { countries ->
            adapter?.updateCountries(countries)
        })
        sortVm.getSortTypeLiveData().observe(viewLifecycleOwner, { sortType ->
            vm.setSortType(sortType)
        })
    }

    private fun showErrorDialog(message: String) {
        val bundle = bundleOf(DIALOG_PARAGRAPH_KEY to message)
        findNavController().navigate(R.id.action_mainFragment_to_errorDialogFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        _binding = null
    }
}