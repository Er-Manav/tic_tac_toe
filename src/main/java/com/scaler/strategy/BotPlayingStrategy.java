package com.scaler.strategy;

import com.scaler.models.Board;
import com.scaler.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
