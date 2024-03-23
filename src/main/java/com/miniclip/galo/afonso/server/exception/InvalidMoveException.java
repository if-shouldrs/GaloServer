package com.miniclip.galo.afonso.server.exception;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(String move) {
        super("Invalid move: " + move);
    }

}
