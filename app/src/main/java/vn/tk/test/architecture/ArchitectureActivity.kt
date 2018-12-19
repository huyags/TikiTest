package vn.tk.test.architecture

import android.arch.lifecycle.*
import android.os.Bundle
import android.view.View
import com.huy.library.ui.BaseActivity
import vn.com.elc.esupport.data.livedata.ProgressLiveData
import vn.com.elc.esupport.data.livedata.ToastLiveData


abstract class ArchitectureActivity<VM : ArchitectureViewModel> : BaseActivity(), LifecycleOwner {

    protected lateinit var viewModel: VM

    private lateinit var lifecycleRegistry: LifecycleRegistry

    protected abstract fun viewModelClass(): Class<VM>

    protected abstract fun onViewConfig()

    protected abstract fun onRegisterLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleRegistry = LifecycleRegistry(this)

        lifecycleRegistry.markState(Lifecycle.State.CREATED)

        viewModel = ViewModelProviders.of(this).get(viewModelClass())

        onViewConfig()

        ProgressLiveData.instance.observe(this,Observer {
            if (it == true) showProgress()
            else hideProgress()
        })

        ToastLiveData.instance.observe(this,Observer {
            it ?: return@Observer
            toast(it)
        })

        onRegisterLiveData()
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()

        ProgressLiveData.instance.removeObservers(this)

        ToastLiveData.instance.removeObservers(this)

        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onViewClick(view: View) {

    }
}