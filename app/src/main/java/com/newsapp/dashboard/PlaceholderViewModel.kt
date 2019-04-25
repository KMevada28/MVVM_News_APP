package com.newsapp.dashboard

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.newsapp.network.NewsApi
import com.newsapp.response.NewsResponseEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlaceholderViewModel : BaseViewModel() {

    @Inject
    lateinit var newsApi: NewsApi
    val postListAdapter: PostListAdapter = PostListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val errorMessage: MutableLiveData<Int> = MutableLiveData()
    private var newsRetrieveStart: MutableLiveData<Boolean> = MutableLiveData()

    private lateinit var subscription: Disposable


    fun getNews(country: String, category: String) {
        try {
            newsRetrieveStart.value = true
            subscription = newsApi.getTopNewResponse(country, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    newsRetrieveStart.value = false
                    onRetrieveNewsListStart()
                }
                .doOnTerminate { onRetrieveNewsListFinish() }
                .subscribe({ result ->
                    onRetrievePostListSuccess(result)
                }, {
                    onRetrieveNewsListError()
                }
                )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrieveNewsListStart() : MutableLiveData<Boolean>{
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
        return newsRetrieveStart
    }

    private fun onRetrieveNewsListFinish() {
        loadingVisibility.value = View.GONE
        errorMessage.value = null
    }

    private fun onRetrievePostListSuccess(newsResponseEntity: NewsResponseEntity) {
        if (postListAdapter != null && newsResponseEntity?.articles != null) {
            postListAdapter.updatePostList(newsResponseEntity.articles!!)
        }
    }

    private fun onRetrieveNewsListError() {

    }

}