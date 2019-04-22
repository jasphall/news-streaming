package pl.playground.news.api.api.rest

import org.slf4j.LoggerFactory
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.playground.news.api.ChangeSpeed
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/news/configuration")
class NewsStreamConfigurationController(
        private val rSocketRequester: RSocketRequester
) {

    companion object {
        private val logger = LoggerFactory.getLogger(NewsStreamConfigurationController::class.java)
    }

    @GetMapping("/speed/{speed}")
    fun changeSpeed(@PathVariable speed: Int): Mono<Void> {
        logger.info("Requested for news source speed change. New speed: $speed")

        return rSocketRequester
                .route("/news/configure/speed")
                .data(ChangeSpeed(speed))
                .send()
    }

}
