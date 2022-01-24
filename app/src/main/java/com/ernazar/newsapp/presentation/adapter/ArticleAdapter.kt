package com.ernazar.newsapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ernazar.newsapp.R
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.databinding.ArticleItemBinding
import com.ernazar.newsapp.utils.Utils

class ArticleAdapter(val articleListener: ArticleListener)
    : PagingDataAdapter<Article, ArticleAdapter.ViewHolder>(ArticleDiffItemCallback) {

    inner class ViewHolder(private val binding: ArticleItemBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(item: Article?) {
            binding.tvTitle.text = item?.title
            binding.tvDesc.text = item?.description
            binding.tvAuthor.text = item?.author
            binding.tvPublishedAt.text = item?.publishedAt?.let { Utils.getAbbreviatedFromDateTime(it) }
            binding.tvSource.text = item?.source?.name
            binding.ibBookmark.resources.getDrawable(
                R.drawable.ic_bookmark_empty, null )

            binding.ibBookmark.setOnClickListener {
                val position = layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    getItem(position)?.let { articleListener.onClickBookmark(it) }
                }
            }

            Glide.with(binding.root)
                .load(item?.urlToImage)
                .centerCrop()
                .into(binding.imageView)

        }

        override fun onClick(v: View?) {
            val position = layoutPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { articleListener.onSelectArticle(it) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleAdapter.ViewHolder {
        val binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface ArticleListener {
        fun onSelectArticle(article: Article)

        fun onClickBookmark(article: Article)
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title && oldItem.url == newItem.url
    }
}