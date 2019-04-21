package pl.playground.news.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class NewsHandler(
        private val newsProvider: NewsProvider,
        private val objectMapper: ObjectMapper
): WebSocketHandler {

    override fun handle(session: WebSocketSession): Mono<Void> {
        val publisher = newsProvider
                .listen()
                .map { objectMapper.writeValueAsString(it) }
                .map { session.textMessage(it) }

        return session.send(publisher)
    }

}
