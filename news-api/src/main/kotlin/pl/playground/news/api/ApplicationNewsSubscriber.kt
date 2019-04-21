package pl.playground.news.api

import org.springframework.stereotype.Component

@Component
class ApplicationNewsSubscriber(
        newsProvider: NewsProvider
) {

    init {
        newsProvider
                .listen()
                .subscribe()
    }

}
