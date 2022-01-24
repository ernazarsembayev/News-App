package com.ernazar.newsapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.databinding.ActivityDetailBinding
import com.ernazar.newsapp.utils.Utils
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class DetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var binding: ActivityDetailBinding

    private var isHideToolbarView = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article: Article = intent.getParcelableExtra("article")!!

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val collapsingToolbarLayout = binding.collapsingToolbar
        collapsingToolbarLayout.title = ""

        val appBarLayout = binding.appbar
        appBarLayout.addOnOffsetChangedListener(this)


        Glide.with(binding.root)
            .load(article.urlToImage)
            .centerCrop()
            .into(binding.imageView)

        binding.titleOnAppbar.text = article.title
        binding.title.text = article.title
        binding.time.text = article.publishedAt?.let { " \u2022 " + Utils.getAbbreviatedFromDateTime(it) }
        binding.subtitleOnAppbar.text = article.url
        binding.date.text = article.publishedAt?.let { Utils.getAbbreviatedFromDateTime(it) }

        article.url?.let { initWedView(it) }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWedView(url: String) {
        val webView = binding.webView
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
        super.onBackPressed()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {

        val maxScroll = appBarLayout?.totalScrollRange ?: 1
        val percentage = abs(verticalOffset).toFloat() / maxScroll.toFloat()

        if (percentage == 1f && isHideToolbarView) {
            binding.dateBehavior.visibility = View.GONE
            binding.titleAppbar.visibility = View.INVISIBLE
            isHideToolbarView = !isHideToolbarView
        } else if (percentage < 1F && !isHideToolbarView) {
            binding.dateBehavior.visibility = View.VISIBLE
            binding.titleAppbar.visibility = View.GONE
            isHideToolbarView = !isHideToolbarView
        }
    }
}