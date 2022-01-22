package com.ernazar.newsapp.data.server.response.getArticlesResponse

data class GetArticlesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)