package com.codemobiles.buyersguildmvp.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemobiles.buyersguildmvp.adapter.MobileListAdapter
import com.codemobiles.buyersguildmvp.contract.BaseSortInterface
import com.codemobiles.buyersguildmvp.contract.FavouriteListView
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.buyersguildmvp.presenter.MobileFavouriteListPresenter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import javax.inject.Inject


class FavouriteListFragment : DaggerFragment(), FavouriteListView, BaseSortInterface {

    private var mAdapter: MobileListAdapter? = null
    private var mDataArrayFavourite: ArrayList<MobileResponse> = arrayListOf()

    @Inject
    lateinit var mPresenter: MobileFavouriteListPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.codemobiles.buyersguildmvp.R.layout.fragment_recyclerview, container, false)
//        mPresenter = MobileFavouriteListPresenter()
        mPresenter?.setView(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        mPresenter?.initDatabase(context!!)
    }

    override fun updateSortType(sortType: String) {
        mPresenter?.sortMobile(mDataArrayFavourite, sortType)
    }

    override fun showMobileFav(mobileFav: ArrayList<MobileResponse>) {
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
            mPresenter?.removeMobileFav(mDataArrayFavourite, position)
        }
    }

    fun setFavAdapter(view: View) {
        mAdapter = MobileListAdapter(1, object : MobileListAdapter.MobileAdapterInterface {
            override fun getDetail(infomation: MobileResponse) {}

            override fun addFavMobile(target: MobileResponse) {}

            override fun removeFavMobile(target: MobileResponse) {}

            override fun setImage(imageTarget: ImageView, imageURL: String) {
                mPresenter?.setImageTarget(view.context, imageTarget, imageURL)
            }
        })
    }

    fun init(view: View) {
        setFavAdapter(view)
        view.rcv_frgment.let { recyclerView ->
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }

    fun sendDataFav(list: ArrayList<MobileResponse>?) {
        mPresenter?.setMobileFav(list)
    }

    fun getUnFav(): ArrayList<MobileResponse> {
        return mDataArrayFavourite
    }
}
