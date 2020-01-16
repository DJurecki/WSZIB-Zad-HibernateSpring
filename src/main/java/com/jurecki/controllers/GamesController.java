package com.jurecki.controllers;

import com.jurecki.model.Game;
import com.jurecki.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author Dawid Jurecki on 02.12.2019
 **/

@Controller
public class GamesController {

    GamesRepository gamesRepository;

    @Autowired
    public GamesController(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public String gamesPage(Model model){
        model.addAttribute("gamesList", this.gamesRepository.getGameList());
        return "listGamesPage";
    }

    @RequestMapping(value = "/updateGame", method = RequestMethod.GET)
    public ModelAndView updateGamePage(Model model){
        model.addAttribute("gamesList", this.gamesRepository.getGameList());
        return new ModelAndView("updateGamePage", "gameKey", new Game());
    }

    @RequestMapping(value = "/updateGame", method = RequestMethod.POST)
    public ModelAndView updateGameForm(@ModelAttribute("gameKey") Game game, Model model){
        gamesRepository.updateGame(game);
        model.addAttribute("gamesList", this.gamesRepository.getGameList());
        return new ModelAndView("updateGamePage", "gameKey", new Game());
    }

    @RequestMapping(value = "/deleteGame", method = RequestMethod.GET)
    public ModelAndView deleteGamePage(Model model){
        model.addAttribute("gamesList", this.gamesRepository.getGameList());
        return new ModelAndView("deleteGamePage", "gameKey", new Game());
    }

    @RequestMapping(value = "/deleteGame", method = RequestMethod.POST)
    public ModelAndView deleteGameForm(@ModelAttribute("gameKey") Game game , Model model){
        gamesRepository.deleteGame(game.getId());
        model.addAttribute("gamesList", this.gamesRepository.getGameList());
        return new ModelAndView("deleteGamePage", "gameKey", new Game());
    }

    @RequestMapping(value = "/addGame", method = RequestMethod.GET)
    public ModelAndView loginPage(){
        return new ModelAndView("addGamePage", "gameKey", new Game());
    }

    @RequestMapping(value = "/addGame", method = RequestMethod.POST)
    public String loginForm(@ModelAttribute("gameKey") Game game){
            gamesRepository.persistGame(game);
            return "addGamePage";
    }
}
