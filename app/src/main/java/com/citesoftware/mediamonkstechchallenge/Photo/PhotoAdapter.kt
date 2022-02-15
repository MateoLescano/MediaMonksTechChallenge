package com.citesoftware.mediamonkstechchallenge.Photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.citesoftware.mediamonkstechchallenge.R
import com.citesoftware.mediamonkstechchallenge.databinding.CardAlbumBinding
import com.citesoftware.mediamonkstechchallenge.databinding.CardPhotoBinding
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class PhotoAdapter(private val photoList: MutableList<PhotoDataModel>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val binding = CardPhotoBinding.bind(itemView)


        fun bindView(photoList: PhotoDataModel){
            val texto = "${photoList.title} \n (foto ${photoList.id})"
            binding.tvPhotoTitle.text = texto
            Picasso.get().load(photoList.thumbnailUrl).into(binding.ivThumbnail)
            Picasso.get().load(photoList.url).into(binding.ivExpanded)

            binding.photoItem.setOnClickListener {
                if (!binding.ivExpanded.isVisible){
                    binding.tvPhotoTitle.visibility = View.INVISIBLE
                    binding.ivThumbnail.visibility = View.INVISIBLE
                    binding.ivExpanded.visibility = View.VISIBLE
                }else{
                    binding.tvPhotoTitle.visibility = View.VISIBLE
                    binding.ivThumbnail.visibility = View.VISIBLE
                    binding.ivExpanded.visibility = View.INVISIBLE
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