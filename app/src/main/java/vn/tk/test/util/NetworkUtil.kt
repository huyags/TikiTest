package vn.tk.test.util

import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.RequiresPermission
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkUtil{

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    fun isNetworkConnected(context: Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun isNetworkException(t: Throwable): Boolean {
        return t is SocketException ||
                t is UnknownHostException ||
                t is SocketTimeoutException
    }
}