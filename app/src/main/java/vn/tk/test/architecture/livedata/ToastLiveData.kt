package vn.com.elc.esupport.data.livedata

class ToastLiveData : EventLiveData<String>() {

    companion object {

        val instance: ToastLiveData by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ToastLiveData()
        }

        var message: String? = null
            set(string) {
                if(!string.isNullOrEmpty()) instance.value = string
            }
    }

}