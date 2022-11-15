package com.ao.anew.network

import androidx.lifecycle.LiveData
import com.ao.anew.model.NewsModel
import com.ao.anew.util.GenericApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("v2/top-headlines?country=in&apiKey=${"https://newsapi.org/"}")
    fun getNewsArtcles(): LiveData<GenericApiResponse<NewsModel>>

}