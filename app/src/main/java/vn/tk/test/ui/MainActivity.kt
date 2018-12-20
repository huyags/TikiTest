package vn.tk.test.ui

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_keyword.view.*
import vn.tk.test.R
import vn.tk.test.app.ArchitectureActivity
import vn.tk.test.util.ColorUtil
import vn.tk.test.util.addAdapter
import vn.tk.test.util.base.BaseAdapterInt
import vn.tk.test.util.base.BaseRecyclerAdapter

class MainActivity : ArchitectureActivity<MainViewModel>() {

    class KeywordAdapter : BaseRecyclerAdapter<String>() {

        override fun defaultLayoutResource() = R.layout.item_keyword

        override fun View.onBindModel(model: String, position: Int, layout: Int) {
            itemKeyword_textView_label.text = model
            itemKeyword_textView_label.setBackgroundColor(ColorUtil.getRandomColor())
        }
    }

    private val adapter = KeywordAdapter()

    override fun layoutResource() = R.layout.activity_main

    override fun viewModelClass() = MainViewModel::class.java

    override fun onViewConfig() {

        mainRecyclerView.addAdapter(adapter, LinearLayoutManager.HORIZONTAL)
        adapter.itemClickListener = object : BaseAdapterInt.ItemClickListener<String> {
            override fun onItemClick(model: String, position: Int) {
                toast(model)
            }
        }
    }

    override fun onRegisterLiveData() {

        viewModel.getKeywordLiveData().observe(this, Observer {
            adapter.set(it)
        })

    }


}
