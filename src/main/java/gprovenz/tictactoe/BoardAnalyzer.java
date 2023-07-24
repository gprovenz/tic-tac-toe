package gprovenz.tictactoe;

import lombok.AllArgsConstructor;

import static gprovenz.tictactoe.Board.PLAYER1;
import static gprovenz.tictactoe.Board.PLAYER2;

@AllArgsConstructor
public class BoardAnalyzer {

    private Board board;

    public int getWinner() {
        if (checkRowWin(PLAYER1)
                || checkColWin(PLAYER1)
                || checkMainDiagonalWin(PLAYER1)
                || checkAntiDiagonalWin(PLAYER1)) {
            return PLAYER1;
        }
        if (checkRowWin(PLAYER2)
                || checkColWin(PLAYER2)
                || checkMainDiagonalWin(PLAYER2)
                || checkAntiDiagonalWin(PLAYER2)) {
            return PLAYER2;
        }
        return 0;
    }

    private boolean checkRowWin(int player) {
        for (int row = 0; row < board.size(); row++) {
            int count = 0;
            for (int col = 0; col < board.size(); col++) {
                if (board.get(row, col) == player) {
                    count++;
                }
            }
            if (count == board.size()) {
                return true;
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
                }
            }
            if (count == board.size()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMainDiagonalWin(int player) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i, i) != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiagonalWin(int player) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i, board.size() - 1 - i) != player) {
                return false;
            }
        }
        return true;
    }
}
