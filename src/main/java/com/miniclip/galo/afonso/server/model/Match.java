package com.miniclip.galo.afonso.server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String boardState;
    private MatchStatus status;
    private Player turn;

    /**
     * Creates an instance of Match with a clean state ready to be played.
     * @return a fresh playable instance of Match
     */
    public static Match newInstance() {
        Match match = new Match();
        match.boardState = "   ,   ,   ";
        match.status = MatchStatus.RUNNING;
        match.turn = Player.PLAYER1;
        return match;
    }

}
