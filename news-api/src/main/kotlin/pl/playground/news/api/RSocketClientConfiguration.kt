package pl.playground.news.api

import io.rsocket.RSocket
import io.rsocket.RSocketFactory
import io.rsocket.frame.decoder.PayloadDecoder
import io.rsocket.transport.netty.client.TcpClientTransport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.util.MimeTypeUtils

@Configuration
class RSocketClientConfiguration {

    @Bean
    fun reactiveSocket(): RSocket {
        return RSocketFactory
                .connect()
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .transport(TcpClientTransport.create(7001))
                .start()
                .block()!!
    }

    @Bean
    fun reactiveSocketRequester(rSocketStrategies: RSocketStrategies): RSocketRequester {
        return RSocketRequester.create(
                reactiveSocket(),
                MimeTypeUtils.APPLICATION_JSON,
                rSocketStrategies
        )
    }

}
