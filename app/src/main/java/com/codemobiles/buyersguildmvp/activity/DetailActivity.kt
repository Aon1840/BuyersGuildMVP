package com.codemobiles.buyersguildmvp.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobiles.buyersguildmvp.INFORMATION
import com.codemobiles.buyersguildmvp.R
import com.codemobiles.buyersguildmvp.adapter.PhotoListAdapter
import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.model.PhotoListModel
import com.codemobiles.presentation.presenter.DetailPresenter
import com.codemobiles.presentation.view.DetailView
import kotlinx.android.synthetic.main.activity_mobile_detail.detail_rcv
import kotlinx.android.synthetic.main.activity_mobile_detail.detail_toolbar
import kotlinx.android.synthetic.main.activity_mobile_detail.mobile_detail
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailBrand
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailDescription
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailName
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailPrice
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailRating
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailView {

    @Inject
    lateinit var mPresenter: DetailPresenter

    private var mData: MobileModel? = null
    private var mAdapter: PhotoListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_detail)
        setupData()
    }

    override fun setName(name: String?) {
        txt_detailName.text = name
    }

    override fun setBrand(brand: String?) {
        txt_detailBrand.text = brand
    }

    override fun setPrice(price: String?) {
        txt_detailPrice.text = price
    }

    override fun setDescription(description: String?) {
        txt_detailDescription.text = description
    }

    override fun setRating(rating: String?) {
        txt_detailRating.text = rating
    }

    override fun setImageList(imageList: ArrayList<PhotoListModel>?) {
        imageList?.let { list -> mAdapter?.sublistList(list) }
    }


    private fun setupData() {
        mPresenter.setView(this)
        mAdapter = PhotoListAdapter()

        detail_rcv.adapter = mAdapter
        detail_rcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mData = intent.getParcelableExtra(INFORMATION) as MobileModel
        mPresenter.getPassData(mData)
        mPresenter.feedImageDetail(mData?.id)
        detail_toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun getView(): View {
        return mobile_detail
    }
}
