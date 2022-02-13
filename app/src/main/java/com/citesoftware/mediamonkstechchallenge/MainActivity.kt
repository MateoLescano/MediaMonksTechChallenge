package com.citesoftware.mediamonkstechchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.citesoftware.mediamonkstechchallenge.APIrest.InterfaceAPI
import com.citesoftware.mediamonkstechchallenge.Album.AlbumDataModel
import com.citesoftware.mediamonkstechchallenge.APIrest.ServiceBuilder
import com.citesoftware.mediamonkstechchallenge.Album.AlbumAdapter
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
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = AlbumAdapter(response.body()!!)
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