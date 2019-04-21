package pl.playground.news.streamer

data class News(
        val id: Long,
        val breaking: Boolean,
        val postedAt: String,
        val title: String,
        val content: String
)
