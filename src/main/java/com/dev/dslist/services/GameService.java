package com.dev.dslist.services;

import com.dev.dslist.dto.GameDTO;
import com.dev.dslist.dto.GameMinDTO;
import com.dev.dslist.entities.Game;
import com.dev.dslist.projections.GameMinProjection;
import com.dev.dslist.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Optional<Game> game = this.gameRepository.findById(id);
        if (game.isPresent()) {
            return new GameDTO(game.get());
        }
        throw new RuntimeException("Game not found");
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> result = this.gameRepository.findAll();
        return result.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {
        List<GameMinProjection> result = this.gameRepository.searchByList(listId);
        return result.stream().map(GameMinDTO::new).toList();
    }

}
