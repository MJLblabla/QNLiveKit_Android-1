package com.qlive.uikitcore

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.qlive.jsonutil.JsonUtils
import java.io.InputStream

/**
 * 通过网页配置UI  正常接入无需关心
 * @constructor Create empty U i json configurator
 */
object UIJsonConfigurator {
    var enable = true
    private var uiJsonConfOption: UIJsonConfOption? = null
    const val key_beauty = "beauty"
    const val key_relay = "relay"
    const val key_mic = "mic"
    const val key_item = "item"
    const val key_gift = "gift"

    //弹幕
    const val key_bulletScreen = "bulletScreen"

    //预约
    const val key_booking = "booking"
    const val key_like = "like"
    const val key_announcement = "announcement"
    var uiJsonConfigNameMap = HashMap<String, String>()
    fun init(context: Context) {
        if (!enable) {
            return
        }
        readBundleFile(context)
    }

    fun checkEnable(view: View): Boolean {
        if (uiJsonConfOption == null) {
            return true
        }
        uiJsonConfOption!!.config?.forEach {
            if (it.flag == 0 && it.name == (uiJsonConfigNameMap[view.javaClass.canonicalName!!])) {
                view.visibility = View.GONE
                return false
            }
        }
        return true
    }

    fun checkEnable(name: String, view: View): Boolean {
        if (uiJsonConfOption == null) {
            return true
        }
        uiJsonConfOption!!.config?.forEach {
            if (it.flag == 0 && it.name == name) {
                (view.parent as ViewGroup?)?.removeView(view)
                return false
            }
        }
        return true
    }

    private fun readBundleFile(context: Context) {
        var buffer: ByteArray? = null
        try {
            val mAssets: InputStream = context.assets.open("config.conf")
            val lenght: Int = mAssets.available()
            buffer = ByteArray(lenght)
            mAssets.read(buffer)
            mAssets.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        buffer ?: return
        val jsonStr = String(buffer)
        uiJsonConfOption = JsonUtils.parseObject(jsonStr, UIJsonConfOption::class.java)
    }
}
