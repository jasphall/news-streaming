package pl.playground.news.streamer.api

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import pl.playground.news.streamer.domain.ChangeSpeed
import pl.playground.news.streamer.domain.News
import pl.playground.news.streamer.domain.NewsProvider
import reactor.core.publisher.Flux

@Controller
class NewsController(
        private val newsProvider: NewsProvider
) {

    @MessageMapping("/news")
    fun news(): Flux<News> {
        return newsProvider.listen()
    }

    @MessageMapping("/news/configure/speed")
    fun changeSpeed(changeSpeed: ChangeSpeed) {
        newsProvider.changeSpeed(changeSpeed)
    }

}
