package br.com.fernando.webflux.repository;

import br.com.fernando.webflux.domain.Anime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AnimeRepository  extends ReactiveCrudRepository<Anime, Integer> {

}
