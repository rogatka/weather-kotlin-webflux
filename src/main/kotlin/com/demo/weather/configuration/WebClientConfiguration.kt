package com.demo.weather.configuration

import com.demo.weather.configuration.properties.WeatherApiProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.function.Consumer


@Configuration
class WebClientConfiguration (val log: Logger = LoggerFactory.getLogger(WebClientConfiguration::class.java)) {

    @Bean
    fun webClient(weatherApiProperties: WeatherApiProperties, builder: WebClient.Builder): WebClient {
        return builder
            .baseUrl(weatherApiProperties.baseUrl)
            .filter(logRequest())
            .filter(logResponse())
            .build()
    }

    private fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest: ClientRequest ->
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url())
            clientRequest.headers()
                .forEach { name: String?, values: List<String?> ->
                    values.forEach(
                        Consumer<String?> { value: String? ->
                            log.info(
                                "{}={}",
                                name,
                                value
                            )
                        })
                }
            Mono.just<ClientRequest>(clientRequest)
        }
    }

    private fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse: ClientResponse ->
            log.info("Response status: {}", clientResponse.statusCode())
            clientResponse.headers().asHttpHeaders()
                .forEach { name: String?, values: List<String?> ->
                    values.forEach(
                        Consumer { value: String? ->
                            log.info(
                                "{}={}",
                                name,
                                value
                            )
                        })
                }
            Mono.just(clientResponse)
        }
    }
}