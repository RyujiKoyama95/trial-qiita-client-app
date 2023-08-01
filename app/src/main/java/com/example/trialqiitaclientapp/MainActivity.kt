package com.example.trialqiitaclientapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trialqiitaclientapp.ui.theme.TrialQiitaClientAppTheme
import com.example.trialqiitaclientapp.view.Detail
import com.example.trialqiitaclientapp.view.Search
import com.example.trialqiitaclientapp.view.detail.DetailScreen
import com.example.trialqiitaclientapp.view.search.SearchScreen
import com.example.trialqiitaclientapp.view.search.SearchViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrialQiitaClientAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavHost()
                }
            }
        }
    }
}

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val vm = viewModel<SearchViewModel>()
    val observerArticles = vm.articles.observeAsState()

    NavHost(navController = navController, startDestination = "search") {
        composable(
            route = Search.root,
            content = {
                SearchScreen(
                    articles = observerArticles.value ?: listOf(),
                    onClickArticle = { url -> navController.navigate(url) },
                    onClickSearch = { query -> vm.searchArticle(query) }
                )
            }
        )
        composable(
            route = Detail.root,
            content = { navBackStackEntry ->
                navBackStackEntry.arguments?.getString("url")?.let { url ->
                    DetailScreen(url = url)
                } ?: run {
                    DetailScreen(url = "https://qiita.com/")
                }
            }
        )
    }
}