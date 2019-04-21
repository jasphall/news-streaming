package pl.playground.news.api

import org.slf4j.LoggerFactory
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class NewsProvider(
        private val rSocketRequester: RSocketRequester
) {

    companion object {
        private val logger = LoggerFactory.getLogger(NewsProvider::class.java)
    }

    fun listen(): Flux<News> {
        return rSocketRequester
                .route("/news")
                .data(Mono.empty<Any>())
                .retrieveFlux(News::class.java)
                .doOnNext {
                    logger.info("Received message: $it")
                }
    }

}
