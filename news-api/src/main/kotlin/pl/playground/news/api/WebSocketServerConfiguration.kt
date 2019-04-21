package pl.playground.news.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter

@Configuration
class WebSocketServerConfiguration(
        private val newsHandler: NewsHandler
) {

    @Bean
    fun handlerMapping(): HandlerMapping {
        val handlerMapping = SimpleUrlHandlerMapping()

        handlerMapping.order = 1
        handlerMapping.urlMap = mapOf(
                "/news" to newsHandler
        )

        return handlerMapping
    }

    @Bean
    fun handlerAdapter() = WebSocketHandlerAdapter()

}
