package com.scaler.strategy;

import java.util.List;

import com.scaler.models.Board;
import com.scaler.models.Cell;
import com.scaler.models.Move;
import com.scaler.models.Player;
import com.scaler.models.Symbol;
import com.scaler.models.enums.CellState;
import com.scaler.models.enums.PlayerType;

public class HardBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {
        int bestScore = Integer.MIN_VALUE;
        Cell bestCell = null;
        for (List<Cell> row : board.getCells()) {
            for (Cell cell : row) {
                if (!cell.isEmpty()) {
                    continue;
                }
                place(cell, player);
                int score = minimax(board, player, false, 0);
                clear(cell);
                if (score > bestScore) {
                    bestScore = score;
                    bestCell = cell;
                }
            }
        }

        if (bestCell == null) {
            throw new IllegalStateException("No legal moves are available");
        }
        return new Move(player, bestCell);
    }

    private int minimax(Board board, Player bot, boolean botTurn, int depth) {
        Player opponent = findOpponent(board, bot);
        if (opponent == null) {
            opponent = new Player("opponent", new Symbol(otherSymbol(bot)), PlayerType.HUMAN) {
                @Override
                public Move makeMove(Board ignoredBoard) {
                    return null;
                }
            };
        }
        if (MediumBotPlayingStrategy.hasLine(board, bot)) {
            return 10 - depth;
        }
        if (opponent != null && MediumBotPlayingStrategy.hasLine(board, opponent)) {
            return depth - 10;
        }

        Cell emptyCell = firstEmpty(board);
        if (emptyCell == null) {
            return 0;
        }

        int bestScore = botTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (List<Cell> row : board.getCells()) {
            for (Cell cell : row) {
                if (!cell.isEmpty()) {
                    continue;
                }
                place(cell, botTurn ? bot : opponent);
                int score = minimax(board, bot, !botTurn, depth + 1);
                clear(cell);
                bestScore = botTurn ? Math.max(bestScore, score) : Math.min(bestScore, score);
            }
        }
        return bestScore;
    }

    private Player findOpponent(Board board, Player bot) {
        for (List<Cell> row : board.getCells()) {
            for (Cell cell : row) {
                if (cell.getPlayer() != null && cell.getPlayer() != bot) {
                    return cell.getPlayer();
                }
            }
        }
        return null;
    }

    private char otherSymbol(Player player) {
        return player.getSymbol().getCharacter() == 'X' ? 'O' : 'X';
    }

    private Cell firstEmpty(Board board) {
        for (List<Cell> row : board.getCells()) {
            for (Cell cell : row) {
                if (cell.isEmpty()) {
                    return cell;
                }
            }
        }
        return null;
    }

    private void place(Cell cell, Player player) {
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(player);
    }

    private void clear(Cell cell) {
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);
    }
}
