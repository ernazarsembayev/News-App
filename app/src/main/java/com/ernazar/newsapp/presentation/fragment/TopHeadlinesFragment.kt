package com.ernazar.newsapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.data.network.model.articlesResponse.ArticleDto
import com.ernazar.newsapp.databinding.FragmentTopHeadlinesBinding
import com.ernazar.newsapp.domain.TopHeadlinesViewModel
import com.ernazar.newsapp.presentation.adapter.ArticleAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopHeadlinesFragment : Fragment(), ArticleAdapter.OnArticleSelect {

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

//        lifecycleScope.launch {
//            viewModel.news.collectLatest { pagingData ->
//                articleAdapter.submitData(pagingData)
//            }
//        }

        viewModel.liveDataArticles.observe(viewLifecycleOwner) {
            Log.e("paging data observe", it.toString())
            lifecycleScope.launch {
                articleAdapter.submitData(it)
            }
        }

        return binding.root
    }

    override fun onSelect(article: Article) {
        viewModel.articleSelect(article)
    }

}