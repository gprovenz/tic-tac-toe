# Tic-Tac-Toe with Minimax Algorithm

## Description

This is an open-source Java implementation of the classic game Tic-Tac-Toe with an advanced AI player using the Minimax algorithm with Alpha-Beta Pruning and Iterative Depth. The Minimax algorithm is a decision-making algorithm commonly used in two-player games with perfect information, like Tic-Tac-Toe. It ensures that the AI player always selects the best move possible, leading to a competitive and challenging gaming experience.

The AI player in this game employs Alpha-Beta Pruning to further enhance the search efficiency, and Iterative Depth allows the AI to explore deeper levels of the game tree, providing a stronger challenge on larger boards.

The game provides a simple console-based interface where users can play against the AI. Players can take turns to place their symbol ('X' or 'O') on the board of variable size, ranging from 3x3 up to 6x6. To win, you need to place at least 4 symbols in a row (3 on 3x3 board), either horizontally, vertically, or diagonally.

Please note that the AI player is time-limited in larger boards (e.g., 6x6) to ensure a reasonable and enjoyable game experience. Consequently, skilled players may have a chance to defeat the AI on the larger boards.

## Features

- Play against an intelligent AI that uses the Minimax algorithm with Alpha-Beta Pruning and Iterative Depth.
- Choose the board size (from 3x3 to 6x6) for added complexity.
- The game alternates the starting player between human and AI.
- To win, place at least 4 symbols in a row (3 on 3x3 board), either horizontally, vertically, or diagonally.
- Simple and intuitive console-based interface.

## How to Play

1. Clone the repository to your local machine:

```
git clone https://github.com/gprovenz/tic-tac-toe.git
```

2. Navigate to the project directory:

```
cd tic-tac-toe
```

3. Build the project using Maven:

```
mvn install
```

4. Run the game:

```
java -jar target/tic-tac-toe-1.0-SNAPSHOT.jar
```

5. Follow the on-screen instructions to select the board size, make your moves, and play against the AI. The game will alternate who starts between human and AI.

## Contributing

Contributions to the project are welcome! If you find any bugs or have ideas for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute the code as per the terms of this license.
