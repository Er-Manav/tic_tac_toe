package com.scaler.controllers;

import com.scaler.models.Game;
import com.scaler.models.Player;
import com.scaler.models.enums.GameState;
import com.scaler.strategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int size,
                          List<Player> players,
                          List<WinningStrategy> winningStrategies) {
        return Game.getBuilder()
                .setSize(size)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void display(Game game) {
        game.getBoard().display();
    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }
}
