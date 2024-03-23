package com.miniclip.galo.afonso.server.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.miniclip.galo.afonso.server.exception.InvalidPlayerException;

public enum Player {
    PLAYER1('X'),
    PLAYER2('O');

    private final char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    @JsonValue
    public char getSymbol() {
        return symbol;
    }

    @JsonCreator
    public static Player fromSymbol(char symbol) {
        return switch (symbol) {
            case 'X' -> PLAYER1;
            case 'O' -> PLAYER2;
            default -> throw new InvalidPlayerException(symbol);
        };
    }

    public static Player getNext(Player player) {
        return switch (player) {
            case PLAYER1 -> PLAYER2;
            case PLAYER2 -> PLAYER1;
        };
    }

}
