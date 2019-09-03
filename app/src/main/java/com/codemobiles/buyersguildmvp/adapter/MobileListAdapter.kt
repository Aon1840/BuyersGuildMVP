package com.codemobiles.buyersguildmvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemobiles.buyersguildmvp.R
import com.codemobiles.buyersguildmvp.model.MobileResponse
import kotlinx.android.synthetic.main.mobile_list_item.view.ivImg
import kotlinx.android.synthetic.main.mobile_list_item.view.tvName
import kotlinx.android.synthetic.main.mobile_list_item.view.tvDescription
import kotlinx.android.synthetic.main.mobile_list_item.view.tvPrice
import kotlinx.android.synthetic.main.mobile_list_item.view.tvRating
import kotlinx.android.synthetic.main.mobile_list_item.view.imgFav

class MobileListAdapter(private val setHolder: Int, private val mobileAdapterInterface: MobileAdapterInterface) :
    RecyclerView.Adapter<PhoneItemHolder>() {

    private var mDataArray: ArrayList<MobileResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneItemHolder {
        return PhoneItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.mobile_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }

    override fun onBindViewHolder(holder: PhoneItemHolder, position: Int) {
        if (setHolder == 0) {
            setMobileListHoler(holder, position)
        } else {
            setFavoriteHolder(holder, position)
        }
        holder.itemView.setOnClickListener {
            val adapterPos = holder.adapterPosition
            if (adapterPos != RecyclerView.NO_POSITION) {
                mobileAdapterInterface.getDetail(mDataArray[position])
            }
        }
    }

    interface MobileAdapterInterface {
        fun getDetail(infomation: MobileResponse)
        fun setImage(imageTarget: ImageView, imageURL: String)
        fun addFavMobile(target: MobileResponse)
        fun removeFavMobile(target: MobileResponse)
    }

    fun submitList(list: ArrayList<MobileResponse>) {
        mDataArray = list
        notifyDataSetChanged()
    }

    fun setMobileListHoler(holder: PhoneItemHolder, position: Int) {
        var like: Boolean = mDataArray[position].fav
        holder.tvName.text = mDataArray[position].name
        holder.tvDescription.text = mDataArray[position].description
        holder.tvPrice.text = mDataArray[position].price.toString()
        holder.tvRating.text = mDataArray[position].rating.toString()
        mobileAdapterInterface.setImage(holder.ivFav, mDataArray[position].thumbImageURL)

//        if (like) {
//            holder.favorite.setImageResource(R.drawable.ic_favorite)
//        } else {
//            holder.favorite.setImageResource(R.drawable.ic_heart)
//        }
//
//        holder.favorite.setOnClickListener {
//            //switch fav or not
//            if (like) {
//                holder.favorite.setImageResource(R.drawable.ic_heart)
//                mDataArray[position].fav = false
//                //write data here
//                mobileAdapterInterface.removeFavMobile(mDataArray[position])
//                like = false
//            } else {
//                holder.favorite.setImageResource(R.drawable.ic_favorite)
//                mDataArray[position].fav = true
//                //write data here
//                mobileAdapterInterface.addFavMobile(mDataArray[position])
//                like = true
//            }
//        }
    }

    fun setFavoriteHolder(holder: PhoneItemHolder, position: Int) {
        holder.tvName.text = mDataArray[position].name
        holder.tvDescription.text = mDataArray[position].price.toString()
        holder.tvPrice.text = mDataArray[position].rating.toString()
        holder.tvPrice.alpha = 0.5f
        mobileAdapterInterface.setImage(holder.ivImg, mDataArray[position].thumbImageURL)
        holder.tvRating.visibility = View.GONE
        holder.ivFav.visibility = View.GONE
    }

}

class PhoneItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivImg: ImageView = view.ivImg
    val tvName: TextView = view.tvName
    val tvDescription: TextView = view.tvDescription
    val tvPrice: TextView = view.tvPrice
    val tvRating: TextView = view.tvRating
    val ivFav: ImageButton = view.imgFav
}