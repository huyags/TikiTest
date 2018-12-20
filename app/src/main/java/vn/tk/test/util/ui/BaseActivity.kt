package com.huy.library.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.huy.library.handler.ViewClickListener
import vn.tk.test.util.dialog.ProgressDialog


abstract class BaseActivity : AppCompatActivity(),
    BaseView,
    ViewClickListener {

    private var progressDialog: ProgressDialog? = null

    @LayoutRes
    protected abstract fun layoutResource(): Int

    /**
     * [AppCompatActivity] override
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource())
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog?.dismiss()
        progressDialog = null
    }

    /**
     * [BaseView] implement
     */
    override fun toast(charSequence: CharSequence) {
        Toast.makeText(this, charSequence, Toast.LENGTH_SHORT).show()
    }

    override fun toast(stringRes: Int) {
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        if(null == progressDialog)
             progressDialog = ProgressDialog(this)
         progressDialog?.show()
    }

    override fun hideProgress() {
        progressDialog?.dismiss()
    }


}