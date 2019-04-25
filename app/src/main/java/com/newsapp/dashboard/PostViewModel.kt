package com.newsapp.dashboard

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.newsapp.response.ArticleResponseEntity

class PostViewModel : BaseViewModel() {
    private val title = MutableLiveData<String>()
    private val imageUrl = MutableLiveData<String>()

    fun bind(post: ArticleResponseEntity) {
        if (post != null) {
            title.value = post.title?:"No Title Available"
            imageUrl.value = post.urlToImage?:"https://placebear.com/640/360"
        }
    }
    companion object {
        @BindingAdapter("urlToImage")
        @JvmStatic
        fun setUrlToImage(view: ImageView, url: String) {
            Glide.with(view.context).load(url).into(view)
        }
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getUrlToImage(): MutableLiveData<String> {
        return imageUrl
    }

}