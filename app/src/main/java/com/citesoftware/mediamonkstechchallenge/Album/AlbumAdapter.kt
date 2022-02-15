package com.citesoftware.mediamonkstechchallenge.Album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.citesoftware.mediamonkstechchallenge.R
import com.citesoftware.mediamonkstechchallenge.databinding.CardAlbumBinding

class AlbumAdapter(private val albumList: MutableList<AlbumDataModel>): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        private val binding = CardAlbumBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bindView(albumList: AlbumDataModel){
            val texto = "${albumList.title} \n (album ${albumList.id})"
            binding.tvAlbumTitle.text = texto
        }
    }

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_album, parent, false)
        return AlbumViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        return holder.bindView(albumList[position])
    }

    override fun getItemCount(): Int {
        return albumList.size
    }
}