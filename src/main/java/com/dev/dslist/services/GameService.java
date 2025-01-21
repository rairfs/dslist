package com.dev.dslist.services;

import com.dev.dslist.dto.GameMinDTO;
import com.dev.dslist.entities.Game;
import com.dev.dslist.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameMinDTO> findAll() {
        List<Game> result = this.gameRepository.findAll();
        return result.stream().map(GameMinDTO::new).toList();
    }
}
