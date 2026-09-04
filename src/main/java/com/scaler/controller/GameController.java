package com.scaler.controller;

import java.io.PrintStream;
import java.util.Scanner;

import com.scaler.models.Cell;
import com.scaler.models.Game;
import com.scaler.models.Move;
import com.scaler.models.Player;
import com.scaler.models.enums.GameState;
import com.scaler.models.enums.PlayerType;

public class GameController {
    private final Game game;
    private final Scanner scanner;
    private final PrintStream output;

    public GameController(Game game, Scanner scanner) {
        this(game, scanner, System.out);
    }

    public GameController(Game game, Scanner scanner, PrintStream output) {
        if (game == null || scanner == null || output == null) {
            throw new IllegalArgumentException("Game, scanner, and output are required");
        }
        this.game = game;
        this.scanner = scanner;
        this.output = output;
    }

    public void play() {
        output.println("Tic-Tac-Toe");
        output.println("Enter moves as: row column");

        while (game.getGameState() == GameState.IN_PROGRESS) {
            game.printBoard();
            Player currentPlayer = game.getCurrentPlayer();

            try {
                Move move = currentPlayer.getPlayerType() == PlayerType.BOT
                        ? currentPlayer.makeMove(game.getBoard())
                        : readHumanMove(currentPlayer);
                game.makeMove(move);
            } catch (IllegalArgumentException exception) {
                output.println(exception.getMessage());
            } catch (IllegalStateException exception) {
                output.println(exception.getMessage());
                return;
            }
        }

        game.printBoard();
        if (game.getWinner() == null) {
            output.println("Game ended in a draw.");
        } else {
            output.println(game.getWinner().getName() + " wins!");
        }
    }

    private Move readHumanMove(Player player) {
        output.print(player.getName() + " (" + player.getSymbol().getCharacter()
                + ") enter row and column (1-" + game.getBoard().getSize() + "): ");
        if (!scanner.hasNextInt()) {
            if (scanner.hasNext()) {
                scanner.next();
                throw new IllegalArgumentException("Move must contain row and column numbers");
            }
            throw new IllegalStateException("Input ended before the game was completed.");
        }
        int row = scanner.nextInt() - 1;
        if (!scanner.hasNextInt()) {
            if (scanner.hasNext()) {
                scanner.next();
                throw new IllegalArgumentException("Move must contain row and column numbers");
            }
            throw new IllegalStateException("Input ended before the game was completed.");
        }
        int col = scanner.nextInt() - 1;
        return new Move(player, new Cell(row, col));
    }
}