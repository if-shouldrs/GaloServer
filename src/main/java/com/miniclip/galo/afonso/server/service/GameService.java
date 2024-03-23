package com.miniclip.galo.afonso.server.service;

import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.model.Move;

public interface GameService {
    boolean isValidMove(Match match, Move move);
    void applyMove(Match match, Move move);
    boolean isWin(Match match, Move move);
    boolean isTie(String boardState);
}
