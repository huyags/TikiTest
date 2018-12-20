package vn.tk.test.util

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import java.io.StringReader

object JsonUtil {

    private val gson = Gson()

    fun <T> parse(string: String?, cls: Class<T>): T? {

        if(string.isNullOrEmpty()) {
            return null
        }
        return try {
            return gson.fromJson(string, cls)
        } catch(e: IllegalStateException) {
            null
        } catch(e: JsonSyntaxException) {
            null
        } catch(e: IllegalArgumentException) {
            null
        }
    }

    fun <T> parse(json: JsonObject?, cls: Class<T>): T? {
        json ?: return null
        return parse(json.toString(), cls)
    }

    fun <T> parse(string: String?, cls: Class<Array<T>>): List<T>? {

        if(string.isNullOrEmpty()) {
            return null
        }
        return try {
            return gson.fromJson(StringReader(string), cls).toList()
        } catch(e: IllegalStateException) {
            null
        } catch(e: JsonSyntaxException) {
            null
        } catch(e: IllegalArgumentException) {
            null
        }
    }

    fun <T> parse(json: JsonArray?, cls: Class<Array<T>>): List<T>? {
        json ?: return null
        return parse(json.toString(), cls)
    }

}