package com.citesoftware.mediamonkstechchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.citesoftware.mediamonkstechchallenge.APIrest.InterfaceAPI
import com.citesoftware.mediamonkstechchallenge.Album.AlbumDataModel
import com.citesoftware.mediamonkstechchallenge.APIrest.ServiceBuilder
import com.citesoftware.mediamonkstechchallenge.Album.AlbumAdapter
import com.citesoftware.mediamonkstechchallenge.Photo.PhotoActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = ServiceBuilder.build(InterfaceAPI::class.java)

        val llamar = builder.getAlbums()

        val recyclerView = findViewById<RecyclerView>(R.id.rvAlbum)

        llamar.enqueue(object : Callback<MutableList<AlbumDataModel>> {
            override fun onResponse(
                call: Call<MutableList<AlbumDataModel>>,
                response: Response<MutableList<AlbumDataModel>>
            ) {
                if (response.isSuccessful){

                    recyclerView.apply {
                        val adapterAlbum = AlbumAdapter(response.body()!!)
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = adapterAlbum
                        adapterAlbum.setOnItemClickListener(object : AlbumAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {
                                val intent = Intent(context, PhotoActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                intent.putExtra("albumId", position+1)
                                startActivity(intent)
                            }
                        })

                    }

                }
            }

            override fun onFailure(call: Call<MutableList<AlbumDataModel>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })

    }
}