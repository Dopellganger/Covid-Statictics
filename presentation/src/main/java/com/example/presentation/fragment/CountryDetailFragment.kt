package com.example.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCountryDetailBinding
import com.example.presentation.models.CountryDetailPresentation
import com.example.presentation.viewmodel.CountryDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

private val DECIMAL_FORMAT = DecimalFormat("#.##")
const val COUNTRY_DETAIL_KEY_INT = "COUNTRY_DETAIL_KEY_INT"
const val COUNTRY_DETAIL_KEY_BOOLEAN = "COUNTRY_DETAIL_KEY_BOOLEAN"

class CountryDetailFragment : Fragment() {

    private var _binding: FragmentCountryDetailBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModel<CountryDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        arguments?.let {
            val countryId = it.getInt(COUNTRY_DETAIL_KEY_INT)
            val loadFromDB = it.getBoolean(COUNTRY_DETAIL_KEY_BOOLEAN)
            if (loadFromDB) {
                binding.deleteButton.visibility = View.VISIBLE
            } else {
                binding.addButton.visibility = View.VISIBLE
            }
            if (vm.isLoaded) {
                vm.load(countryId, loadFromDB)
                vm.isLoaded = false
            }
        }
        setButtonsClicksListeners()
    }

    private fun observeLiveData() {
        vm.getCountryDetailLive().observe(viewLifecycleOwner, { country ->
            setContent(country)
        })
        vm.getErrorLive().observe(viewLifecycleOwner, { error ->
            showErrorDialog("${error.code}: ${error.error}")
        })
        vm.getNetworkErrorLive().observe(viewLifecycleOwner, {
            showErrorDialog(NETWORK_ERROR_MESSAGE)
        })
//        vm.getIsMapOpening().observe(viewLifecycleOwner, {
//
//        })
    }

    private fun setContent(country: CountryDetailPresentation) {
        binding.apply {
            countryName.text = country.countryName
            lastUpdate.text = country.getFormattedDate()
            confirmedCases.text = country.confirmedCases.toString()
            deathCases.text = country.deadCases.toString()
            incidentRate.text = DECIMAL_FORMAT.format(country.incidentRate)
            mortalityRate.text = DECIMAL_FORMAT.format(country.mortalityRate)
            if (country.is03 == null) {
                binding.countryCodeCard.visibility = View.INVISIBLE
            } else {
                countryCode.text = country.is03
            }
            if (country.x == null || country.y == null) {
                binding.mapCoordinateCard.visibility = View.INVISIBLE
            } else {
                latitude.text = DECIMAL_FORMAT.format(country.y)
                longitude.text = DECIMAL_FORMAT.format(country.x)
            }
        }
    }

    private fun setButtonsClicksListeners() {
        binding.mapCoordinateCard.setOnClickListener {
            activity?.applicationContext?.let { context ->
                vm.openMap { latitude, longitude ->
                    val uri = "geo:$latitude,$longitude"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
            }
        }
        binding.deleteButton.setOnClickListener {
            vm.delete()
        }
        binding.addButton.setOnClickListener {
            vm.save()
        }
    }

    private fun showErrorDialog(message: String) {
        val bundle = bundleOf(DIALOG_PARAGRAPH_KEY to message)
        findNavController().navigate(
            R.id.action_countryDetailFragment_to_errorDialogFragment,
            bundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}