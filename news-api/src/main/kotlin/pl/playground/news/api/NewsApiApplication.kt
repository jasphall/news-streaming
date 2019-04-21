package pl.playground.news.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewsApiApplication

fun main(args: Array<String>) {
    runApplication<NewsApiApplication>(*args)
}
