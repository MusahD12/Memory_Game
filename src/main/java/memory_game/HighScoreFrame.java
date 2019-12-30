package main.java.memory_game;



import main.java.memory_game.database.HighscoreDB;
import main.java.memory_game.models.Player;

import javax.swing.*;
// creation of separate window for score
public class HighScoreFrame extends JFrame {

    private HighscoreDB database;

    HighScoreFrame() {
        this.database = new HighscoreDB();
        createPanel();
    }

    private void createPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame hFrame = new JFrame();
        JPanel p1Panel = new JPanel();
        Player[] listPlayer = database.getAllScore();
        JList<Player> players = new JList<>(listPlayer);
        p1Panel.add(players);
        hFrame.add(p1Panel);
        hFrame.setVisible(true);
    }
}
