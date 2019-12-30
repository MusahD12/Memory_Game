package main.java.memory_game.core;

import main.java.memory_game.models.Game;
import main.java.memory_game.models.OpponentType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * This class is using the GameFrameFactory we used it to allow a second user to play in the interface.
 */
public class GameFrameHUMAN extends GameFrameFactory {


    static JLabel user2;
    static JLabel labelScoreP2;
    static JLabel scoreBoardP2;
    static JLabel clockP2;


    public GameFrameHUMAN(Game game) {
        super(game);
        setTitle("Anime Memory Game");
        setVisible(true);
        createScoreBoard();
    }

    public void createScoreBoard() {
        this.game.setScorePlayer1(0);
        this.game.setScorePlayer2(0);
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
        user2 = new JLabel(this.game.getPlayer2());
        scoring.add(user2);
        labelScoreP2 = new JLabel("Score");
        scoreBoardP2 = new JLabel("" + this.game.getScorePlayer2());
        scoreBoardP2.setForeground(Color.RED);
        scoring.add(labelScoreP2);
        scoring.add(scoreBoardP2);
        scoring.add(user1);
        scoring.add(labelScoreP1);
        scoring.add(scoreBoardP1);
        scoring.setBackground(Color.BLACK);
        scoring.setBorder(BorderFactory.createTitledBorder(LineBorder.createBlackLineBorder(), "ScoreBoard"));
        JButton restart = new JButton("Restart");
        restart.setBackground(Color.RED);
        restart.setForeground(Color.BLACK);
        restart.addActionListener(e -> super.restart());
        scoring.add(restart);
        scoring.add(time);
        scoring.add(clockP1);
        add(scoring, BorderLayout.NORTH);

    }
}
