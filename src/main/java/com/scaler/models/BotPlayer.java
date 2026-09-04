package com.scaler.models;

import com.scaler.factory.BotPlayingStrategyFactory;
import com.scaler.models.enums.BotDifficultyLevel;
import com.scaler.models.enums.PlayerType;
import com.scaler.strategy.BotPlayingStrategy;

public class BotPlayer extends Player {
    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStrategy playingStrategy;

    public BotPlayer(String name,
                     Symbol symbol,
                     PlayerType playerType,
                     BotDifficultyLevel difficultyLevel) {
        super(name, symbol, playerType);
        this.difficultyLevel = difficultyLevel;

        // Strategy + Factory design patterns.
        this.playingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);

        // This code is violating SRP and OCP - Perfect use-case of Factory.
        // Create a separate factory class.
//        if (difficultyLevel == BotDifficultyLevel.HARD) {
//            this.playingStrategy = new HardBotPlayingStrategy();
//        } else if (difficultyLevel == BotDifficultyLevel.MEDIUM) {
//            this.playingStrategy = new MediumBotPlayingStrategy();
//        } else {
//            this.playingStrategy = new EasyBotPlayingStrategy();
//        }
    }

    @Override
    public Move makeMove(Board board) {
        return playingStrategy.makeMove(board, this);
    }
}
