package vn.tk.test.util.dialog

import android.app.Activity
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import vn.tk.test.R


class ProgressDialog : BaseDialog {

    constructor(activity: Activity) : super(activity) {

        val window = self.window ?: return
        val layoutParams: WindowManager.LayoutParams = window.attributes ?: return

        layoutParams.gravity = Gravity.TOP
        layoutParams.flags = layoutParams.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(window.context, R.color.colorPrimary)
        }
        window.attributes = layoutParams
    }

    override fun layoutRes() = R.layout.dialog_progress

    override fun theme() = R.style.DialogProgress

    override fun onViewCreated(view: View) {
        //disableCancelOnTouchOutside()
    }

}