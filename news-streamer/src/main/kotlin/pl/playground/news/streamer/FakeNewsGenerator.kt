package pl.playground.news.streamer

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.EmitterProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class FakeNewsGenerator: NewsProvider {

    companion object {
        private val logger = LoggerFactory.getLogger(FakeNewsGenerator::class.java)
    }

    private val generator = Random()
    private var newsSource = EmitterProcessor.create<News>()
    private var sourceInterval: Long = 1000

    init {
        initNewsSource()
    }

    override fun listen(): Flux<News> {
        return newsSource
                .toFlux()
                .onBackpressureDrop {
                    logger.info("Message dropped via backpressure: $it")
                }
    }

    private fun initNewsSource() {
        Flux
                .interval(Duration.ofMillis(sourceInterval))
                .subscribe {
                    newsSource.onNext(fakeNews(it))
                }
    }

    private fun fakeNews(id: Long): News {
        val breaking = generator.nextInt(10) > 8
        return if (breaking) {
            News(id, breaking, Instant.now(),
                    "Article $id: Super Important News",
                    "The world is on fire! The World is on Fire!")
        } else {
            News(id, breaking, Instant.now(),
                    "Article $id: Fake News",
                    "Some boring news article")
        }
    }

}
