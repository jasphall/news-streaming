package pl.playground.news.streamer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewsStreamerApplication

fun main(args: Array<String>) {
    runApplication<NewsStreamerApplication>(*args)
}
