package com.miniclip.galo.afonso.server.exception;

public class InvalidPlayerException extends RuntimeException {

    public InvalidPlayerException(char symbol) {
        super("Invalid player: " + symbol);
    }

}
