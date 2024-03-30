package com.miniclip.galo.afonso.server.service;

import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.model.MatchStatus;
import com.miniclip.galo.afonso.server.model.Move;
import com.miniclip.galo.afonso.server.model.Player;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public boolean isValidMove(Match match, Move move) {
        if (!MatchStatus.RUNNING.equals(match.getStatus())) {
            return false;
        }

        int row = move.getRow();
        int col = move.getCol();
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }

        // each row takes up 4 characters (3 for playable positions and 1 for the comma)
        int position = (row * 4) + col;

        // Check if the chosen position is empty
        if (!Character.isWhitespace(match.getBoardState().charAt(position))) {
            return false;
        }

        // Ensure it is the correct player's turn
        return match.getTurn().equals(move.getPlayer());
    }

    @Override
    public void applyMove(Match match, Move move) {
        int position = (move.getRow() * 4) + move.getCol();

        // Replace character at the move's position with the player's character
        StringBuilder board = new StringBuilder(match.getBoardState());
        board.setCharAt(position, move.getPlayer().getSymbol());

        match.setBoardState(board.toString());

        // Handle end of match conditions
        processMatchStatus(match, move);
    }

    private void processMatchStatus(Match match, Move move) {
        MatchStatus status = MatchStatus.RUNNING;

        if (isWin(match, move)) {
            status = move.getPlayer().equals(Player.PLAYER1) ? MatchStatus.PLAYER1WON : MatchStatus.PLAYER2WON;
        } else if (isTie(match.getBoardState())) {
            status = MatchStatus.TIE;
        } else {
            match.setTurn(Player.getNext(move.getPlayer()));
        }

        match.setStatus(status);
    }

    @Override
    public boolean isWin(Match match, Move move) {
        char symbol = move.getPlayer().getSymbol();
        String board = match.getBoardState();
        return isStraightLineWin(board, symbol) || isDiagonalWin(board, symbol);
    }

    @Override
    public boolean isTie(String boardState) {
        return !boardState.contains(" ");
    }

    private static boolean isStraightLineWin(String board, char symbol) {
        for (int i = 0; i < 3; i++) {
            if ((board.charAt(i * 4) == symbol && board.charAt(i * 4 + 1) == symbol && board.charAt(i * 4 + 2) == symbol) || // Rows
                    (board.charAt(i) == symbol && board.charAt(4 + i) == symbol && board.charAt(8 + i) == symbol)) { // Columns
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String board, char symbol) {
        return (board.charAt(0) == symbol && board.charAt(5) == symbol && board.charAt(10) == symbol) // Left-to-right
                || (board.charAt(2) == symbol && board.charAt(5) == symbol && board.charAt(8) == symbol); // Right-to-left
    }
    
}
