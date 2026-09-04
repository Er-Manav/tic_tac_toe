package com.scaler.strategy;

import java.util.HashMap;

import com.scaler.models.Move;
import com.scaler.models.Player;

public class DiagonalWinningStrategy implements WinningStrategy {
    //Diagonal Hashmaps.
    private HashMap<Character, Integer> leftDiagonalMap;
    private HashMap<Character, Integer> rightDiagonalMap;
    private int size;

    public DiagonalWinningStrategy(int size) {
        this.size = size;
        this.leftDiagonalMap = new HashMap<>();
        this.rightDiagonalMap = new HashMap<>();
    }

    @Override
    public boolean checkWinner(Move move) {
        Player currentPlayer = move.getPlayer();

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Character character = currentPlayer.getSymbol().getCharacter();

        if (row == col) {
            if (!leftDiagonalMap.containsKey(character)) {
                leftDiagonalMap.put(character, 0);
            }
            leftDiagonalMap.put(character, leftDiagonalMap.get(character) + 1);
        }

        if (row + col == size - 1) {
            if (!rightDiagonalMap.containsKey(character)) {
                rightDiagonalMap.put(character, 0);
            }
            rightDiagonalMap.put(character, rightDiagonalMap.get(character) + 1);
        }

        return leftDiagonalMap.getOrDefault(character, 0) == size ||
            rightDiagonalMap.getOrDefault(character, 0) == size;
    }
}
