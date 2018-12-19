package vn.com.elc.esupport.data.livedata


class ProgressLiveData : EventLiveData<Boolean>() {

    @Volatile
    var animated = false

    companion object {

        val instance: ProgressLiveData by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ProgressLiveData()
        }

        fun show() {
            if(!instance.animated) {
                instance.animated = true
                instance.value = true
            }
        }

        fun hide() {
            if(instance.animated) {
                instance.animated = false
                instance.value = false
            }
        }

    }

}