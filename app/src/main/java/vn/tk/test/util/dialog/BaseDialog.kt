package vn.tk.test.util.dialog

import android.app.Activity
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View

abstract class BaseDialog(val activity: Activity) : BaseDialogInt {

    protected var self: AlertDialog

    protected var view: View

    var tag: String? = null

    var showListener: BaseDialogInt.ShowListener? = null

    var dismissListener: BaseDialogInt.DismissListener? = null

    init {
        view = LayoutInflater.from(activity).inflate(layoutRes(), null)
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity, theme())
        builder.setView(view)
        self = builder.create()
        onViewCreated(view)
    }

    @StyleRes
    protected open fun theme(): Int = 0

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun onViewCreated(view: View)

    override fun addShowListener(tag: String, block: (String) -> Unit) {
        self.setOnShowListener { block(tag) }
    }

    override fun addDismissListener(tag: String, block: (String) -> Unit) {
        self.setOnDismissListener { block(tag) }
    }

    override fun dropListener() {
        self.setOnShowListener(null)
        self.setOnDismissListener(null)
    }

    override fun disableCancelOnTouchOutside() {
        self.setCanceledOnTouchOutside(false)
    }

    override fun show() {
        if (self.isShowing) return

        if (null != tag && null != showListener) {
            self.setOnShowListener {
                showListener?.onShowDismiss(tag!!)
            }
        }
        self.show()
        if (null != tag && null != dismissListener) {
            self.setOnShowListener {
                dismissListener?.onDialogDismiss(tag!!)
            }
        }
    }

    override fun dismiss() {
        if (self.isShowing) self.dismiss()
    }

    override fun isShowing(): Boolean {
        return self.isShowing
    }

    override fun isGone(): Boolean {
        return !self.isShowing
    }

}