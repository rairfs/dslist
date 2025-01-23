package com.dev.dslist.services;

import com.dev.dslist.dto.GameDTO;
import com.dev.dslist.dto.GameListDTO;
import com.dev.dslist.dto.GameMinDTO;
import com.dev.dslist.entities.Game;
import com.dev.dslist.entities.GameList;
import com.dev.dslist.projections.GameMinProjection;
import com.dev.dslist.repositories.GameListRepository;
import com.dev.dslist.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameListService {

    private final GameListRepository gameListRepository;
    private final GameRepository gameRepository;

    public GameListService(GameListRepository gameListRepository, GameRepository gameRepository) {
        this.gameListRepository = gameListRepository;
        this.gameRepository = gameRepository;
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

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> list = this.gameRepository.searchByList(listId);
        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);

        for (int i = min; i <= max; i++) {
            this.gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }

}
