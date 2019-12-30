package main.java.memory_game.core;

import main.java.memory_game.models.Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
/**
 * 
 * contains all the starting menu settings and visuals
 *
 */
public class GameFrameCPU extends GameFrameFactory {
    public GameFrameCPU(Game game) {
        super(game);
        setTitle("Anime Memory Game");
        setVisible(true);
        createScoreBoard();
    }

    // Setting up scoreboard
    public void createScoreBoard() {
        this.game.setScorePlayer1(0);
        seconds = 0;
        JPanel scoring = new JPanel();
        // Player 1
        user1 = new JLabel(this.game.getPlayer1());
        user1.setForeground(Color.WHITE);
        labelScoreP1 = new JLabel("Score");
        scoreBoardP1 = new JLabel("" + this.game.getScorePlayer1());
        JLabel time = new JLabel("Time passed: ");
        clockP1 = new JLabel("Time passed: " + seconds);
        scoreBoardP1.setForeground(Color.RED);
        clockP1.setForeground(Color.WHITE);
        clockP1.setText(Integer.toString(seconds));
        scoring.add(user1);
        scoring.add(labelScoreP1);
        scoring.add(scoreBoardP1);
        scoring.setBackground(Color.BLACK);
        scoring.setBorder(BorderFactory.createTitledBorder(LineBorder.createBlackLineBorder(), "ScoreBoard"));
        JButton restart = new JButton("Restart");
        restart.setBackground(Color.RED);
        restart.setForeground(Color.BLACK);
        restart.addActionListener(e -> restart());
        scoring.add(restart);
        scoring.add(time);
        scoring.add(clockP1);
        add(scoring, BorderLayout.NORTH);

    }
}
