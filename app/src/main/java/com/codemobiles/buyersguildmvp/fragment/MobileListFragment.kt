package com.codemobiles.buyersguildmvp.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobiles.buyersguildmvp.adapter.MobileListAdapter
import com.codemobiles.buyersguildmvp.contract.BaseSortInterface
import com.codemobiles.buyersguildmvp.contract.MobileListView
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.buyersguildmvp.presenter.MobileListPresenter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_recyclerview.rcv_frgment
import javax.inject.Inject

class MobileListFragment : DaggerFragment(), MobileListView, BaseSortInterface {

    private var mDataArray: ArrayList<MobileResponse> = arrayListOf()
    private var mAdapter: MobileListAdapter? = null

    @Inject
    lateinit var mPresenter: MobileListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.codemobiles.buyersguildmvp.R.layout.fragment_recyclerview, container, false)
        mPresenter.setView(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        setMobileAdapter()
        mDataArray.clear()
        mPresenter.feedMobileList()
        mPresenter.checkFavourite()
        rcv_frgment.let { recyclerView ->
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(view.context)
        }
    }

    override fun showMobileList(phoneList: ArrayList<MobileResponse>) {
        mDataArray = phoneList
        mAdapter?.submitList(mDataArray)
    }

    override fun setPreFavourite() {
        mPresenter.getCurrentFav(mDataArray, mPresenter.getFavouriteMobile())
    }

    override fun updateSortType(sortType: String) {
        mPresenter.sortMobile(mDataArray, sortType)
    }

    private fun setMobileAdapter() {
        mAdapter = MobileListAdapter(0, object : MobileListAdapter.MobileAdapterInterface {

            override fun addFavMobile(mobile: MobileResponse) {
                mPresenter.addFavoriteMobile(mobile)
            }

            override fun removeFavMobile(mobile: MobileResponse) {
                mPresenter.removeFavoriteMobile(mobile)
            }

        })
    }

    fun getFavData(): ArrayList<MobileResponse>? {
        return mPresenter.getFavouriteMobile()
    }

    fun checkUnFav(list: ArrayList<MobileResponse>?) {
        mPresenter.getCurrentFav(mDataArray, list)
    }

}
