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



    @Test
    void test5x5AntiDiag() {
        Board board = new Board(5);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(4, HUMAN_PLAYER);
        board.put(8, HUMAN_PLAYER);
        board.put(12, HUMAN_PLAYER);
        board.put(16, HUMAN_PLAYER);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(HUMAN_PLAYER, winner);

        board = new Board(5);
        boardAnalyzer = new BoardAnalyzer(board);

        board.put(10, AI_PLAYER);
        board.put(14, AI_PLAYER);
        board.put(18, AI_PLAYER);
        board.put(22, AI_PLAYER);

        System.out.println("\n" + board.printBoard());
        winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(AI_PLAYER, winner);
    }

    @Test
    void test5x5Diag() {
        Board board = new Board(5);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(2, HUMAN_PLAYER);
        board.put(8, HUMAN_PLAYER);
        board.put(14, HUMAN_PLAYER);
        board.put(20, HUMAN_PLAYER);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(HUMAN_PLAYER, winner);

        board = new Board(5);
        boardAnalyzer = new BoardAnalyzer(board);

        board.put(6, AI_PLAYER);
        board.put(12, AI_PLAYER);
        board.put(18, AI_PLAYER);
        board.put(24, AI_PLAYER);

        System.out.println("\n" + board.printBoard());
        winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(AI_PLAYER, winner);
    }

    @Test
    void test6x6Diag() {
        Board board = new Board(6);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(3, HUMAN_PLAYER);
        board.put(10, HUMAN_PLAYER);
        board.put(17, HUMAN_PLAYER);
        board.put(24, HUMAN_PLAYER);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(HUMAN_PLAYER, winner);

        board = new Board(6);
        boardAnalyzer = new BoardAnalyzer(board);

        board.put(13, AI_PLAYER);
        board.put(20, AI_PLAYER);
        board.put(27, AI_PLAYER);
        board.put(34, AI_PLAYER);

        System.out.println("\n" + board.printBoard());
        winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(AI_PLAYER, winner);
    }

    @Test
    void test6x6AntiDiag() {
        Board board = new Board(6);
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        board.put(5, HUMAN_PLAYER);
        board.put(10, HUMAN_PLAYER);
        board.put(15, HUMAN_PLAYER);
        board.put(20, HUMAN_PLAYER);

        System.out.println("\n" + board.printBoard());
        int winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(HUMAN_PLAYER, winner);

        board = new Board(6);
        boardAnalyzer = new BoardAnalyzer(board);

        board.put(17, AI_PLAYER);
        board.put(22, AI_PLAYER);
        board.put(27, AI_PLAYER);
        board.put(32, AI_PLAYER);

        System.out.println("\n" + board.printBoard());
        winner = boardAnalyzer.getWinner();
        System.out.println("Winner: " + board.getSymbol(winner));

        assertEquals(AI_PLAYER, winner);
    }
}