package pl.playground.news.api

import io.rsocket.util.DefaultPayload
import org.slf4j.LoggerFactory
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

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
                .data(DefaultPayload.EMPTY_BUFFER)
                .retrieveFlux(News::class.java)
                .doOnNext {
                    logger.info("Received message: $it")
                }
    }

}
