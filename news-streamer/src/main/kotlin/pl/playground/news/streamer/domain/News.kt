package pl.playground.news.streamer.domain

data class News(
        val id: Long,
        val breaking: Boolean,
        val postedAt: String,
        val title: String,
        val content: String
)
