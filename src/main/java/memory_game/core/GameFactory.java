package main.java.memory_game.core;

import main.java.memory_game.database.HighscoreDB;
import main.java.memory_game.models.Game;
import main.java.memory_game.models.OpponentType;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This is the main factory, by being abstract with this class you can only have a CPU GameStrategy or a Human Game
 * Strategy.
 * This can allow you in the future to add more specfic behavior but it's one behavior by game
 */
public abstract class GameFactory implements ActionListener {

    public Game game;
    public HighscoreDB database;
    ///
    ImageIcon m1 = new ImageIcon(this.getClass().getResource("../Icons/m1.jpg"));
    ImageIcon joker = new ImageIcon(this.getClass().getResource("../Icons/m4.jpg"));
    static int oddClicks;
    static int clicks;
    static int openImages;
    Timer timer;

    public GameFactory(Game game) {
        this.game = game;
        this.database = new HighscoreDB();
    }

    public static GameFactory getGameStrategy(Game game) {
        if (game.getOpponentType() == OpponentType.CPU)
            return new CPUGameStrategy(game);
        else if (game.getOpponentType() == OpponentType.HUMAN) {
            return new HUMANGameStrategy(game);
        } else {
            System.err.println("This game strategy is not implemented yet !");
            return null;
        }
    }

}
