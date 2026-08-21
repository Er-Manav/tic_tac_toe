package com.scaler.models;

import com.scaler.models.enums.PlayerType;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, Symbol symbol, PlayerType playerType) {
        super(name, symbol, playerType);
    }

    @Override
    public Move makeMove(Board board) {
        return null;
    }
}
