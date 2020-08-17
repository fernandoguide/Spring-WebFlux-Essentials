package br.com.fernando.webflux.resources;

import br.com.fernando.webflux.domain.Anime;
import br.com.fernando.webflux.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeResource {

    private final AnimeRepository animeRepository;

    @GetMapping
    public Flux<Anime> listAll(){
        return animeRepository.findAll();
    }


}
