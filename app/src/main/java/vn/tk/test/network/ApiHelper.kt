package com.huy.kotlin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.tk.test.app.BASE_URL
import vn.tk.test.app.LOG
import vn.tk.test.network.ApiCallback
import vn.tk.test.util.Logger
import okhttp3.OkHttpClient
import java.util.*


class ApiHelper private constructor() : Logger(ApiHelper::class.java, LOG) {


    /**
     * Linked list of request tag
     * guaranteed a unique request at a time
     */
    var requestQueue: Queue<String> = LinkedList<String>()

    /**
     * Init instance service
     */
    private var client = OkHttpClient()

    private val service: ApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()
        .create(ApiService::class.java)

    companion object {

        val instance: ApiHelper by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiHelper()
        }

        val service : ApiService get() = instance.service
    }

}