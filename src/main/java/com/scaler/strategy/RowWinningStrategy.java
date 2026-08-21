package com.scaler.strategy;

import com.scaler.models.Board;
import com.scaler.models.Move;
import com.scaler.models.Player;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy {
    // Row Hashmaps
    private HashMap<Character, Integer> rowMaps[];
    private int size;

    public RowWinningStrategy(int size) {
        this.size = size;
        this.rowMaps = new HashMap[size];

        for (int i = 0; i < size; i++) {
            rowMaps[i] = new HashMap<>();
        }
    }

    @Override
    public boolean checkWinner(Move move) {
        //Get the current player
        Player currentPlayer = move.getPlayer();

        // Get the current row.
        int row = move.getCell().getRow();

        //Hashmap of the current row.
        HashMap<Character, Integer> currRowMap = rowMaps[row];

        Character character = currentPlayer.getSymbol().getCharacter();

        if (!currRowMap.containsKey(character)) {
            currRowMap.put(character, 0);
        }

        currRowMap.put(character, currRowMap.get(character) + 1);

        return currRowMap.get(character) == size;
    }
}
