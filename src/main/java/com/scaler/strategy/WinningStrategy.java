package com.scaler.strategy;

import com.scaler.models.Board;
import com.scaler.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Move move);
}
