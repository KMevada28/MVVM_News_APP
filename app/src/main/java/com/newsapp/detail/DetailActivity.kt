package com.newsapp.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.newsapp.R
import com.newsapp.response.ArticleResponseEntity
import com.newsapp.utils.Constants.Companion.ARG_ARTICLE_DATA
import com.newsapp.utils.Constants.Companion.EMPTY_STRING
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import android.text.TextUtils
import android.widget.TextView
import com.newsapp.utils.Constants.Companion.shareUrl


class DetailActivity : AppCompatActivity() {

    private var articleResponseEntity: ArticleResponseEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        toolbar.title = EMPTY_STRING
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        intent.extras[ARG_ARTICLE_DATA]?.let {
            articleResponseEntity = intent.extras[ARG_ARTICLE_DATA] as ArticleResponseEntity?
            setMarqueeEffect(articleResponseEntity?.title)
        }
        setData(articleResponseEntity)
    }

    private fun setData(articleResponseEntity: ArticleResponseEntity?) {
        tvTitle.setText(articleResponseEntity?.title)
        tvContent.setText(articleResponseEntity?.content)
        Glide.with(this)
            .load(articleResponseEntity?.urlToImage)
            .into(imageView)
        tvAuthor.setText("Author:\n" + articleResponseEntity?.author)
        tvPublishDate.setText("Published At:\n" + articleResponseEntity?.publishedAt)
        val url : String? = articleResponseEntity?.url
        textlink.setOnClickListener(View.OnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        })
        floatingActionButton.setOnClickListener(View.OnClickListener {
            shareUrl(url,this)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setMarqueeEffect(title : String?){
        var titleTextView: TextView? = null

        try {
            val f = toolbar::class.java.getDeclaredField("mTitleTextView")
            f.isAccessible = true
            titleTextView = f.get(toolbar) as TextView?
            titleTextView!!.ellipsize = TextUtils.TruncateAt.MARQUEE
            titleTextView.isFocusable = true
            titleTextView.isFocusableInTouchMode = true
            titleTextView.requestFocus()
            titleTextView.setSingleLine(true)
            titleTextView.isSelected = true
            titleTextView.marqueeRepeatLimit = -1
            titleTextView.text = title
            toolbar.title = titleTextView.text
        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalAccessException) {
        }

    }

}
