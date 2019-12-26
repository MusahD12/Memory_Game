package main.java.memory_game;



import main.java.memory_game.database.HighscoreDB;
import main.java.memory_game.models.Player;

import javax.swing.*;
import java.awt.*;

public class HighScoreFrame extends JFrame {

    JFrame hFrame;
    JPanel p1Panel;
    JList players;
    HighscoreDB database;
    Player[] listPlayer;

    public HighScoreFrame() {
        this.database = new HighscoreDB();
        createPlanel();
    }

    private void createPlanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hFrame = new JFrame();
        p1Panel = new JPanel();
        listPlayer = database.getAllScore();
        players = new JList(listPlayer);
        p1Panel.add(players);
        hFrame.add(p1Panel);
        hFrame.setVisible(true);
    }
}
