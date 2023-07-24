package gprovenz.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer {
    int level = 0;

    public int move(Board board) {
        List<Integer> validMoves = new ArrayList<>();
        for (int i=1; i <= board.getTotalCells(); i++) {
            if (board.get(i) == 0) {
                validMoves.add(i);
            }
        }
        if (validMoves.isEmpty()) {
            return 0;
        }
        return validMoves.get((int)(Math.random() * validMoves.size()));
    }
}
