package com.example.trialqiitaclientapp.model

import com.squareup.moshi.Json

data class Article(
    val title: String,
    val url: String,
    val user: User
) {
    data class User(
        val id: String,
        @Json(name = "profile_image_url")
        val profileImageUrl: String
    )
}