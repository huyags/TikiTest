package vn.tk.test.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.support.annotation.StringRes
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import vn.tk.test.app.App

class AppUtil private constructor() {

    init {
        throw UnsupportedOperationException("Not allow instantiating object.")
    }

    companion object {

        private var sDeviceId: String? = null

        private var sDensity = 1f

        private val sDisplaySize = Point(0, 0)

        private val packageName: String

        private val sUIHandler = Handler(Looper.getMainLooper())

        init {
            sDensity = App.instance.resources.displayMetrics.density
            packageName = App.instance.packageName
        }

        fun dp(dp: Float): Int {
            return if (dp == 0f) {
                0
            } else Math.ceil((sDensity * dp).toDouble()).toInt()
        }

        fun dp2(dp: Float): Int {
            return if (dp == 0f) {
                0
            } else Math.floor((sDensity * dp).toDouble()).toInt()
        }

        fun dp2px(dp: Float): Float {
            return sDensity * dp
        }

        val deviceId: String?
            @SuppressLint("HardwareIds")
            get() {

                val context = App.instance
                if (TextUtils.isEmpty(sDeviceId)) {
                    try {
                        sDeviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
                return sDeviceId
            }

        fun currentOsVersion(): String {
            return Build.VERSION.RELEASE
        }

        fun currentOsVersionCode(): Int {
            return Build.VERSION.SDK_INT
        }

        val deviceName: String
            get() {
                val manufacturer = Build.MANUFACTURER
                val model = Build.MODEL
                return if (model.startsWith(manufacturer)) {
                    capitalize(model)
                } else capitalize(manufacturer) + " " + model
            }

        private fun capitalize(str: String): String {
            if (TextUtils.isEmpty(str)) {
                return str
            }
            val arr = str.toCharArray()
            var capitalizeNext = true
            var phrase = ""
            for (c in arr) {
                if (capitalizeNext && Character.isLetter(c)) {
                    phrase += Character.toUpperCase(c)
                    capitalizeNext = false
                    continue
                } else if (Character.isWhitespace(c)) {
                    capitalizeNext = true
                }
                phrase += c
            }
            return phrase
        }

        fun getDisplaySize(context: Context): Point {
            if (sDisplaySize.x == 0 && sDisplaySize.y == 0) {
                val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                wm.defaultDisplay.getSize(sDisplaySize)
            }
            return Point(sDisplaySize)
        }

        fun showToast(message: String) {
            if (TextUtils.isEmpty(message)) {
                return
            }
            if (isOnMainThread) {
                Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show()
            } else {
                sUIHandler.post { Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show() }
            }
        }

        fun showToast(@StringRes stringRes: Int, vararg arguments: Any) {
            try {
                val message = App.appContext.getString(stringRes, *arguments)
                if (isOnMainThread) {
                    Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show()
                } else {
                    sUIHandler.post { Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show() }
                }
            } catch (e: Resources.NotFoundException) {
                e.printStackTrace()
            }

        }

        fun showToast(@StringRes stringRes: Int) {
            val context = App.appContext
            try {
                val message = context.getString(stringRes)
                if (isOnMainThread) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                } else {
                    sUIHandler.post { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
                }
            } catch (e: Resources.NotFoundException) {
                e.printStackTrace()
            }

        }

        fun isTablet(context: Context): Boolean {
            return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
        }

        fun getString(@StringRes stringRes: Int): String {
            try {
                return App.instance.getString(stringRes)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }

        val isOnMainThread: Boolean
            get() = Looper.myLooper() == Looper.getMainLooper()

        fun resourceToUri(context: Context?, resID: Int): Uri {
            if (context == null) {
                return Uri.parse("")
            }
            try {
                return Uri.parse(
                    ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                            context.resources.getResourcePackageName(resID) + '/'.toString() +
                            context.resources.getResourceTypeName(resID) + '/'.toString() +
                            context.resources.getResourceEntryName(resID)
                )
            } catch (ignored: Exception) {
                return Uri.parse("")
            }

        }

        fun openAppSettings(activity: Activity, requestCode: Int) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivityForResult(intent, requestCode)
        }
    }

}
