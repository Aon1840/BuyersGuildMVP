package com.codemobiles.buyersguildmvp.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemobiles.buyersguildmvp.R
import com.codemobiles.buyersguildmvp.adapter.MobileListAdapter
import com.codemobiles.presentation.view.BaseSortInterface
import com.codemobiles.presentation.view.FavouriteListView
import com.codemobiles.domain.model.MobileModel
import com.codemobiles.presentation.presenter.MobileFavouriteListPresenter
import com.codemobiles.presentation.view.FragmentView
import com.codemobiles.presentation.view.MainView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_recyclerview.view.rcv_frgment
import javax.inject.Inject


class FavouriteListFragment : DaggerFragment(), FavouriteListView,
    BaseSortInterface, FragmentView {

    private var mAdapter: MobileListAdapter? = null
    private var mDataArrayFavourite: ArrayList<MobileModel> = arrayListOf()

    @Inject
    lateinit var mPresenter: MobileFavouriteListPresenter
    private var mainView: MainView? = null

    fun setMainView(mainView: MainView){
        this.mainView = mainView
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        mPresenter.setView(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    override fun updateSortType(sortType: String) {
        mPresenter.sortMobile(mDataArrayFavourite, sortType)
    }

    override fun showMobileFav(mobileFav: ArrayList<MobileModel>) {
        mDataArrayFavourite = mobileFav
        mAdapter?.submitList(mDataArrayFavourite)
    }

    private var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT
    ) {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            val dragFlags = 0
            val swipeFlags = ItemTouchHelper.START
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            mPresenter.removeMobileFav(mDataArrayFavourite, position)
        }
    }

    private fun setFavAdapter() {
        mAdapter = MobileListAdapter(1, object : MobileListAdapter.MobileAdapterInterface {

            override fun addFavMobile(target: MobileModel) {}

            override fun removeFavMobile(target: MobileModel) {}

        })
    }

    fun init(view: View) {
        setFavAdapter()
        view.rcv_frgment.let { recyclerView ->
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }

    fun setDataFav(list: ArrayList<MobileModel>?) {
        mPresenter.setMobileFav(list)
    }

    fun getFav(): ArrayList<MobileModel> {
        return mDataArrayFavourite
    }

    override fun addFavorite(data: MobileModel) {
        mDataArrayFavourite.add(data)
        mAdapter?.submitList(mDataArrayFavourite)
    }

    override fun removeFavourite(mobile: MobileModel) {
        mDataArrayFavourite.remove(mobile)
        mAdapter?.submitList(mDataArrayFavourite)
    }

    override fun favoriteRemoveComplete(mobile: MobileModel) {
        mainView?.removeFavourite(mobile)
    }
}
