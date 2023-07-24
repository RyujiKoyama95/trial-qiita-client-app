package com.example.trialqiitaclientapp.network

import com.example.trialqiitaclientapp.model.Article
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://qiita.com/"

private val retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .client(OkHttpClient())
    .addConverterFactory(
        MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        )
    )
    .build()

interface QiitaApiService {
    @GET("api/v2/items?page=1&per_page=20")
    suspend fun searchArticle(@Query("query") query: String): Response<List<Article>>
}

object QiitaApi {
    val retrofitService: QiitaApiService by lazy {
        retrofit.create(QiitaApiService::class.java)
    }
}