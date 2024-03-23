package com.miniclip.galo.afonso.server.exception;

public class MatchNotFoundException extends RuntimeException {

    public MatchNotFoundException(Long id) {
        super("Match with id " + id + " not found");
    }

}
