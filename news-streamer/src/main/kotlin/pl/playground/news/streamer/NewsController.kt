package pl.playground.news.streamer

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@Controller
class NewsController(
        private val newsProvider: NewsProvider
) {

    @MessageMapping("/news")
    fun news(): Flux<News> {
        return newsProvider.listen()
    }

}
