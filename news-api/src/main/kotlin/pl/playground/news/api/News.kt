package pl.playground.news.api

data class News(
        val id: Long,
        val breaking: Boolean,
        val postedAt: String,
        val title: String,
        val content: String
)
