package com.gprovenz.tictactoe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// Simple class to store score of the move and and associated cell on the board
public class Move {
    private final int score;
    private final int cell;
}
