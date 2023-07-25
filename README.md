# Tic-Tac-Toe with Minimax Algorithm

![Tic-Tac-Toe](tic_tac_toe.jpg)

## Description

This is an open-source Java implementation of the classic game Tic-Tac-Toe with an advanced AI player using the Minimax algorithm. The Minimax algorithm is a decision-making algorithm commonly used in two-player games with perfect information, like Tic-Tac-Toe. It ensures that the AI player always selects the best move possible, leading to a competitive and challenging gaming experience.

The game provides a simple console-based interface where users can play against the AI. Players can take turns to place their symbol ('X' or 'O') on the board of variable size, ranging from 3x3 up to 6x6. To win, you need to place at least 4 symbols in a row, either horizontally, vertically, or diagonally.

## Features

- Play against an intelligent AI that uses the Minimax algorithm.
- Choose the board size (from 3x3 to 6x6) for added complexity.
- To win, place at least 3 symbols in a row (in 3x3 board) or at least 4 simbols in a row on bigger boards, either horizontally, vertically, or diagonally
- Simple and intuitive console-based interface.
- Error handling for invalid moves and user input.
- The game alternates who plays first
- AI is time limited so you could win (at least on 6x6 board)

## How to Play

1. Clone the repository to your local machine:

```
git clone https://github.com/gprovenz/TicTacToe.git
```

2. Navigate to the project directory:

```
cd TicTacToe
```

3. Compile the Java code:

```
javac TicTacToe.java
```

4. Run the game:

```
java TicTacToe
```

5. Follow the on-screen instructions to select the board size, make your moves, and play against the AI.

## Contributing

Contributions to the project are welcome! If you find any bugs or have ideas for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute the code as per the terms of this license.
