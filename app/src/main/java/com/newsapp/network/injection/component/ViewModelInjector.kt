package com.newsapp.network.injection.component

import com.newsapp.dashboard.PlaceholderViewModel
import com.newsapp.network.injection.module.NetworkModule
import dagger.Component

/**
 * Component providing inject() methods for presenters.
 */
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(placeholderViewModel: PlaceholderViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}