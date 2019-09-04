package com.codemobiles.buyersguildmvp.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobiles.buyersguildmvp.INFORMATION
import com.codemobiles.buyersguildmvp.R
import com.codemobiles.buyersguildmvp.adapter.PhotoListAdapter
import com.codemobiles.buyersguildmvp.contract.DetailVIew
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.buyersguildmvp.model.PhotoListResponse
import com.codemobiles.buyersguildmvp.presenter.DetailPresenter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailName
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailBrand
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailPrice
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailDescription
import kotlinx.android.synthetic.main.activity_mobile_detail.txt_detailRating
import kotlinx.android.synthetic.main.activity_mobile_detail.detail_rcv
import kotlinx.android.synthetic.main.activity_mobile_detail.detail_toolbar
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity(), DetailVIew {

    private var mData: MobileResponse? = null
    private var mAdapter: PhotoListAdapter? = null

    @Inject
    lateinit var mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_detail)
        setupData()
    }

    override fun setName(name: String) {
        txt_detailName.text = name
    }

    override fun setBrand(brand: String) {
        txt_detailBrand.text = brand
    }

    override fun setPrice(price: String) {
        txt_detailPrice.text = price
    }

    override fun setDescription(description: String) {
        txt_detailDescription.text = description
    }

    override fun setRating(rating: String) {
        txt_detailRating.text = rating
    }

    override fun setImageList(imageList: ArrayList<PhotoListResponse>) {
        mAdapter?.sublistList(imageList)
    }

    private fun setupData() {
//        mPresenter = DetailPresenter()
        mPresenter?.setView(this)
        mAdapter = PhotoListAdapter()

        detail_rcv.setAdapter(mAdapter)
        detail_rcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mData = intent.extras?.getSerializable(INFORMATION) as MobileResponse
        mPresenter?.getPassData(mData!!)
        mPresenter?.feedImageDetail(mData!!.id)
        detail_toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
