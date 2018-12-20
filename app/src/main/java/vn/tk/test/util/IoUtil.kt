package vn.tk.test.util

import android.content.Context
import java.io.Closeable
import java.nio.charset.Charset

object IoUtil {

    fun safeClose(closeable: Closeable?) {
        if(closeable != null) {
            try {
                closeable.close()
            } catch(ignored: Exception) {
            }
        }
    }

    fun readAsset(context: Context, fileName: String): String {
        (context.assets).open(fileName).let {
            val buffer = ByteArray(it.available())
            it.read(buffer)
            it.close()
            return String(buffer, Charset.forName("UTF-8"))
        }
    }

}