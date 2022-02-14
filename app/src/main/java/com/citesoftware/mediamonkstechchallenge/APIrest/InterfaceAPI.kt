package com.citesoftware.mediamonkstechchallenge.APIrest

import com.citesoftware.mediamonkstechchallenge.Album.AlbumDataModel
import com.citesoftware.mediamonkstechchallenge.Photo.PhotoDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface InterfaceAPI {

    @GET("/albums")
    fun getAlbums(): Call<MutableList<AlbumDataModel>>

    @GET("/albums/{albumId}/photos")
    fun getPhotos(@Path("albumId") albumId: Int): Call<MutableList<PhotoDataModel>>

}