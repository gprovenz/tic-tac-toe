package gprovenz.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

import static gprovenz.tictactoe.Board.*;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the board size (e.g., 3 for 3x3, 4 for 4x4): ");
        int boardSize = scanner.nextInt();

        while (boardSize < 3 || boardSize > 5) {
            System.out.println("Invalid board size. Please enter a size between 3 and 5.");
            System.out.print("Enter the board size: ");
            boardSize = scanner.nextInt();
        }


        boolean playerFirst = false; // Set to false to let AI start first

        while (true) {
            Board board = new Board(boardSize);
            BoardAnalyzer analyzer = new BoardAnalyzer(board);
            AIPlayer aiPlayer = new AIPlayer();

            int totalMoves = boardSize * boardSize;
            int moves = 0;
            int currentTurn = playerFirst ? PLAYER1 : PLAYER2;

            while (moves < totalMoves) {
                // Player's move
                if (currentTurn == PLAYER1) {
                    printBoard(board);
                    int cell;
                    do {
                        System.out.print("Enter cell (1-" + boardSize*boardSize + "): ");
                        cell = scanner.nextInt();
                    } while (!isValidMove(board, cell));

                    board.put(cell, PLAYER1);
                    moves++;
                    if (analyzer.getWinner() == PLAYER1) {
                        printBoard(board);
                        System.out.println("Congratulations! You win!");
                        break;
                    }
                    currentTurn = PLAYER2;
                } else {
                    // AI's move
                    int bestMove = aiPlayer.move(board);
                    board.put(bestMove, PLAYER2);
                    moves++;
                    if (analyzer.getWinner() == PLAYER2) {
                        printBoard(board);
                        System.out.println("AI wins!");
                        break;
                    }
                    currentTurn = PLAYER1;
                }

                // Check if there's a winner (draw is handled by moves == totalMoves)
                if (moves == totalMoves) {
                    printBoard(board);
                    System.out.println("It's a draw!");
                }
            }

            // Switch starting turn for the next game
            playerFirst = !playerFirst;
            System.out.println("Starting turn for the next game: " + (playerFirst ? "Player" : "AI"));
            System.out.println("Do you want to play again? (y/n)");
            Scanner playAgainScanner = new Scanner(System.in);
            String playAgainInput = playAgainScanner.nextLine().toLowerCase();
            if (!playAgainInput.equals("y")) {
                break;
            }
        }

        scanner.close();
    }

    private static void printBoard(Board board) {
        System.out.println(board.printBoard());
    }

    private static boolean isValidMove(Board board, int cell) {
        return cell > 0 && cell <= board.size() * board.size() && board.get(cell)==0;
    }
}

