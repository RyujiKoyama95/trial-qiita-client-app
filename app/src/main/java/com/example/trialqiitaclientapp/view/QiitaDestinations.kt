package com.example.trialqiitaclientapp.view

import androidx.compose.runtime.Composable
import com.example.trialqiitaclientapp.view.detail.DetailScreen
import com.example.trialqiitaclientapp.view.search.SearchScreen

interface QiitaDestinations {
    val root: String
}

object Search: QiitaDestinations {
    override val root = "search"
}

object Detail: QiitaDestinations {
    override val root = "detail/{url}"
}

