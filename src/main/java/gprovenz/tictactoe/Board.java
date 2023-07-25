package gprovenz.tictactoe;

import lombok.Getter;

public class Board {

    public static final char EMPTY = 0;
    public static final char HUMAN_PLAYER = 'X';
    public static final char AI_PLAYER = 'O';

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private int size;

    @Getter
    private int totalCells;

    private char[][] board;

    public Board(int size) {
        this.size = size;
        this.totalCells = size * size;
        this.board = new char[size][size];
    }

    public Board(Board board) {
        this(board.size);
        for (int cell=1; cell <= board.getTotalCells(); cell++) {
            this.put(cell, board.get(cell));
        }
    }

    public int size() {
        return size;
    }

    public String getSymbol(int row, int col) {
        int value = board[row][col];
        switch (value) {
            case HUMAN_PLAYER: return ANSI_YELLOW + " " + HUMAN_PLAYER;
            case AI_PLAYER: return ANSI_RED + " " + AI_PLAYER;
            default:
                int cellNum = (row * size + col + 1);
                return ANSI_BLUE + String.format("%2s", cellNum);
        }
    }

    public void put(int cell, char player) {
        if (cell < 1 || cell > getTotalCells()) {
            throw new IllegalArgumentException("Invalid cell number: " + cell);
        }
        put ((cell-1) / size, (cell-1) % size, player);
    }

    public void put(int row, int col, char player) {
        board[row][col] = player;
    }

    public char get(int cell) {
        return get((cell-1) / size, (cell-1) % size);
    }

    public char get(int row, int col) {
        return board[row][col];
    }

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

    public boolean isFull() {
        for (int i = 1; i <= getTotalCells(); i++) {
            if (get(i) == EMPTY) {
                return false;
            }
        }
        return true;
    }


    public boolean isEmpty() {
        for (int i = 1; i <= getTotalCells(); i++) {
            if (get(i) != EMPTY) {
                return false;
            }
        }
        return true;
    }

}

