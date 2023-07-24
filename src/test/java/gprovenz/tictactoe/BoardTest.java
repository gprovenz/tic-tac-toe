package gprovenz.tictactoe;

import org.junit.jupiter.api.Test;

import static gprovenz.tictactoe.Board.AI_PLAYER;
import static gprovenz.tictactoe.Board.HUMAN_PLAYER;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    @Test
    void testFullBoard() {
        Board board = new Board(3);

        board.put(1, HUMAN_PLAYER);
        board.put(2, AI_PLAYER);
        board.put(3, AI_PLAYER);
        board.put(4, AI_PLAYER);
        board.put(5, HUMAN_PLAYER);
        board.put(6, HUMAN_PLAYER);
        board.put(7, HUMAN_PLAYER);
        board.put(8, AI_PLAYER);
        board.put(9, AI_PLAYER);

        System.out.println(board.printBoard());

        assertTrue(board.isFull());
    }

    @Test
    void testNotFullBoard() {
        Board board = new Board(3);

        board.put(1, HUMAN_PLAYER);
        board.put(2, AI_PLAYER);
        board.put(3, AI_PLAYER);
        board.put(4, AI_PLAYER);
        board.put(5, HUMAN_PLAYER);
        board.put(6, HUMAN_PLAYER);
        board.put(7, HUMAN_PLAYER);
        board.put(8, AI_PLAYER);

        System.out.println(board.printBoard());

        assertFalse(board.isFull());
    }
}
