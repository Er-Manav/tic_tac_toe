package com.scaler;

import java.util.List;
import java.util.Scanner;

import com.scaler.controller.GameController;
import com.scaler.models.BotPlayer;
import com.scaler.models.Game;
import com.scaler.models.HumanPlayer;
import com.scaler.models.Symbol;
import com.scaler.models.enums.BotDifficultyLevel;
import com.scaler.models.enums.PlayerType;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HumanPlayer human = new HumanPlayer("Player", new Symbol('X'), PlayerType.HUMAN);
        BotPlayer bot = new BotPlayer("Computer", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.HARD);
        Game game = new Game(3, List.of(human, bot));
        new GameController(game, scanner).play();
    }
}