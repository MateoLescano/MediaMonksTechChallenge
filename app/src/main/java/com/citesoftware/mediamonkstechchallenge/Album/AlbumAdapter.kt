package com.citesoftware.mediamonkstechchallenge.Album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.citesoftware.mediamonkstechchallenge.R

class AlbumAdapter(private val albumList: MutableList<AlbumDataModel>): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val tvAlbumTitle: TextView = itemView.findViewById(R.id.tvAlbumTitle)

        fun bindView(albumList: AlbumDataModel){
            val texto = "${albumList.title} \n (album ${albumList.id})"
            tvAlbumTitle.text = texto
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        return holder.bindView(albumList[position])
    }

    override fun getItemCount(): Int {
        return albumList.size
    }
}