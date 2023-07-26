package com.gprovenz.tictactoe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Move {
    private final int score;
    private final int cell;
}
