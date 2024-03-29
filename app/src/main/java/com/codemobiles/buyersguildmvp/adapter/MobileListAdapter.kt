package com.codemobiles.buyersguildmvp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codemobiles.buyersguildmvp.INFORMATION
import com.codemobiles.buyersguildmvp.R
import com.codemobiles.buyersguildmvp.activity.DetailActivity
import com.codemobiles.domain.model.MobileModel
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.txt_header
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.image_favorite
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.txt_description
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.image_mobile
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.txt_rating
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.txt_price

class MobileListAdapter(private val setHolder: Int, private val mobileAdapterInterface: MobileAdapterInterface) :
    RecyclerView.Adapter<PhoneItemHolder>() {

    private var mDataArray: ArrayList<MobileModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneItemHolder {
        return PhoneItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_mobile_list_item,
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
        holder.itemView.setOnClickListener { view ->
            val adapterPos = holder.adapterPosition
            if (adapterPos != RecyclerView.NO_POSITION) {
                val intent = Intent(view.context, DetailActivity::class.java)
                intent.putExtra(INFORMATION, mDataArray[position])
                view.context.startActivity(intent)
            }
        }
    }

    interface MobileAdapterInterface {
        fun addFavMobile(mobile: MobileModel)
        fun removeFavMobile(mobile: MobileModel)
    }

    fun submitList(list: ArrayList<MobileModel>) {
        mDataArray = list
        notifyDataSetChanged()
    }

    private fun setMobileListHoler(holder: PhoneItemHolder, position: Int) {
        var like: Boolean = mDataArray[position].fav
        holder.name.text = mDataArray[position].name
        holder.description.text = mDataArray[position].description
        holder.price.text = "Price: ${mDataArray[position].price}"
        holder.rate.text = "Rating: ${mDataArray[position].rating}"

        Glide.with(holder.itemView.context)
            .load(mDataArray[position].thumbImageURL)
            .into(holder.imgMobile)

        if (like) {
            holder.favorite.setImageResource(R.drawable.ic_favourite_press)
        } else {
            holder.favorite.setImageResource(R.drawable.ic_favourite)
        }

        holder.favorite.setOnClickListener {
            if (like) {
                holder.favorite.setImageResource(R.drawable.ic_favourite)
                mDataArray[position].fav = false
                mobileAdapterInterface.removeFavMobile(mDataArray[position])
                like = false
            } else {
                holder.favorite.setImageResource(R.drawable.ic_favourite_press)
                mDataArray[position].fav = true
                mobileAdapterInterface.addFavMobile(mDataArray[position])
                like = true
            }
        }
    }

    private fun setFavoriteHolder(holder: PhoneItemHolder, position: Int) {
        holder.name.text = mDataArray[position].name
        holder.description.text = "Price: ${mDataArray[position].price}"
        holder.price.text = "Rating: ${mDataArray[position].rating}"
        holder.price.alpha = 0.5f
        Glide.with(holder.itemView.context)
            .load(mDataArray[position].thumbImageURL)
            .into(holder.imgMobile)

        holder.rate.visibility = View.GONE
        holder.favorite.visibility = View.GONE
    }

}

class PhoneItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.txt_header
    val favorite: ImageView = view.image_favorite
    val description: TextView = view.txt_description
    val imgMobile: ImageView = view.image_mobile
    val rate: TextView = view.txt_rating
    val price: TextView = view.txt_price
}