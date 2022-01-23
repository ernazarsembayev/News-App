package com.ernazar.newsapp.data.network.model.articlesResponse

data class ArticlesResponseDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)