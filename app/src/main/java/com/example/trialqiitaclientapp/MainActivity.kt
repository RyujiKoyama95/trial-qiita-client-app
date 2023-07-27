package com.example.trialqiitaclientapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trialqiitaclientapp.ui.theme.TrialQiitaClientAppTheme
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

    NavHost(navController = navController, startDestination = "search") {
        composable(
            route = "search",
            content = {
                SearchScreen(navController = navController, vm = vm)
            }
        )
        composable(
            route = "detail/{url}",
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