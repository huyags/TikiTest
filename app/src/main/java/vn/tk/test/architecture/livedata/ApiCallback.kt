package vn.tk.test.architecture.livedata

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import vn.com.elc.esupport.data.livedata.EventLiveData
import vn.com.elc.esupport.data.livedata.ToastLiveData
import java.io.IOException

open class ApiCallback<T> : Callback<T?> {

    val liveData = EventLiveData<T>()

    override fun onFailure(call: Call<T?>, t: Throwable) {
        ToastLiveData.message = t.message
    }

    override fun onResponse(call: Call<T?>, response: Response<T?>) {
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