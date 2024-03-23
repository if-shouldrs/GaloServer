package com.miniclip.galo.afonso.server.service;

import com.miniclip.galo.afonso.server.dto.MoveRequest;
import com.miniclip.galo.afonso.server.exception.InvalidMoveException;
import com.miniclip.galo.afonso.server.exception.MatchNotFoundException;
import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.model.Move;
import com.miniclip.galo.afonso.server.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final GameService gameService;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, GameService gameService) {
        this.matchRepository = matchRepository;
        this.gameService = gameService;
    }

    @Override
    public Match createMatch() {
        Match newMatch = Match.newInstance();
        return matchRepository.save(newMatch);
    }

    @Override
    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));
    }

    @Override
    public Match makeMove(Long id, MoveRequest request) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));
        Move move = new Move(request.getPlayer(), request.getRow(), request.getCol());

        if (!gameService.isValidMove(match, move)) {
            throw new InvalidMoveException(request);
        }

        gameService.applyMove(match, move);
        return matchRepository.save(match);
    }

    @Override
    public List<Match> listAllMatches() {
        return new ArrayList<>(matchRepository.findAll());
    }

}
