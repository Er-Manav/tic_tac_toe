package com.scaler.factory;

import com.scaler.models.enums.BotDifficultyLevel;
import com.scaler.strategy.BotPlayingStrategy;
import com.scaler.strategy.EasyBotPlayingStrategy;
import com.scaler.strategy.HardBotPlayingStrategy;
import com.scaler.strategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    // This factory class will help us to create the corresponding BotPlayingStrategy based on the
    // BotDifficultyLevel

    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficultyLevel) {
        if (difficultyLevel.equals(BotDifficultyLevel.EASY)) {
            return new EasyBotPlayingStrategy();
        } else if (difficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
            return new MediumBotPlayingStrategy();
        } else {
            return new HardBotPlayingStrategy();
        }
    }
}
