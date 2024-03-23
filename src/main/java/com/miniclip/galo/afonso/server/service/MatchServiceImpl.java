package com.miniclip.galo.afonso.server.service;

import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match createMatch() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Match getMatchById(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Match makeMove(Long id, String move) {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @Override
    public List<Long> listAllMatchIds() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
