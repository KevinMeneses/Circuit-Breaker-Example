package com.meneses.circuitbreaker.service.v1

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Service
class CatFactService(
    private val webClientBuilder: WebClient.Builder,
    private val circuitBreakerFactory: ReactiveCircuitBreakerFactory<*, *>
) {
    suspend fun getCatFact(): Mono<String> {
        val url = "https://catfact.ninja/fact"
        val result = webClientBuilder
            .build()
            .get()
            .uri(url)
            .retrieve()
            .bodyToMono<String>()
            .transform { mono ->
                circuitBreakerFactory
                    .create("catFacts")
                    .run(mono) {
                        Mono.just(it.localizedMessage)
                    }
            }
        return result
    }
}