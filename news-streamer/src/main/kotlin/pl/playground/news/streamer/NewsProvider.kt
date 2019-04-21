package pl.playground.news.streamer

import reactor.core.publisher.Flux

interface NewsProvider {

    fun listen(): Flux<News>
    fun changeSpeed(changeSpeed: ChangeSpeed)

}
