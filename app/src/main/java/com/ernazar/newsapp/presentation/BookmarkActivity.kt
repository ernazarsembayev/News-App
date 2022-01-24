package com.ernazar.newsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.databinding.ActivityBookmarkBinding
import com.ernazar.newsapp.domain.BookmarkViewModel
import com.ernazar.newsapp.presentation.adapter.ArticleAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkActivity : AppCompatActivity(), ArticleAdapter.ArticleListener {

    private val viewModel by viewModel<BookmarkViewModel>()
    private lateinit var binding: ActivityBookmarkBinding
    private lateinit var articleRecyclerView: RecyclerView
    private val articleAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ArticleAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articleRecyclerView = binding.recyclerView
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        articleRecyclerView.layoutManager = llm
        articleRecyclerView.adapter = articleAdapter

        viewModel.liveDataArticles.observe(this, {
            TODO("Not yet implemented")
        })

    }

    override fun onSelectArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override fun onClickBookmark(article: Article) {
        TODO("Not yet implemented")
    }

}