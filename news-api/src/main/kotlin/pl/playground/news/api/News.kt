package pl.playground.news.api

import java.time.Instant

data class News(
        val id: Long,
        val breaking: Boolean,
        val postedAt: Instant,
        val title: String,
        val content: String
)
