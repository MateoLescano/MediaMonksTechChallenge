package com.citesoftware.mediamonkstechchallenge.Photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.citesoftware.mediamonkstechchallenge.APIrest.InterfaceAPI
import com.citesoftware.mediamonkstechchallenge.APIrest.ServiceBuilder
import com.citesoftware.mediamonkstechchallenge.Album.AlbumAdapter
import com.citesoftware.mediamonkstechchallenge.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val albumId = intent.getIntExtra("albumId", 1)

        val builder = ServiceBuilder.build(InterfaceAPI::class.java)

        val llamar = builder.getPhotos(albumId)

        val recyclerView = findViewById<RecyclerView>(R.id.rvPhoto)



        llamar.enqueue(object : Callback<MutableList<PhotoDataModel>> {
            override fun onResponse(
                call: Call<MutableList<PhotoDataModel>>,
                response: Response<MutableList<PhotoDataModel>>
            ) {
                if (response.isSuccessful){
                    recyclerView.apply {
                        layoutManager = GridLayoutManager(this@PhotoActivity, 2)
                        adapter = PhotoAdapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<PhotoDataModel>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })
    }
}