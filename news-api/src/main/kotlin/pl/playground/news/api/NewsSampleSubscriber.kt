package pl.playground.news.api

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class NewsSampleSubscriber(
        newsProvider: NewsProvider
) {

    companion object {
        private val logger = LoggerFactory.getLogger(NewsSampleSubscriber::class.java)
    }

    init {
        newsProvider
                .listen()
                .subscribe { logger.info("News: $it") }
    }

}
