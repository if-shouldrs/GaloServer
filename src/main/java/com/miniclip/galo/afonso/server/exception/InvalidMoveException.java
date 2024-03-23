package com.miniclip.galo.afonso.server.exception;

import com.miniclip.galo.afonso.server.dto.MoveRequestDto;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException() {
        super("Invalid move");
    }

    public InvalidMoveException(MoveRequestDto move) {
        super("Invalid move: " + move);
    }

}
