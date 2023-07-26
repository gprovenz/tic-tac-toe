package com.gprovenz.tictactoe;

import lombok.Getter;

import static com.gprovenz.tictactoe.Board.AI_PLAYER;
import static com.gprovenz.tictactoe.Board.HUMAN_PLAYER;

public class BoardAnalyzer {
    public static final int MAX_IN_A_ROW = 4;

    @Getter
    private final Board board;

    private final int inARowToWin;

    public BoardAnalyzer(Board board) {
        this.board = board;
        this.inARowToWin = Math.min(MAX_IN_A_ROW, board.size());
    }

    public char getWinner() {
        if (checkRowWin(HUMAN_PLAYER)
                || checkColWin(HUMAN_PLAYER)
                || checkMainDiagonalWin(HUMAN_PLAYER)
                || checkAntiDiagonalWin(HUMAN_PLAYER)) {
            return HUMAN_PLAYER;
        }
        if (checkRowWin(AI_PLAYER)
                || checkColWin(AI_PLAYER)
                || checkMainDiagonalWin(AI_PLAYER)
                || checkAntiDiagonalWin(AI_PLAYER)) {
            return AI_PLAYER;
        }
        return 0;
    }

    private boolean checkRowWin(int player) {
        for (int row = 0; row < board.size(); row++) {
            int count = 0;
            for (int col = 0; col < board.size(); col++) {
                if (board.get(row, col) == player) {
                    count++;
                    if (count >= inARowToWin) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }

        }
        return false;
    }

    private boolean checkColWin(int player) {
        for (int col = 0; col < board.size(); col++) {
            int count = 0;
            for (int row = 0; row < board.size(); row++) {
                if (board.get(row, col) == player) {
                    count++;
                    if (count >= inARowToWin) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    public int getInARowToWin() {
        return Math.min(MAX_IN_A_ROW, board.size());
    }

    private boolean checkMainDiagonalWin(int player) {
        // check other diagonals for >= 5x5
        for (int row=0; row + getInARowToWin() <= board.size(); row++ ) {
            for (int col=0; col + getInARowToWin() <= board.size(); col++ ) {
                int count = 0;
                int j = col;
                for (int i = row; i < board.size() && j < board.size(); i++) {
                    if (board.get(i, j) == player) {
                        count++;
                        if (count >= inARowToWin) {
                            return true;
                        }
                    } else {
                        count = 0;
                    }
                    j++;
                }
            }
        }

        return false;
    }

    private boolean checkAntiDiagonalWin(int player) {
        // check other diagonals for >= 5x5
        for (int row=0; row + getInARowToWin() <= board.size(); row++ ) {
            for (int col=board.size()-1; col - getInARowToWin() >= -1; col--) {
                int count = 0;
                int j = col;
                for (int i = row; i < board.size() && j >=0; i++) {
                    if (board.get(i, j) == player) {
                        count++;
                        if (count >= inARowToWin) {
                            return true;
                        }
                    } else {
                        count = 0;
                    }
                    j--;
                }
            }
        }

        return false;
    }
}
