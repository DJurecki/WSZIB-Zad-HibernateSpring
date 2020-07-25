package com.jurecki.repository;

import com.jurecki.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Author Dawid Jurecki on 15.01.2020
 **/
@ExtendWith(MockitoExtension.class)
public class GamesRepositoryTest {

    @Mock
    GamesRepository gamesRepository;

    Game game;

    @BeforeEach
    public void setUp(){
        game = new Game();
    }

    @Test
    void persistGame(){
            gamesRepository.persistGame(game);
        verify(gamesRepository, times(1)).persistGame(any(Game.class));
    }

    @Test
    void deleteGame(){
        gamesRepository.deleteGame(game.getId());
        gamesRepository.deleteGame(game.getId());
        then(gamesRepository).should(times(2)).deleteGame(any(Integer.class));
    }

    @Test
    public void getGameById() {
        given(gamesRepository.getGameById(anyInt())).willReturn(game);

        Game gameBack = gamesRepository.getGameById(anyInt());

        assertNotNull(gameBack, "Null game");
        then(gamesRepository).should(times(1)).getGameById(anyInt());
        then(gamesRepository).shouldHaveNoMoreInteractions();

    }
}
