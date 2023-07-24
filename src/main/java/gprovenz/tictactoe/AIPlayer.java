package gprovenz.tictactoe;

import java.util.ArrayList;
import java.util.List;

import static gprovenz.tictactoe.Board.*;

public class AIPlayer {
    private int level = 1;
    private BoardAnalyzer boardAnalyzer;
    private Board board;

    public AIPlayer(Board board) {
        this.board = board;
        this.boardAnalyzer = new BoardAnalyzer(board);
    }

    public int move(Board board) {
        if (level < 1) {
            return randomMove(board);
        } else {
            return getBestMove();
        }
    }

    private int getBestMove() {
        int depth = board.getTotalCells();
        int[] bestMove = minimax(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return bestMove[1];
    }

    private int randomMove(Board board) {
        List<Integer> validMoves = new ArrayList<>();
        for (int i = 1; i <= board.getTotalCells(); i++) {
            if (board.get(i) == EMPTY) {
                validMoves.add(i);
            }
        }
        if (validMoves.isEmpty()) {
            return 0;
        }
        return validMoves.get((int)(Math.random() * validMoves.size()));
    }

    private int evaluateScore(int depth) {
        int winner = boardAnalyzer.getWinner();
        switch (winner) {
            case HUMAN_PLAYER:
                return -board.getTotalCells()*2 - 1 - depth;
            case AI_PLAYER:
                return board.getTotalCells()*2 + 1 + depth;
            default:
                return 0;
        }
    }

    private int[] minimax(int depth, int alpha, int beta, boolean maximizingPlayer) {
        int score = evaluateScore(depth);
        if (depth == 0 || board.isFull() || score != 0) {
            return new int[] {score, -1};
        }

        int bestCell = -1;
        int bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        int currentPlayer = maximizingPlayer ? AI_PLAYER : HUMAN_PLAYER;

        for (int cell = 1; cell <= board.getTotalCells(); cell++) {

            if (board.get(cell) == EMPTY) {
                board.put(cell, currentPlayer);

                int[] currentMove = minimax(depth - 1, alpha, beta, !maximizingPlayer);

                board.put(cell, EMPTY);

                int currentScore = currentMove[0];

                if (maximizingPlayer) {
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestCell = cell;
                    }
                    alpha = Math.max(alpha, bestScore);
                } else {
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestCell = cell;
                    }
                    beta = Math.min(beta, bestScore);
                }

                if (beta <= alpha) {
                    break;
                }
            }
        }

        return new int[]{bestScore, bestCell};
    }
}
