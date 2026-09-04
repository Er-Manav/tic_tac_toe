package com.scaler.strategy;

import com.scaler.models.Board;
import com.scaler.models.Move;
import com.scaler.models.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player);
}
