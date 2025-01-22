package com.dev.dslist.controllers;

import com.dev.dslist.dto.GameListDTO;
import com.dev.dslist.services.GameListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    private final GameListService gameListService;

    public GameListController(GameListService gameListService) {
        this.gameListService = gameListService;
    }

    @GetMapping(value = "/{id}")
    public GameListDTO findById(@PathVariable Long id) {
        return gameListService.findById(id);
    }

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }
}
