package com.citesoftware.mediamonkstechchallenge.Photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.citesoftware.mediamonkstechchallenge.R
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class PhotoAdapter(private val photoList: MutableList<PhotoDataModel>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val tvPhotoTitle: TextView = itemView.findViewById(R.id.tvPhotoTitle)
        private val thumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)
        private val expanded: ImageView = itemView.findViewById(R.id.ivExpanded)
        private val photoItem: MaterialCardView = itemView.findViewById(R.id.photoItem)

        fun bindView(photoList: PhotoDataModel){
            val texto = "${photoList.title} \n (foto ${photoList.id})"
            tvPhotoTitle.text = texto
            Picasso.get().load(photoList.thumbnailUrl).into(thumbnail)
            Picasso.get().load(photoList.url).into(expanded)

            photoItem.setOnClickListener {
                if (!expanded.isVisible){
                    tvPhotoTitle.visibility = View.INVISIBLE
                    thumbnail.visibility = View.INVISIBLE
                    expanded.visibility = View.VISIBLE
                }else{
                    tvPhotoTitle.visibility = View.VISIBLE
                    thumbnail.visibility = View.VISIBLE
                    expanded.visibility = View.INVISIBLE
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        return holder.bindView(photoList[position])
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}