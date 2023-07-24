package com.example.trialqiitaclientapp.repository

import com.example.trialqiitaclientapp.model.Article
import com.example.trialqiitaclientapp.network.QiitaApi

class ArticleRepository {
    suspend fun searchArticles(query: String): List<Article>? =
        QiitaApi.retrofitService.searchArticle(query).body()
}