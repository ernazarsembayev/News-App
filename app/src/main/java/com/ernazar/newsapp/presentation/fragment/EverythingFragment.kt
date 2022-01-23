package com.ernazar.newsapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.databinding.FragmentEverythingBinding
import com.ernazar.newsapp.domain.EverythingViewModel
import com.ernazar.newsapp.presentation.adapter.ArticleAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EverythingFragment : Fragment(), ArticleAdapter.OnArticleSelect {

    private val viewModel: EverythingViewModel by sharedViewModel()

    private lateinit var binding: FragmentEverythingBinding
    private lateinit var articleRecyclerView: RecyclerView
    private val articleAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ArticleAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEverythingBinding.inflate(layoutInflater)

        articleRecyclerView = binding.recyclerView
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        articleRecyclerView.layoutManager = llm
        articleRecyclerView.adapter = articleAdapter

        viewModel.liveDataArticles.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                articleAdapter.submitData(it)
            }
        })

        return binding.root
    }

    override fun onSelect(article: Article) {
        viewModel.articleSelect(article)
    }
}