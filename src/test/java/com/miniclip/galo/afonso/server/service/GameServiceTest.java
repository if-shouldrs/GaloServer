package com.miniclip.galo.afonso.server.service;

import com.miniclip.galo.afonso.server.dto.MoveRequest;
import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = GameServiceImpl.class)
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    void isValidMove_ReturnsTrue_WhenOpeningTopLeftCorner() {
        Match match = Match.newInstance();
        MoveRequest move = new MoveRequest(Player.PLAYER1, 0, 0);
        assertTrue(gameService.isValidMove(match, move));
    }
    @Test
    void isValidMove_ReturnsTrue_WhenOpeningBottomRightCorner() {
        Match match = Match.newInstance();
        MoveRequest move = new MoveRequest(Player.PLAYER1, 2, 2);
        assertTrue(gameService.isValidMove(match, move));
    }

    @Test
    void isValidMove_ReturnsFalse_WhenMoveIsOutOfBounds() {
        Match match = Match.newInstance();
        MoveRequest move = new MoveRequest(Player.PLAYER1, 3, 3); // Out of board bounds
        assertFalse(gameService.isValidMove(match, move));
    }

    @Test
    void isValidMove_ReturnsFalse_WhenWrongPlayerTriesValidMoves() {
        Match match = Match.newInstance();
        MoveRequest move = new MoveRequest(Player.PLAYER2, 0, 0); // PLAYER2 tries to move first
        assertFalse(gameService.isValidMove(match, move));
    }

    @ParameterizedTest
    @MethodSource("getStraightLineWinStates")
    void isWin_ReturnsTrue_onStraightLineWin(String boardState, int row, int col) {
        Match match = Match.newInstance();
        match.setBoardState(boardState);
        // The move that won the game
        MoveRequest move = new MoveRequest(Player.PLAYER1, row, col);
        assertTrue(gameService.isWin(match, move));
    }

    private static Stream<Arguments> getStraightLineWinStates() {
        return Stream.of(
                Arguments.of("XXX,   ,   ", 0, 2),
                Arguments.of("   ,XXX,   ", 1, 2),
                Arguments.of("   ,   ,XXX", 2, 2),
                Arguments.of("X  ,X  ,X  ", 0, 0),
                Arguments.of(" X , X , X ", 0, 1),
                Arguments.of("  X,  X,  X", 0, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("getDiagonalWinStates")
    void isWin_ReturnsTrue_onDiagonalWin(String boardState, int row, int col) {
        Match match = Match.newInstance();
        match.setBoardState(boardState);
        // The move that won the game
        MoveRequest move = new MoveRequest(Player.PLAYER1, row, col);
        assertTrue(gameService.isWin(match, move));
    }

    private static Stream<Arguments> getDiagonalWinStates() {
        return Stream.of(
                Arguments.of("X  , X ,  X", 0, 0),
                Arguments.of("  X, X ,X  ", 0, 2)
        );
    }

    @Test
    void isTie_ReturnsTrue_WhenBoardIsFull() {
        Match match = Match.newInstance();
        match.setBoardState("XOX,XOO,OXX");
        assertTrue(gameService.isTie(match.getBoardState()));
    }

    @Test
    void isTie_ReturnsFalse_WhenBoardIsNotFull() {
        Match match = Match.newInstance();
        match.setBoardState("XOX,XOO,   ");
        assertFalse(gameService.isTie(match.getBoardState()));
    }

}
