package vn.tk.test.app

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {

        lateinit var instance: App private set

        val appContext: Context get() = instance.applicationContext

        val baseContext: Context get() = instance.baseContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}