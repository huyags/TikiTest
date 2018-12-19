package com.huy.library.handler

import android.os.SystemClock
import android.view.View

class ViewClickHandler constructor(private val clickListener: ViewClickListener?) {

    private var lastClickTime: Long = 0

    private var onViewClick: View.OnClickListener? = null

    fun addClickListener(vararg views: View) {

        if (onViewClick == null)
            onViewClick = View.OnClickListener { v ->
                v.isClickable = false
                v.postDelayed({ v.isClickable = true }, lastClickTime)
                if (SystemClock.elapsedRealtime() - lastClickTime > 720)
                    clickListener?.onViewClick(v)
                lastClickTime = SystemClock.elapsedRealtime()
            }

        for (v in views)
            v.setOnClickListener(onViewClick)
    }


}