package com.newsapp.utils

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.newsapp.R
import com.newsapp.dashboard.OnRetryClickListener
import kotlinx.android.synthetic.main.network_alert_layout.*
import android.os.Handler


class NetworkAlertFragment : DialogFragment() {

    lateinit var onRetryClickListener: OnRetryClickListener

    fun newInstance(onRetryClickListener: OnRetryClickListener) : NetworkAlertFragment{
        this.onRetryClickListener = onRetryClickListener
        return this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.network_alert_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnRetry.setOnClickListener(View.OnClickListener {
            this.dismiss()
            val handler = Handler()
            handler.postDelayed(Runnable {
                this.onRetryClickListener.onRetryClick()
            }, 5000)
        })
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window.setLayout(width, height)
        }
    }
}