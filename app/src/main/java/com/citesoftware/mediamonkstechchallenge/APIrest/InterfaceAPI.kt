package com.citesoftware.mediamonkstechchallenge.APIrest

import com.citesoftware.mediamonkstechchallenge.Album.AlbumDataModel
import retrofit2.Call
import retrofit2.http.GET

interface InterfaceAPI {

    @GET("/albums")
    fun getAlbums(): Call<MutableList<AlbumDataModel>>

}