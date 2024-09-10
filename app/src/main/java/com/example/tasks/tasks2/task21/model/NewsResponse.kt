package com.example.tasks.tasks2.task21.model

data class NewsResponse (
    val status: String,
    val totalResults: Int,
    val articles: MutableList<ArticleDto>,
    )