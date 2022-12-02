package com.example.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.models.SortType
import com.example.presentation.R
import com.example.presentation.adapter.ViewPagerAdapter
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.viewmodel.SortViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val sortVm by sharedViewModel<SortViewModel>()
    private var mMediator: TabLayoutMediator? = null
    private var mFragments: List<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFragments = listOf(
            AllCountriesFragment(),
            WatchedCountriesFragment()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
        mFragments?.let { list ->
            adapter.updateFragments(list)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBar.sortButton.setOnClickListener {
            sortVm.updateSort()
        }
        observeLiveData()
        mMediator = TabLayoutMediator(binding.appBar.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "All"
                1 -> tab.text = "Watchlist"
            }
        }
        mMediator?.attach()
    }

    private fun observeLiveData() {
        sortVm.getSortTypeLiveData().observe(viewLifecycleOwner, { sortType ->
            changeImage(sortType)
        })
    }

    private fun changeImage(sortType: SortType) {
        val image = when (sortType) {
            is SortType.Alphabet -> R.drawable.ic_baseline_sort_by_alpha_24
            is SortType.ConfirmedAsc -> R.drawable.ic_baseline_sort_24
        }
        binding.appBar.sortButton.setImageResource(image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mMediator?.detach()
        mMediator = null
        binding.viewPager.adapter = null
        binding.appBar.tabLayout.setupWithViewPager(null)
        _binding = null
    }
}