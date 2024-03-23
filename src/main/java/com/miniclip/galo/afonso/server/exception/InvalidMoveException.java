package com.miniclip.galo.afonso.server.exception;

import com.miniclip.galo.afonso.server.dto.MoveRequest;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(MoveRequest move) {
        super("Invalid move: " + move);
    }

}
