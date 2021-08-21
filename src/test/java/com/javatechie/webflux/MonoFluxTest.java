package com.javatechie.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMonoSuccess() {
        Mono<String> monoString = Mono.just("javatechie").log();
        monoString.subscribe(System.out::println);
    }

    @Test
    public void testMonoFailed() {
        Mono<?> monoString = Mono.just("javatechie")
                .then(Mono.error(new RuntimeException("Exception occurred in Mono")))
                .log();
        monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFluxSuccess() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Hibernate", "microservice")
                .concatWithValues("AWS")
                .log();

        fluxString.subscribe(System.out::println);
    }

    @Test
    public void testFluxFailed() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Hibernate", "microservice")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occurred in Flux")))
                .concatWithValues("cloud")
                .log();

        fluxString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

}
