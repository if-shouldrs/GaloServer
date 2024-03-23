package com.miniclip.galo.afonso.server.service;

import com.miniclip.galo.afonso.server.dto.MoveRequest;
import com.miniclip.galo.afonso.server.model.Match;

public interface GameService {
    boolean isValidMove(Match match, MoveRequest move);
    void applyMove(Match match, MoveRequest move);
    boolean isWin(Match match, MoveRequest move);
    boolean isTie(String boardState);
}
