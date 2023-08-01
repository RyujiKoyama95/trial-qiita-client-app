package com.example.trialqiitaclientapp.view.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.trialqiitaclientapp.model.Article
import com.example.trialqiitaclientapp.view.component.SearchBar
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    articles: List<Article>,
    onClickArticle: (String) -> Unit,
    onClickSearch: (String) -> Unit
) {
    Scaffold {
        Column {
            val textFieldState = remember { mutableStateOf(TextFieldValue("")) }
            SearchBar(
                textFieldState = textFieldState,
                onSubmit = { text ->
                    onClickSearch(text)
                }
            )

            LazyColumn {
                items(articles) {article ->
                    val encoderUrl =
                        URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                    val url = "detail/$encoderUrl"
                    SearchResultCell(
                        article = article,
                        onClickCell = { onClickArticle(url) }
                    )
                }
            }
        }
    }
}