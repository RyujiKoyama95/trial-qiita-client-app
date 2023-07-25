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
import com.example.trialqiitaclientapp.view.component.SearchBar
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController, vm: SearchViewModel) {
    Scaffold {
        Column {
            val textFieldState = remember { mutableStateOf(TextFieldValue("")) }
            SearchBar(textFieldState = textFieldState) {
                // Todo: itで入力されたテキストにアクセスできる理由がよくわからん
                vm.searchArticle(it)
            }

            val observerArticles = vm.articles.observeAsState()
            observerArticles.value?.let { articles ->
                LazyColumn {
                    items(articles) {article ->
                        SearchResultCell(article = article) {
                            val encoderUrl =
                                URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                            navController.navigate("detail/$encoderUrl")
                        }
                    }
                }
            }
        }
    }
}