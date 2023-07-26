package com.gprovenz.tictactoe;

import lombok.Getter;

// The class to represent the game board
public class Board {

    // Constants to represent different players and empty cells
    public static final char EMPTY = 0;
    public static final char HUMAN_PLAYER = 'X';
    public static final char AI_PLAYER = 'O';

    // Constants for ANSI color codes and a right arrow symbol.
    // They are blank by default since most consoles do not support them.
    public static final String ANSI_RED =       "";
    public static final String ANSI_YELLOW =    "";
    public static final String ANSI_BLUE =      "";
    public static final String ANSI_GREEN =     "";
    public static final String ANSI_CYAN =      "";
    public static final String ANSI_WHITE =     "";
    public static final String RIGHT_ARROW =    ">";

    // Use the constants below instead if your console supports ANSI colors, to have pretty-colored boards:
    /*
    public static final String ANSI_RED =       "\u001B[31m";
    public static final String ANSI_YELLOW =    "\u001B[33m";
    public static final String ANSI_BLUE =      "\u001B[34m";
    public static final String ANSI_GREEN =     "\u001B[32m";
    public static final String ANSI_CYAN =      "\u001B[36m";
    public static final String ANSI_WHITE =     "\u001B[37m";
    public static final String RIGHT_ARROW =    "\u2192";
    */

    // Board size and total number of cells
    private final int size;
    @Getter
    private final int totalCells;

    // 2D array to represent the game board
    private final char[][] board;

    // Array to store the last move made (row and column)
    @Getter
    private final int[] lastMove = new int[2];

    // Constructor to initialize the board with the given size
    public Board(int size) {
        this.size = size;
        this.totalCells = size * size;
        this.board = new char[size][size];
    }

    // Copy constructor to create a new board from an existing board
    public Board(Board board) {
        this(board.size);
        for (int cell=1; cell <= board.getTotalCells(); cell++) {
            this.put(cell, board.get(cell));
        }
    }

    // Getter method for the board size
    public int size() {
        return size;
    }

    // Returns the symbol (X, O, or cell number) at the specified row and column
    public String getSymbol(int row, int col) {
        int value = board[row][col];
        boolean isLastMove = row == lastMove[0] && col == lastMove[1];
        switch (value) {
            case HUMAN_PLAYER: return (isLastMove ? ANSI_GREEN + RIGHT_ARROW : " ") + ANSI_YELLOW + HUMAN_PLAYER;
            case AI_PLAYER: return (isLastMove ? ANSI_GREEN + RIGHT_ARROW : " ") + ANSI_RED + AI_PLAYER;
            default:
                int cellNum = (row * size + col + 1);
                return ANSI_BLUE + String.format("%2s", cellNum);
        }
    }

    // Places a player's symbol at the specified cell
    public void put(int cell, char player) {
        if (cell < 1 || cell > getTotalCells()) {
            throw new IllegalArgumentException("Invalid cell number: " + cell);
        }
        put ((cell-1) / size, (cell-1) % size, player);
    }

    // Places a player's symbol at the specified row and column
    public void put(int row, int col, char player) {
        board[row][col] = player;
        lastMove[0]=row;
        lastMove[1]=col;
    }

    // Returns the symbol (X, O) at the specified cell
    public char get(int cell) {
        return get((cell-1) / size, (cell-1) % size);
    }

    // Returns the symbol (X or O) at the specified row and column
    public char get(int row, int col) {
        return board[row][col];
    }

    // Prints the current board with symbols and separators
    public String printBoard() {
        StringBuilder sb = new StringBuilder(ANSI_CYAN);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(" ").append(getSymbol(i, j)).append(" ").append(ANSI_WHITE);
                if (j < size - 1) {
                    sb.append(" | ");
                }
            }
            sb.append("\n");
            if (i < size - 1) {
                for (int j = 0; j < size; j++) {
                    sb.append("----");
                    if (j < size - 1) {
                        sb.append("-+-");
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    // Checks if the board is completely filled with symbols
    public boolean isFull() {
        for (int i = 1; i <= getTotalCells(); i++) {
            if (get(i) == EMPTY) {
                return false;
            }
        }
        return true;
    }

    // Checks if the board is empty (no symbols placed yet)
    public boolean isEmpty() {
        for (int i = 1; i <= getTotalCells(); i++) {
            if (get(i) != EMPTY) {
                return false;
            }
        }
        return true;
    }
}
