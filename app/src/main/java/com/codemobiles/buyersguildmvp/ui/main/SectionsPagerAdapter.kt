package com.codemobiles.buyersguildmvp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.codemobiles.buyersguildmvp.contract.BaseSortInterface
import com.codemobiles.buyersguildmvp.fragment.FavouriteListFragment
import com.codemobiles.buyersguildmvp.fragment.MobileListFragment
import com.codemobiles.buyersguildmvp.model.MobileResponse


class SectionsPagerAdapter(val fm: FragmentManager) : FragmentPagerAdapter(fm) {

    fun updateSortType(sortType: String) {
        val fragment = fm.fragments
        fragment.forEach { fragment ->
            if (fragment is BaseSortInterface) {
                fragment.updateSortType(sortType)
            }
        }
    }

    fun getFavouriteMobile(): ArrayList<MobileResponse>? {
        val fragment = fm.fragments
        fragment.forEach { fragment ->
            if (fragment is MobileListFragment) {
                return fragment.getFavData()
            }

        }
        return null
    }

    fun setFavouriteMobile() {
        val favMobile = getFavouriteMobile()
        val fragment = fm.fragments
        fragment.forEach {fragment ->
            if (fragment is FavouriteListFragment) {
                fragment.sendDataFav(favMobile)
            }
        }
    }

    fun getUnFavouriteMobile(): ArrayList<MobileResponse>? {
        val fragment = fm.fragments
        fragment.forEach { fragment ->
            if (fragment is FavouriteListFragment) {
                return fragment.getUnFav()
            }

        }
        return null
    }

    fun setUnFavouriteMobile() {
        val unfavMobile = getUnFavouriteMobile()
        val fragment = fm.fragments
        fragment.forEach {fragment ->
            if (fragment is MobileListFragment) {
                fragment.checkUnFav(unfavMobile)
            }
        }
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                return MobileListFragment()
            }
            else -> {
                return FavouriteListFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "Mobile List"
            }
            else -> {
                return "Favorite"
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}