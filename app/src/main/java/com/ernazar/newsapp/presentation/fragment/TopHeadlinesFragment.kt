package com.ernazar.newsapp.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.databinding.FragmentTopHeadlinesBinding
import com.ernazar.newsapp.domain.TopHeadlinesViewModel
import com.ernazar.newsapp.presentation.DetailActivity
import com.ernazar.newsapp.presentation.adapter.ArticleAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopHeadlinesFragment : Fragment(), ArticleAdapter.ArticleListener {

    private lateinit var binding: FragmentTopHeadlinesBinding
    private val viewModel: TopHeadlinesViewModel by sharedViewModel()

    private lateinit var articleRecyclerView: RecyclerView
    private val articleAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ArticleAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopHeadlinesBinding.inflate(layoutInflater)

        articleRecyclerView = binding.recyclerView
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        articleRecyclerView.layoutManager = llm
        articleRecyclerView.adapter = articleAdapter

        viewModel.liveDataArticles.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                articleAdapter.submitData(it)
            }
        }

        viewModel.liveDataArticle.observe(viewLifecycleOwner, { article ->
            val detailActivityIntent = Intent(context, DetailActivity::class.java)
            detailActivityIntent.putExtra("article", article)
            startActivity(detailActivityIntent)
        })

        return binding.root
    }

    override fun onSelectArticle(article: Article) {
        viewModel.articleSelect(article)
    }

    override fun onClickBookmark(article: Article) {
        viewModel.articleBookmark(article)
    }

}