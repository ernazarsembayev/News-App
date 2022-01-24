package com.ernazar.newsapp.data.network.model

import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.data.model.Source
import com.ernazar.newsapp.data.network.model.articlesResponse.SourceDto

internal fun com.ernazar.newsapp.data.network.model.articlesResponse.ArticleDto.toArticle(): Article {
    return Article(
        source = this.source?.toSource(),
        title = title,
        url = url,
        description = description,
        author = author,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        isMarked = false
    )
}

private fun SourceDto.toSource(): Source {
    return Source(id = id, name = name)
}