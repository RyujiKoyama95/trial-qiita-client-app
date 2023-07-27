package com.example.trialqiitaclientapp.view.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailScreen(url: String) {
    AndroidView(
        factory = ::WebView,
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
        }
    )
}