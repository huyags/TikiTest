package vn.tk.test.ui

import com.huy.kotlin.network.ApiHelper
import vn.com.elc.esupport.data.livedata.EventLiveData
import vn.tk.test.architecture.ArchitectureViewModel
import vn.tk.test.architecture.livedata.ApiCallback

class MainViewModel : ArchitectureViewModel() {

    fun getKeywordLiveData(): EventLiveData<List<String>?> {

        val response = ApiCallback<List<String>?>()
        ApiHelper.instance.getKeywords(response)
        return response.liveData
    }
}