package gprovenz.tictactoe;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static gprovenz.tictactoe.Board.*;

public class AIPlayer {
    public static final int MAX_THINK_TIME = 5;
    private Board board;

    public AIPlayer(Board board) {
        this.board = board;
    }

    public int move(Board board) {
        if (board.isEmpty()) {
            return randomMove(board);
        } else {
            return iterativeDepth();
        }
    }

    @SneakyThrows
    private int iterativeDepth() {
        // Create an ExecutorService with a single thread
        ExecutorService executor = Executors.newSingleThreadExecutor();

        var ref = new Object() {
            int best = -1;
            int depth = 1;
        };
        Future<Void> future = null;
        try {
            // Submit the task to the executor
            future = (Future<Void>) executor.submit(() -> {
                // Call your function here
                for (int depth = 1; depth <= board.getTotalCells(); depth++) {
                    ref.best = getBestMove(new BoardAnalyzer(new Board(board)), depth);
                    ref.depth = depth;
                    if (Thread.interrupted()) {
                        break;
                    }
                }
            });

            // Wait for the result, and specify the timeout
            future.get(MAX_THINK_TIME, TimeUnit.SECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            // do nothing
        } finally {
            future.cancel(true);
            // Shutdown the executor
            executor.shutdownNow();
        }

        System.out.println("Calculated at depth " + ref.depth);
        return ref.best;
    }

    @SneakyThrows
    private int getBestMove(BoardAnalyzer boardAnalyzer, int depth) {
        int[] bestMove = minimax(boardAnalyzer, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
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
        Random random = new Random();
        int randomCell = random.nextInt(validMoves.size());
        return validMoves.get(randomCell);
    }

    private static int evaluateScore(BoardAnalyzer boardAnalyzer, int depth) {
        int winner = boardAnalyzer.getWinner();
        switch (winner) {
            case HUMAN_PLAYER:
                return -boardAnalyzer.getBoard().getTotalCells()*2 - 1 - depth;
            case AI_PLAYER:
                return boardAnalyzer.getBoard().getTotalCells()*2 + 1 + depth;
            default:
                return 0;
        }
    }

    private static int[] minimax(BoardAnalyzer boardAnalyzer, int depth, int alpha, int beta, boolean maximizingPlayer) {
        int score = evaluateScore(boardAnalyzer, depth);
        Board board = boardAnalyzer.getBoard();
        if (depth == 0 || board.isFull() || score != 0) {
            return new int[] {score, -1};
        }

        int bestCell = -1;
        int bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        int currentPlayer = maximizingPlayer ? AI_PLAYER : HUMAN_PLAYER;

        for (int cell = 1; cell <= board.getTotalCells(); cell++) {

            if (board.get(cell) == EMPTY) {
                board.put(cell, currentPlayer);

                int[] currentMove = minimax(boardAnalyzer, depth - 1, alpha, beta, !maximizingPlayer);

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
