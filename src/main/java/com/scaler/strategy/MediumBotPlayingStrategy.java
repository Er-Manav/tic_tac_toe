package com.scaler.strategy;

import java.util.List;

import com.scaler.models.Board;
import com.scaler.models.Cell;
import com.scaler.models.Move;
import com.scaler.models.Player;
import com.scaler.models.enums.CellState;

public class MediumBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {
        Cell winningCell = findTacticalMove(board, player, player);
        if (winningCell != null) {
            return new Move(player, winningCell);
        }

        for (List<Cell> row : board.getCells()) {
            for (Cell cell : row) {
                if (!cell.isEmpty()) {
                    continue;
                }
                cell.setCellState(CellState.FILLED);
                cell.setPlayer(player);
                boolean createsLine = hasLine(board, player);
                cell.setCellState(CellState.EMPTY);
                cell.setPlayer(null);
                if (createsLine) {
                    return new Move(player, cell);
                }
            }
        }

        return new EasyBotPlayingStrategy().makeMove(board, player);
    }

    private Cell findTacticalMove(Board board, Player actingPlayer, Player targetPlayer) {
        for (List<Cell> row : board.getCells()) {
            for (Cell cell : row) {
                if (!cell.isEmpty()) {
                    continue;
                }
                cell.setCellState(CellState.FILLED);
                cell.setPlayer(targetPlayer);
                boolean createsLine = hasLine(board, targetPlayer);
                cell.setCellState(CellState.EMPTY);
                cell.setPlayer(null);
                if (createsLine) {
                    return cell;
                }
            }
        }
        return null;
    }

    static boolean hasLine(Board board, Player player) {
        int size = board.getSize();
        for (int index = 0; index < size; index++) {
            boolean rowComplete = true;
            boolean columnComplete = true;
            for (int offset = 0; offset < size; offset++) {
                rowComplete &= board.getCells().get(index).get(offset).getPlayer() == player;
                columnComplete &= board.getCells().get(offset).get(index).getPlayer() == player;
            }
            if (rowComplete || columnComplete) {
                return true;
            }
        }

        boolean leftDiagonalComplete = true;
        boolean rightDiagonalComplete = true;
        for (int index = 0; index < size; index++) {
            leftDiagonalComplete &= board.getCells().get(index).get(index).getPlayer() == player;
            rightDiagonalComplete &= board.getCells().get(index).get(size - index - 1).getPlayer() == player;
        }
        return leftDiagonalComplete || rightDiagonalComplete;
    }
}
