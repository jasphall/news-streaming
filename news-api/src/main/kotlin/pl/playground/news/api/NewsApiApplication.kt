package pl.playground.news.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class NewsApiApplication

fun main(args: Array<String>) {
    runApplication<NewsApiApplication>(*args)
}

@Component
class NewsSubscriber(
        newsProvider: NewsProvider
) {

    init {
        newsProvider
                .listen()
                .subscribe { println(it) }
    }

}
