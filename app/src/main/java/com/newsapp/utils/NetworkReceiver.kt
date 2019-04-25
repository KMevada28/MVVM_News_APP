package com.newsapp.utils

import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context


class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        status = NetworkState.getConnectivityStatusString(context)
        Toast.makeText(context, status, Toast.LENGTH_LONG).show()
        if (status === "Wifi enabled") {
            //your code when wifi enable
        } else if (status === "Mobile data enabled") {
            //your code when TYPE_MOBILE network enable
        } else if (status === "Not connected to Internet") {
            //your code when no network connected
        }
    }

    companion object {
        internal var status: String? = null
    }
}