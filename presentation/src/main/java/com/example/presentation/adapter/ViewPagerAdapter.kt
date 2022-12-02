package com.example.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private var fragments = mutableListOf<Fragment>()

    fun updateFragments(fragments: List<Fragment>) {
        this.fragments = fragments.toMutableList()
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}