package pl.playground.news.streamer.domain

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.EmitterProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

@Component
class FakeNewsGenerator: NewsProvider {

    companion object {
        private val logger = LoggerFactory.getLogger(FakeNewsGenerator::class.java)
    }

    private val generator = Random()
    private var newsIntervalInMillis: Long = 1000
    private var newsSource = EmitterProcessor.create<News>()
    private var tickerSource = BehaviorSubject.createDefault<Long>(newsIntervalInMillis)
    private var currentId = AtomicLong()

    init {
        initNewsSource()
    }

    override fun listen(): Flux<News> {
        return newsSource
                .toFlux()
                .doOnNext { logger.info("Generated message: $it") }
                .onBackpressureDrop { logger.info("Message dropped via backpressure: $it") }
    }

    override fun changeSpeed(changeSpeed: ChangeSpeed) {
        newsIntervalInMillis = changeSpeed.speedInMillis.toLong()
        logger.info("Changed source speed to $newsIntervalInMillis ms")
    }

    private fun initNewsSource() {
        tickerSource
                .switchMap { Observable.interval(newsIntervalInMillis, TimeUnit.MILLISECONDS) }
                .subscribe {
                    newsSource.onNext(fakeNews(currentId.getAndIncrement()))
                    tickerSource.onNext(newsIntervalInMillis)
                }
    }

    private fun fakeNews(id: Long): News {
        val breaking = generator.nextInt(10) > 8
        return if (breaking) {
            News(id, breaking, Instant.now().toString(),
                    "Article $id: Super Important News",
                    "The world is on fire! The World is on Fire!")
        } else {
            News(id, breaking, Instant.now().toString(),
                    "Article $id: Fake News",
                    "Some boring news article")
        }
    }

}
