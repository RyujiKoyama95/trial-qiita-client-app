package com.example.trialqiitaclientapp.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trialqiitaclientapp.model.Article
import com.example.trialqiitaclientapp.repository.ArticleRepository
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val repo = ArticleRepository()
    private var _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    fun searchArticle(query: String) {
        viewModelScope.launch {
            val result = repo.searchArticles(query)
            _articles.postValue(result)
        }
    }
}