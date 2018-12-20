package vn.tk.test.ui

import com.huy.kotlin.network.ApiHelper
import vn.com.elc.esupport.data.livedata.EventLiveData
import vn.tk.test.app.ArchitectureViewModel
import vn.tk.test.network.ApiCallback

class MainViewModel : ArchitectureViewModel() {

    fun getKeywordLiveData(): EventLiveData<List<String>?> {

        val response = ApiCallback<List<String>?>("keyword")
        ApiHelper.service.getKeywords().enqueue(response)
        return response.liveData
    }
}