package br.com.fernando.webflux.util;

import br.com.fernando.webflux.domain.Anime;

public class AnimeCreator {

    public  static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Inuyasha")
                .build();
    }

    public  static Anime createValidAnime(){
        return Anime.builder()
                .id(1)
                .name("Inuyasha")
                .build();
    }
    public  static Anime createValidUpdateAnime(){
        return Anime.builder()
                .id(1)
                .name("Inuyasha 2")
                .build();
    }
}
