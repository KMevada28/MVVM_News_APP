package com.newsapp.utils

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentManager
import com.newsapp.MainActivity
import com.newsapp.R
import com.newsapp.dashboard.OnRetryClickListener

class Constants {
    companion object {
        const val EMPTY_STRING = ""
        const val TYPE_NOT_CONNECTED = 0
        const val TYPE_WIFI = 1
        const val TYPE_MOBILE = 2
        const val BASE_URL: String = "https://newsapi.org/"
        const val KEY_VAL: String = "18a71458f745406fb38647ef92a17b77"
        const val COUNTRY: String = "us"
        const val ARG_ARTICLE_DATA = "ARTICLE_DATA"

        fun showNetworkErrorScreen(fragmentManager: FragmentManager, onRetryClickListener: OnRetryClickListener) {
            val ft = fragmentManager.beginTransaction()
            val prev = fragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            val dialogFragment = NetworkAlertFragment().newInstance(onRetryClickListener)
            dialogFragment.isCancelable = false
            dialogFragment.show(ft, "dialog")
        }

        fun shareUrl(url: String?, context: Context) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.app_name));
            context.startActivity(Intent.createChooser(shareIntent, url))
        }

        fun navigateToActivity(context: Context, className : Class<MainActivity>) {
            val intent = Intent(context, className)
            context.startActivity(intent)
        }
    }

}