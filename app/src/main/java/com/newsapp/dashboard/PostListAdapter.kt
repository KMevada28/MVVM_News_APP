package com.newsapp.dashboard

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.newsapp.R
import com.newsapp.databinding.PostItemBinding
import com.newsapp.response.ArticleResponseEntity
import kotlinx.android.synthetic.main.post_item.view.*


open class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private lateinit var postList: List<ArticleResponseEntity>
    private lateinit var context: Context
    var onItemClick: ((ArticleResponseEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.ViewHolder {

        val binding: com.newsapp.databinding.PostItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_item, parent, false
        )
        this.context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostListAdapter.ViewHolder, position: Int) {
        holder.bind(postList[position])

    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList: List<ArticleResponseEntity>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()
        val cardView: CardView = itemView.card_view

        fun bind(post: ArticleResponseEntity) {
            viewModel.bind(post)
            binding.viewModel = viewModel
        }

        init {
            cardView.setOnClickListener {
                onItemClick?.invoke(postList[adapterPosition])
            }
        }
    }
}
