package com.gprovenz.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AIPlayerIntegrationTest {

    private static final int MAX_THINK_TIME_SECONDS = 2;

    @Test
    void testAIVsHumanOn6x6Board() {
        int[] humanMoves = new int[] {14, 15, 21, 19, 20, 9, 10, 5, 27, 33, 2, 4, 16, 22};
        Board board = new Board(6);
        BoardAnalyzer analyzer = new BoardAnalyzer(board);
        AIPlayer aiPlayer = new AIPlayer(board, MAX_THINK_TIME_SECONDS, Board.AI_PLAYER);
        for (int i=0; i<humanMoves.length; i++) {
            if (board.get(humanMoves[i])!=Board.EMPTY) {
                continue;
            }
            board.put(humanMoves[i], Board.HUMAN_PLAYER);
            print(board);
            boolean moved = aiPlayer.move();

            if (moved) {
                print(board);
            }
            if (analyzer.getWinner()!=0) {
                System.out.println("Winner is: " + analyzer.getWinner());
                break;
            }
        }

        // with short AI think time it is possible to win:
        assertTrue(analyzer.getWinner()==Board.HUMAN_PLAYER);
    }

    @Test
    void testAIvsAIon6x6Board() {
        Board board = new Board(6);
        BoardAnalyzer analyzer = new BoardAnalyzer(board);
        AIPlayer aiPlayer1 = new AIPlayer(board, MAX_THINK_TIME_SECONDS, Board.HUMAN_PLAYER);
        AIPlayer aiPlayer2 = new AIPlayer(board, MAX_THINK_TIME_SECONDS, Board.AI_PLAYER);
        while (true) {
            boolean moved1 = aiPlayer1.move();
            if (moved1) {
                print(board);
            }
            boolean moved2 = aiPlayer2.move();
            if (moved2) {
                print(board);
            }

            if (board.isFull()) {
                System.out.println("It's a draw!");
                break;
            }
            if (analyzer.getWinner()!=0 || board.isFull()) {
                System.out.println("Winner is: " + analyzer.getWinner());
                break;
            }
        }

    }

    private void print(Board board) {
        System.out.println(board.printBoard());
    }

}
