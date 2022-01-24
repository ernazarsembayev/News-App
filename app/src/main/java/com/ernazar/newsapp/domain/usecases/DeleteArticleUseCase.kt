package com.ernazar.newsapp.domain.usecases

import com.ernazar.newsapp.domain.repository.AppRepository

class DeleteArticleUseCase(private val appRepository: AppRepository) {

//    suspend fun execute(article: Article): Boolean {
//
//        val articleResult = article.url?.let { appRepository.getArticle(articleUrl = it) }
//
//        articleResult?.let {
//            appRepository.deleteArticle(article)
//            return false
//        }?:
//        appRepository.saveArticle(article)
//        return true
//
//    }

}