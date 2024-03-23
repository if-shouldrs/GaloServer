package com.miniclip.galo.afonso.server.service;

import com.miniclip.galo.afonso.server.model.Match;

import java.util.List;

public interface MatchService {

    Match createMatch();
    Match getMatchById(Long id);
    Match makeMove(Long id, String move);
    List<Long> listAllMatchIds();

}
