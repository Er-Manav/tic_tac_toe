package com.scaler.strategy;

import com.scaler.models.Board;
import com.scaler.models.Move;
import com.scaler.models.Player;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy {
    // Column Hashmaps
    private HashMap<Character, Integer> colMaps[];
    private int size;

    public ColumnWinningStrategy(int size) {
        this.size = size;
        this.colMaps = new HashMap[size];

        for (int i = 0; i < size; i++) {
            colMaps[i] = new HashMap<>();
        }
    }

    @Override
    public boolean checkWinner(Move move) {
        //Get the current player
        Player currentPlayer = move.getPlayer();

        // Get the current col.
        int col = move.getCell().getCol();

        //Hashmap of the current col.
        HashMap<Character, Integer> currColMap = colMaps[col];

        Character character = currentPlayer.getSymbol().getCharacter();

        if (!currColMap.containsKey(character)) {
            currColMap.put(character, 0);
        }

        currColMap.put(character, currColMap.get(character) + 1);

        return currColMap.get(character) == size;
    }
}
