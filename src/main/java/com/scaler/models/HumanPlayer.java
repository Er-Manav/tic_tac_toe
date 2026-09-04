package com.scaler.models;

import java.util.Scanner;

import com.scaler.models.enums.PlayerType;

public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(String name, Symbol symbol, PlayerType playerType) {
        this(name, symbol, playerType, new Scanner(System.in));
    }

    public HumanPlayer(String name, Symbol symbol, PlayerType playerType, Scanner scanner) {
        super(name, symbol, playerType);
        this.scanner = scanner;
    }

    @Override
    public Move makeMove(Board board) {
        while (true) {
            System.out.print(getName() + " (" + getSymbol().getCharacter() + ") enter row and column (1-"
                    + board.getSize() + "): ");
            if (!scanner.hasNextInt()) {
                throw new IllegalArgumentException("Move must contain row and column numbers");
            }
            int row = scanner.nextInt() - 1;
            if (!scanner.hasNextInt()) {
                throw new IllegalArgumentException("Move must contain row and column numbers");
            }
            int col = scanner.nextInt() - 1;
            if (row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize()
                    && board.getCells().get(row).get(col).isEmpty()) {
                return new Move(this, board.getCells().get(row).get(col));
            }
            System.out.println("That cell is not available.");
        }
    }
}
