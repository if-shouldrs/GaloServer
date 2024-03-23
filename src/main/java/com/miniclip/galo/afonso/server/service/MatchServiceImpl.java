package com.miniclip.galo.afonso.server.service;

import com.miniclip.galo.afonso.server.dto.MoveRequest;
import com.miniclip.galo.afonso.server.exception.InvalidMoveException;
import com.miniclip.galo.afonso.server.exception.MatchNotFoundException;
import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
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
    public Match makeMove(Long id, MoveRequest move) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));

        if (!isValidMove(match, move)) {
            throw new InvalidMoveException(move);
        }

        applyMove(match, move);
        return matchRepository.save(match);
    }

    private boolean isValidMove(Match match, MoveRequest move) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void applyMove(Match match, MoveRequest move) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Long> listAllMatchIds() {
        return matchRepository.findAll().stream()
                .map(Match::getId)
                .collect(Collectors.toList());
    }
}
