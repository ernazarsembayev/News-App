package com.ernazar.newsapp.domain.usecases

import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.domain.repository.AppRepository

class BookmarkUseCase(private val appRepository: AppRepository) {

//    suspend fun execute(article: Article): Article {
//
//        val articleResult = appRepository.getArticle(articleUrl = article.url)
//
//        articleResult?.let {
//            appRepository.deleteArticle(article)
//            it.isMarked = false
//            return it
//        }?:
//        appRepository.saveArticle(article)
//        article.isMarked = true
//        return article
//
//
//    }

}