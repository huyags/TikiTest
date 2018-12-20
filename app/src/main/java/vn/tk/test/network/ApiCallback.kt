package vn.tk.test.network

import com.huy.kotlin.network.ApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import vn.com.elc.esupport.data.livedata.EventLiveData
import vn.com.elc.esupport.data.livedata.ProgressLiveData
import vn.com.elc.esupport.data.livedata.ToastLiveData
import java.io.IOException

open class ApiCallback<T> : Callback<T?> {

    val liveData = EventLiveData<T>()

    private val tag : String

    constructor(tag: String){

        this.tag = tag
        ProgressLiveData.show()
        ApiHelper.instance.requestQueue.add(tag)
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {

        ProgressLiveData.hide()
        ToastLiveData.message = t.message
        ApiHelper.instance.requestQueue.remove(tag)
    }

    override fun onResponse(call: Call<T?>, response: Response<T?>) {

        ProgressLiveData.hide()
        ApiHelper.instance.requestQueue.remove(tag)
        try {
            if(null == response) {
                println("failed")
                return
            }
            if(response.isSuccessful && null != response.body()) {
                liveData.value = response.body()
                return
            }
            ToastLiveData.message = "${response.code()} ${response.message()}"

        } catch(e: HttpException) {
            ToastLiveData.message = e.message
        } catch(e: IllegalStateException) {
            ToastLiveData.message = e.message
        } catch(e: IOException) {
            ToastLiveData.message = e.message
        }
    }


}