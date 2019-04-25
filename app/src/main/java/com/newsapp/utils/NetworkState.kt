package com.newsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import com.newsapp.utils.Constants.Companion.TYPE_MOBILE
import com.newsapp.utils.Constants.Companion.TYPE_NOT_CONNECTED
import com.newsapp.utils.Constants.Companion.TYPE_WIFI


object NetworkState {


    fun getConnectivityStatus(context: Context): Int {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (null != activeNetwork) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI

            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE
        }
        return TYPE_NOT_CONNECTED
    }

    fun getConnectivityStatusString(context: Context): String? {
        val conn = NetworkState.getConnectivityStatus(context)
        var status: String? = null
        if (conn == TYPE_WIFI) {
            status = "Wifi enabled"
        } else if (conn == TYPE_MOBILE) {
            status = "Mobile data enabled"
        } else if (conn == TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet"
        }
        return status
    }
}
