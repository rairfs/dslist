package com.dev.dslist.services;

import com.dev.dslist.dto.GameDTO;
import com.dev.dslist.dto.GameListDTO;
import com.dev.dslist.dto.GameMinDTO;
import com.dev.dslist.entities.Game;
import com.dev.dslist.entities.GameList;
import com.dev.dslist.repositories.GameListRepository;
import com.dev.dslist.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameListService {

    private final GameListRepository gameListRepository;

    public GameListService(GameListRepository gameListRepository) {
        this.gameListRepository = gameListRepository;
    }

    @Transactional(readOnly = true)
    public GameListDTO findById(Long id) {
        Optional<GameList> list = this.gameListRepository.findById(id);
        if (list.isPresent()) {
            return new GameListDTO(list.get());
        }
        throw new RuntimeException("Game not found");
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = this.gameListRepository.findAll();
        return result.stream().map(GameListDTO::new).toList();
    }

}
