package com.newsapp.dashboard

import android.arch.lifecycle.ViewModel
import com.newsapp.network.injection.component.DaggerViewModelInjector
import com.newsapp.network.injection.component.ViewModelInjector
import com.newsapp.network.injection.module.NetworkModule

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PlaceholderViewModel -> injector.inject(this)
        }
    }
}