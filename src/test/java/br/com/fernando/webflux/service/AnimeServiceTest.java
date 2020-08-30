package br.com.fernando.webflux.service;

import br.com.fernando.webflux.domain.Anime;
import br.com.fernando.webflux.repository.AnimeRepository;
import br.com.fernando.webflux.util.AnimeCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;
    @Mock
    private AnimeRepository animeRepository;

    private  final Anime anime = AnimeCreator.createAnimeToBeSaved();

    @BeforeAll
    public static  void blockHoundSetuo(){
        BlockHound.install();
    }

    @Test
    public void blockHoundWorks(){
        try {
            FutureTask<?> task = new FutureTask<>(() ->{
                Thread.sleep(0);
                return "";
            });
            Schedulers.parallel().schedule(task);
            task.get(10, TimeUnit.SECONDS);
            Assertions.fail("Should fail");

        }catch (Exception e) {
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
        }
    }



}