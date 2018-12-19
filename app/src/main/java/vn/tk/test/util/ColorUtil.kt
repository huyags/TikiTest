package vn.tk.test.util

import android.graphics.Color
import java.util.*

object ColorUtil {

    private val random = Random()

    fun getRandomColor(): Int {
        return Color.rgb(
            random.nextInt(255),
            random.nextInt(255),
            random.nextInt(255)
        )
    }
}