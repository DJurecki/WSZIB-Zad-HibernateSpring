package com.jurecki.controllers;


import com.jurecki.model.Game;
import com.jurecki.repository.GamesRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author Dawid Jurecki on 15.01.2020
 **/

public class GamesControllerTest {

    GamesController controller;

    @Mock
    GamesRepository repository;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new GamesController(repository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void gamesPage() throws Exception{
        List<Game> testGames = new ArrayList<>();

        when(repository.getGameList()).thenReturn(testGames);

        this.mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(view().name("listGamesPage"))
                .andExpect(model().attribute("gamesList", testGames));
    }

    @Test
    public void loginForm() throws Exception{
        mockMvc.perform(post("/addGame"))
                .andExpect(status().isOk())
                .andExpect(view().name("addGamePage"));

        then(repository).should(times(1)).persistGame(any(Game.class));
    }

    @Test
    public void loginPage() throws Exception{
        mockMvc.perform(get("/addGame"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("gameKey"))
                .andExpect(view().name("addGamePage"));
    }

    /*@Test
    public void deleteGameForm() throws Exception {

        mockMvc.perform(post("/deleteGame"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("gameKey"))
                .andExpect(view().name("deleteGamePage"));
    }*/
}
