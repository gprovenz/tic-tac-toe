package com.gprovenz.tictactoe;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static com.gprovenz.tictactoe.Board.*;

public class AIPlayer {

    // The game board
    private final Board board;

    // Time in seconds for the AI to think before making a move
    private final int thinkTimeSeconds;

    // The player symbol for the AI (X or O)
    private final char player;

    // Constructor to initialize AIPlayer with the game board, think time, and player symbol
    public AIPlayer(Board board, int thinkTimeSeconds, char player) {
        this.board = board;
        this.thinkTimeSeconds = thinkTimeSeconds;
        this.player = player;
    }

    // Method to make a move for the AI player
    public boolean move() {
        int move;
        if (board.isEmpty()) {
            move = randomMove(board);
        } else {
            move = iterativeDepth();
        }
        if (move > 0) {
            board.put(move, player);
            return true;
        }
        return false;
    }

    // Method to calculate the best move using iterative deepening
    @SneakyThrows
    private int iterativeDepth() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        var ref = new Object() {
            int best = -1;
            int depth = 1;
        };
        Future<Void> future = null;
        try {
            // I use a futures to interrupt iterative depth when timeout expires
            future = (Future<Void>) executor.submit(() -> {
                // Starts from lower depth, then increase to higher ones until timeout expires
                for (int depth = 1; depth <= board.getTotalCells(); depth++) {
                    ref.best = getBestMove(new BoardAnalyzer(new Board(board)), depth);
                    ref.depth = depth;
                    if (Thread.interrupted()) {
                        break;
                    }
                }
            });

            // Wait for the result, and specify the timeout
            future.get(thinkTimeSeconds, TimeUnit.SECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            // do nothing
        } finally {
            if (future != null) {
                future.cancel(true);
            }
            // Shutdown the executor
            executor.shutdownNow();
        }

        // Print the depth used to calculate the AI move
        System.out.println("AI move calculated with depth " + ref.depth);
        return ref.best;
    }

    // Method to get the best move based on the current board state and depth
    private int getBestMove(BoardAnalyzer boardAnalyzer, int depth) {
        Move bestMove = minimax(boardAnalyzer, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return bestMove.getCell();
    }

    // Method to choose a random valid move when the board is empty
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
        Random random = new Random();
        int randomCell = random.nextInt(validMoves.size());
        return validMoves.get(randomCell);
    }

    // Method to evaluate the score of the current board state based on the winner and depth
    private static int evaluateScore(BoardAnalyzer boardAnalyzer, int depth) {
        char winner= boardAnalyzer.getWinner();
        switch (winner) {
            case HUMAN_PLAYER:
                return -boardAnalyzer.getBoard().getTotalCells() * 2 - 1 - depth;
            case AI_PLAYER:
                return boardAnalyzer.getBoard().getTotalCells() * 2 + 1 + depth;
            default:
                return 0;
        }
    }

    // Minimax algorithm implementation to find the best move based on the current board state and depth
    private static Move minimax(BoardAnalyzer boardAnalyzer, int depth, int alpha, int beta, boolean maximizingPlayer) {
        int score = evaluateScore(boardAnalyzer, depth);
        Board board = boardAnalyzer.getBoard();
        if (depth == 0 || board.isFull() || score != 0) {
            return new Move(score, -1);
        }

        int bestCell = -1;
        int bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        char currentPlayer = maximizingPlayer ? AI_PLAYER : HUMAN_PLAYER;

        for (int cell = 1; cell <= board.getTotalCells(); cell++) {

            if (board.get(cell) == EMPTY) {
                board.put(cell, currentPlayer);

                Move currentMove = minimax(boardAnalyzer, depth - 1, alpha, beta, !maximizingPlayer);

                board.put(cell, EMPTY);

                if (maximizingPlayer) {
                    if (currentMove.getScore() > bestScore) {
                        bestScore = currentMove.getScore();
                        bestCell = cell;
                    }
                    alpha = Math.max(alpha, bestScore);
                } else {
                    if (currentMove.getScore() < bestScore) {
                        bestScore = currentMove.getScore();
                        bestCell = cell;
                    }
                    beta = Math.min(beta, bestScore);
                }

                if (beta <= alpha) {
                    break;
                }
            }
        }

        return new Move (bestScore, bestCell);
    }
}
