package com.codemobiles.buyersguildmvp.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.codemobiles.buyersguildmvp.LIST_SORT
import com.codemobiles.buyersguildmvp.R
import com.codemobiles.buyersguildmvp.adapter.SectionsPagerAdapter
import com.codemobiles.domain.model.MobileModel
import com.codemobiles.presentation.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentPage: Int = 0
    var sectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, object : MainView {
            override fun removeFavourite(mobile: MobileModel) {
                sectionsPagerAdapter?.favFragment?.removeFavourite(mobile)
            }

            override fun addFavorite(mobile: MobileModel) {
                sectionsPagerAdapter?.favFragment?.addFavorite(mobile)
            }
        }, object : MainView {
            override fun removeFavourite(mobile: MobileModel) {
                sectionsPagerAdapter?.listFragment?.removeFavourite(mobile)
            }

            override fun addFavorite(mobile: MobileModel) {
                sectionsPagerAdapter?.listFragment?.addFavorite(mobile)
            }

        })
        main_viewPager.adapter = sectionsPagerAdapter
        main_tab.setupWithViewPager(main_viewPager)
        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        currentPage = 0
                        sectionsPagerAdapter?.setListMobile()
                    }
                    else -> {
                        currentPage = 1
                        sectionsPagerAdapter?.setFavouriteMobile()
                    }
                }
            }

        })

        image_filter.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setSingleChoiceItems(LIST_SORT, -1) { dialogInterface, i ->
                    Toast.makeText(this, LIST_SORT[i], Toast.LENGTH_SHORT).show()
                    sectionsPagerAdapter?.updateSortType(LIST_SORT[i])
                    dialogInterface.dismiss()
                }
                .create()
            builder.show()
        }

    }
}