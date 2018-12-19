package vn.tk.test.architecture

import android.arch.lifecycle.ViewModel
import vn.com.elc.esupport.data.livedata.ProgressLiveData
import vn.com.elc.esupport.data.livedata.ToastLiveData

abstract class ArchitectureViewModel : ViewModel() {

    fun showProcess() {
        ProgressLiveData.show()
    }

    fun hideProcess() {
        ProgressLiveData.hide()
    }

    fun toast(string: String?) {
        ToastLiveData.message = string
    }

}
