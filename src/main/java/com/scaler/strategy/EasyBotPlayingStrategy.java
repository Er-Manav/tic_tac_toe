package com.scaler.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.scaler.models.Board;
import com.scaler.models.Cell;
import com.scaler.models.Move;
import com.scaler.models.Player;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {
        List<Cell> emptyCells = new ArrayList<>();
        for (List<Cell> row : board.getCells()) {
            for (Cell cell : row) {
                if (cell.isEmpty()) {
                    emptyCells.add(cell);
                }
            }
        }

        if (emptyCells.isEmpty()) {
            throw new IllegalStateException("No legal moves are available");
        }

        Cell cell = emptyCells.get(ThreadLocalRandom.current().nextInt(emptyCells.size()));
        return new Move(player, cell);
    }
}
