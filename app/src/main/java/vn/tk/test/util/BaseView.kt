package com.huy.library.ui

import android.support.annotation.StringRes

interface BaseView{

    fun toast(charSequence: CharSequence)

    fun toast(@StringRes stringRes: Int)

    fun showProgress()

    fun hideProgress()

}