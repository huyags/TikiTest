package vn.tk.test.util.base

interface BaseDialogInt {

    interface ShowListener {
        fun onShowDismiss(tag: String?)
    }

    interface DismissListener {
        fun onDialogDismiss(tag: String?)
    }

    fun show()

    fun dismiss()

    fun isShowing(): Boolean

    fun isGone(): Boolean

    fun addShowListener(tag: String, block: (String) -> Unit)

    fun addDismissListener(tag: String, block: (String) -> Unit)

    fun dropListener()

    fun disableCancelOnTouchOutside()

}