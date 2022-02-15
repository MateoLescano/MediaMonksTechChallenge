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
import com.citesoftware.mediamonkstechchallenge.databinding.ActivityMainBinding
import com.citesoftware.mediamonkstechchallenge.databinding.ActivityPhotoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumId = intent.getIntExtra("albumId", 1)

        val builder = ServiceBuilder.build(InterfaceAPI::class.java)

        val llamar = builder.getPhotos(albumId)

        llamar.enqueue(object : Callback<MutableList<PhotoDataModel>> {
            override fun onResponse(
                call: Call<MutableList<PhotoDataModel>>,
                response: Response<MutableList<PhotoDataModel>>
            ) {
                if (response.isSuccessful){
                    binding.rvPhoto.apply {
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