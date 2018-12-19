package com.huy.kotlin.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("keywords.json")
    fun getKeywords(): Call<List<String>?>
}