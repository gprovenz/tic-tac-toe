package gprovenz.tictactoe;

import org.junit.jupiter.api.Test;

import static gprovenz.tictactoe.Board.PLAYER1;
import static gprovenz.tictactoe.Board.PLAYER2;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardAnalyzerTest {

    @Test
    void testNoWinner() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, PLAYER1);
        board.put(3, PLAYER2);
        board.put(8, PLAYER1);
        board.put(9, PLAYER2);
        System.out.println(board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(0, winner);
    }

    @Test
    void testPlayer1WinnerCol() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, PLAYER1);
        board.put(3, PLAYER2);
        board.put(8, PLAYER2);
        board.put(9, PLAYER2);
        board.put(4, PLAYER1);
        board.put(7, PLAYER1);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(PLAYER1, winner);
    }

    @Test
    void testPlayer2WinnerRow() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, PLAYER2);
        board.put(3, PLAYER2);
        board.put(7, PLAYER1);
        board.put(8, PLAYER1);
        board.put(9, PLAYER1);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(PLAYER1, winner);
    }

    @Test
    void testPlayer2AntiDiag() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, PLAYER1);
        board.put(3, PLAYER2);
        board.put(5, PLAYER2);
        board.put(7, PLAYER2);
        board.put(8, PLAYER1);
        board.put(9, PLAYER1);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(PLAYER2, winner);
    }

    @Test
    void testPlayer1Diag() {
        Board board = new Board(3);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(1, PLAYER1);
        board.put(3, PLAYER2);
        board.put(5, PLAYER1);
        board.put(7, PLAYER2);
        board.put(8, PLAYER2);
        board.put(9, PLAYER1);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(PLAYER1, winner);
    }


}