package com.codemobiles.buyersguildmvp.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.codemobiles.buyersguildmvp.R
import com.codemobiles.buyersguildmvp.model.MobileResponse
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.txt_header
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.image_favorite
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.txt_description
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.image_mobile
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.txt_rating
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.txt_price

class MobileListAdapter(private val setHolder: Int, private val mobileAdapterInterface: MobileAdapterInterface) :
    RecyclerView.Adapter<PhoneItemHolder>() {

    private var mDataArray: ArrayList<MobileResponse> = arrayListOf()

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
        holder.name.text = mDataArray[position].name
        holder.description.text = mDataArray[position].description
        holder.price.text = "Price: ${mDataArray[position].price.toString()}"
        holder.rate.text = "Rating: ${mDataArray[position].rating.toString()}"
        mobileAdapterInterface.setImage(holder.img_mobile, mDataArray[position].thumbImageURL)

        if (like) {
            holder.favorite.setImageResource(R.drawable.ic_favourite_press)
        } else {
            holder.favorite.setImageResource(R.drawable.ic_favourite)
        }

        holder.favorite.setOnClickListener {
            //switch fav or not
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

    fun setFavoriteHolder(holder: PhoneItemHolder, position: Int) {
        holder.name.text = mDataArray[position].name
        holder.description.text = "Price: ${mDataArray[position].price.toString()}"
        holder.price.text = "Rating: ${mDataArray[position].rating.toString()}"
        holder.price.alpha = 0.5f
        mobileAdapterInterface.setImage(holder.img_mobile, mDataArray[position].thumbImageURL)
        holder.rate.visibility = View.GONE
        holder.favorite.visibility = View.GONE
    }

}

class PhoneItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name = view.txt_header
    val favorite = view.image_favorite
    val description = view.txt_description
    val img_mobile = view.image_mobile
    val rate = view.txt_rating
    val price = view.txt_price
}