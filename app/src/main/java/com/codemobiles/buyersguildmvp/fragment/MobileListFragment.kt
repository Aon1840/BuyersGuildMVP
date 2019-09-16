package com.codemobiles.buyersguildmvp.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobiles.buyersguildmvp.adapter.MobileListAdapter
import com.codemobiles.domain.model.MobileModel
import com.codemobiles.presentation.presenter.MobileListPresenter
import com.codemobiles.presentation.view.BaseSortInterface
import com.codemobiles.presentation.view.FragmentView
import com.codemobiles.presentation.view.MainView
import com.codemobiles.presentation.view.MobileListView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_recyclerview.rcv_frgment
import javax.inject.Inject

class MobileListFragment : DaggerFragment(), MobileListView,
    BaseSortInterface, FragmentView {

    @Inject
    lateinit var mPresenter: MobileListPresenter

    private var mDataArray: ArrayList<MobileModel> = arrayListOf()
    private var mAdapter: MobileListAdapter? = null
    private var mainView: MainView? = null

    fun setMainView(mainView: MainView) {
        this.mainView = mainView
    }

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

    override fun showMobileList(phoneList: ArrayList<MobileModel>) {
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

            override fun addFavMobile(mobile: MobileModel) {
                mPresenter.addFavoriteMobile(mobile)
            }

            override fun removeFavMobile(mobile: MobileModel) {
                mPresenter.removeFavoriteMobile(mobile)
            }

        })
    }

    fun getFavData(): ArrayList<MobileModel>? {
        return mPresenter.getFavouriteMobile()
    }

    fun checkCurrentFav(list: ArrayList<MobileModel>?) {
        mPresenter.getCurrentFav(mDataArray, list)
    }

    override fun favoriteAddComplete(mobile: MobileModel) {
        mainView?.addFavorite(mobile)
    }

    override fun addFavorite(data: MobileModel) {
        mPresenter.addFavoriteMobile(data)
    }

    override fun favoriteRemoveComplete(mobile: MobileModel) {
        mainView?.removeFavourite(mobile)
    }

    override fun removeFavourite(mobile: MobileModel) {
        mPresenter.removeFavoriteMobile(mobile)
    }
}
