package gprovenz.tictactoe;

import org.junit.jupiter.api.Test;

import static gprovenz.tictactoe.Board.HUMAN_PLAYER;
import static gprovenz.tictactoe.Board.AI_PLAYER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardAnalyzerTest {

    @Test
    void testNoWinner() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, HUMAN_PLAYER);
        board.put(3, AI_PLAYER);
        board.put(8, HUMAN_PLAYER);
        board.put(9, AI_PLAYER);
        System.out.println(board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(0, winner);
    }

    @Test
    void testPlayer1WinnerCol() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, HUMAN_PLAYER);
        board.put(3, AI_PLAYER);
        board.put(8, AI_PLAYER);
        board.put(9, AI_PLAYER);
        board.put(4, HUMAN_PLAYER);
        board.put(7, HUMAN_PLAYER);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(HUMAN_PLAYER, winner);
    }

    @Test
    void testPlayer2WinnerRow() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, AI_PLAYER);
        board.put(3, AI_PLAYER);
        board.put(7, HUMAN_PLAYER);
        board.put(8, HUMAN_PLAYER);
        board.put(9, HUMAN_PLAYER);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(HUMAN_PLAYER, winner);
    }

    @Test
    void testPlayer2AntiDiag() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, HUMAN_PLAYER);
        board.put(3, AI_PLAYER);
        board.put(5, AI_PLAYER);
        board.put(7, AI_PLAYER);
        board.put(8, HUMAN_PLAYER);
        board.put(9, HUMAN_PLAYER);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(AI_PLAYER, winner);
    }

    @Test
    void testPlayer1Diag() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, HUMAN_PLAYER);
        board.put(3, AI_PLAYER);
        board.put(5, HUMAN_PLAYER);
        board.put(7, AI_PLAYER);
        board.put(8, AI_PLAYER);
        board.put(9, HUMAN_PLAYER);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(HUMAN_PLAYER, winner);
    }


}