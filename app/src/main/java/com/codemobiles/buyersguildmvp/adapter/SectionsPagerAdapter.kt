package com.codemobiles.buyersguildmvp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.codemobiles.buyersguildmvp.FAVOURITE
import com.codemobiles.buyersguildmvp.MOBILE_LIST
import com.codemobiles.buyersguildmvp.fragment.FavouriteListFragment
import com.codemobiles.buyersguildmvp.fragment.MobileListFragment
import com.codemobiles.domain.model.MobileModel
import com.codemobiles.presentation.view.BaseSortInterface
import com.codemobiles.presentation.view.FragmentView
import com.codemobiles.presentation.view.MainView


class SectionsPagerAdapter(
    private val fm: FragmentManager,
    private val mobileListMainView: MainView,
    private val favouriteMainView: MainView
) : FragmentPagerAdapter(fm) {
    var listFragment: FragmentView? = null
    var favFragment: FragmentView? = null

    private fun getListMobile(): ArrayList<MobileModel>? {
        val fragment = fm.fragments
        fragment.forEach { mobileList ->
            if (mobileList is MobileListFragment) {
                return mobileList.getFavData()
            }
        }
        return null
    }

    private fun getFavouriteListMobile(): ArrayList<MobileModel>? {
        fm.fragments.forEach { favouriteList ->
            if (favouriteList is FavouriteListFragment) {
                return favouriteList.getFav()
            }
        }
        return null
    }

    fun setListMobile() {
        val mobile = getFavouriteListMobile()
        val fragment = fm.fragments
        fragment.forEach { mobileList ->
            if (mobileList is MobileListFragment) {
                mobileList.checkCurrentFav(mobile)
            }
        }
    }

    fun setFavouriteMobile() {
        val favMobile = getListMobile()
        val fragment = fm.fragments
        fragment.forEach { favouriteList ->
            if (favouriteList is FavouriteListFragment) {
                favouriteList.setDataFav(favMobile)
            }
        }
    }

    fun updateSortType(sortType: String) {
        val fragment = fm.fragments
        fragment.forEach { _ ->
            if (fragment is BaseSortInterface) {
                fragment.updateSortType(sortType)
            }
        }
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = MobileListFragment().apply {
                    this.setMainView(mobileListMainView)
                }
                listFragment = fragment
                fragment
            }
            else -> {
                val fragment = FavouriteListFragment().apply {
                    this.setMainView(favouriteMainView)
                }
                favFragment = fragment
                fragment
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                MOBILE_LIST
            }
            else -> {
                return FAVOURITE
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}