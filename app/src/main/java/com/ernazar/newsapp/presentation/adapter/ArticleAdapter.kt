package com.ernazar.newsapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.databinding.ArticleItemBinding

class ArticleAdapter(val listener: OnArticleSelect)
    : PagingDataAdapter<Article, ArticleAdapter.ViewHolder>(ArticleDiffItemCallback) {

    inner class ViewHolder(private val binding: ArticleItemBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: Article?) {
                binding.tvTitle.text = item?.title
                binding.tvDesc.text = item?.description
                binding.tvAuthor.text = item?.author
                binding.tvPublishedAt.text = item?.publishedAt
                binding.tvSource.text = item?.source?.name

                binding.ibBookmark.setOnClickListener {
                    // todo add to bookmark
                    Log.e("add to bookmark", it.toString())
                }

                Glide.with(binding.root)
                    .load(item?.urlToImage)
                    .centerCrop()
                    .into(binding.imageView)

        }

        override fun onClick(v: View?) {
            val position = layoutPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { listener.onSelect(it) }
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

    interface OnArticleSelect {
        fun onSelect(article: Article)
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