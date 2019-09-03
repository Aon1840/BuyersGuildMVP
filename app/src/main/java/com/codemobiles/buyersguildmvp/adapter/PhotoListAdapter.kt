package com.codemobiles.buyersguildmvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codemobiles.buyersguildmvp.R
import com.codemobiles.buyersguildmvp.model.PhotoListResponse
import kotlinx.android.synthetic.main.grid_item_mobile_image.view.grid_item

class PhotoListAdapter : RecyclerView.Adapter<PhotoHolder>() {

    private var urlArray: ArrayList<PhotoListResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        return PhotoHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.grid_item_mobile_image,
                parent,
                false
            )
        )
    }

    fun sublistList(list: ArrayList<PhotoListResponse>) {
        urlArray = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return urlArray.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        var urlImage: String = urlArray[position].url
        if (!urlImage.startsWith("https://") && !urlImage.startsWith("http://")){
            urlImage = "https://$urlImage"
        }
        Glide.with(holder.itemView.context)
            .load(urlImage)
            .into(holder.imageMobile)
    }
}

class PhotoHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageMobile = view.grid_item
}