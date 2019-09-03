package com.codemobiles.buyersguildmvp.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager

import com.codemobiles.buyersguildmvp.R
import com.codemobiles.buyersguildmvp.adapter.MobileListAdapter
import com.codemobiles.buyersguildmvp.contract.MobileListView
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.buyersguildmvp.presenter.MobileListPresenter
import kotlinx.android.synthetic.main.fragment_mobile_list.view.*

class MobileListFragment : Fragment(), MobileListView {

    private var mDataArray: ArrayList<MobileResponse> = arrayListOf()
    private var mAdapter: MobileListAdapter? = null
    private var mPresenter: MobileListPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mobile_list, container, false)
        setMobileAdapter(view)
        mDataArray.clear()
        mPresenter?.feedMobileList()

        view.recycleView.let {recyclerView ->
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }

        return view
    }

    fun setMobileAdapter(view: View) {
        mAdapter = MobileListAdapter(0, object : MobileListAdapter.MobileAdapterInterface {
            override fun getDetail(infomation: MobileResponse) {

            }

            override fun setImage(imageTarget: ImageView, imageURL: String) {
            }

            override fun addFavMobile(target: MobileResponse) {
            }

            override fun removeFavMobile(target: MobileResponse) {
            }

        })
    }

    override fun showMobileList(phoneList: ArrayList<MobileResponse>) {
        mDataArray = phoneList
        mAdapter?.submitList(mDataArray)
    }

    override fun setPreFavourite() {

    }
}
