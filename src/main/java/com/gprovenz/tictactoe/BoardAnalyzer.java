package com.gprovenz.tictactoe;

import lombok.Getter;

import static com.gprovenz.tictactoe.Board.AI_PLAYER;
import static com.gprovenz.tictactoe.Board.HUMAN_PLAYER;

// The class to analyze the board for winners
public class BoardAnalyzer {
    // Maximum number of consecutive symbols in a row/column/diagonal required to win
    public static final int MAX_IN_A_ROW = 4;

    @Getter
    private final Board board;

    // Number of consecutive symbols in a row/column/diagonal required to win
    private final int inARowToWin;

    // Constructor to initialize BoardAnalyzer with the game board
    public BoardAnalyzer(Board board) {
        this.board = board;
        // Determine the minimum value between MAX_IN_A_ROW and the board size
        // (you need 3 in a row for 3x3 boards, 4 in a row for bigger boards)
        this.inARowToWin = Math.min(MAX_IN_A_ROW, board.size());
    }

    // Method to check for a winner (HUMAN_PLAYER or AI_PLAYER) on the board
    public char getWinner() {
        if (checkRowWin(HUMAN_PLAYER)
                || checkColWin(HUMAN_PLAYER)
                || checkMainDiagonalsWin(HUMAN_PLAYER)
                || checkAntiDiagonalsWin(HUMAN_PLAYER)) {
            return HUMAN_PLAYER;
        }
        if (checkRowWin(AI_PLAYER)
                || checkColWin(AI_PLAYER)
                || checkMainDiagonalsWin(AI_PLAYER)
                || checkAntiDiagonalsWin(AI_PLAYER)) {
            return AI_PLAYER;
        }
        return 0;
    }

    // Method to check for a win on a row for a specific player
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

    // Method to check for a win on a column for a specific player
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

    // Method to check for a win on the main diagonals for a specific player
    private boolean checkMainDiagonalsWin(int player) {
        // Check for diagonals starting from different positions within the board
        for (int row = 0; row + inARowToWin <= board.size(); row++) {
            for (int col = 0; col + inARowToWin <= board.size(); col++) {
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

    // Method to check for a win on the anti-diagonals for a specific player
    private boolean checkAntiDiagonalsWin(int player) {
        // Check for diagonals starting from different positions within the board
        for (int row = 0; row + inARowToWin <= board.size(); row++) {
            for (int col = board.size() - 1; col - inARowToWin >= -1; col--) {
                int count = 0;
                int j = col;
                for (int i = row; i < board.size() && j >= 0; i++) {
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
