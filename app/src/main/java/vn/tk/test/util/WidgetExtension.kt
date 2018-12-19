package vn.tk.test.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

fun RecyclerView.addAdapter(
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null,
    orientation: Int = LinearLayoutManager.VERTICAL,
    reverseLayout: Boolean = false
) {

    this.layoutManager = LinearLayoutManager(context, orientation, reverseLayout)
    this.adapter = adapter
}
