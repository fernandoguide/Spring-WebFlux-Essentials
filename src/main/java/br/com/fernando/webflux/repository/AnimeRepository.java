package br.com.fernando.webflux.repository;

import br.com.fernando.webflux.domain.Anime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AnimeRepository  extends ReactiveCrudRepository<Anime, Integer> {

    Mono<Anime> findById(int id);

}
