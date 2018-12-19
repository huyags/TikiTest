package com.huy.kotlin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.tk.test.architecture.BASE_URL
import vn.tk.test.architecture.LOG
import vn.tk.test.architecture.livedata.ApiCallback
import vn.tk.test.util.Logger

class ApiHelper private constructor() : Logger(ApiHelper::class.java, LOG) {


    fun getKeywords(apiCallback: ApiCallback<List<String>?>) {
        d("get keywords")
        service.getKeywords().enqueue(apiCallback)
    }

    private val service: ApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiService::class.java)

    companion object {
        val instance: ApiHelper by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiHelper()
        }
    }

}