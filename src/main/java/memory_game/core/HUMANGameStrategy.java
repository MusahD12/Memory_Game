package main.java.memory_game.core;

import main.java.memory_game.models.Game;
import main.java.memory_game.models.Level;
import main.java.memory_game.models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.java.memory_game.core.GameFrameFactory.*;
import static main.java.memory_game.core.GameFrameHUMAN.scoreBoardP2;
import static main.java.memory_game.core.GameFrameHUMAN.user2;
import static main.java.memory_game.models.Level.*;

/**
 * This class is used to use a specific behavior versus another user
 */
public class HUMANGameStrategy extends GameFactory implements ActionListener {


    public HUMANGameStrategy(Game game) {
        super(game);
        timer = new Timer(1000, actionEvent -> {
            buttons[clicks].setIcon(m1);
            buttons[oddClicks].setIcon(m1);
            timer.stop();

        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int nButton = this.game.getBoard().getRows() * this.game.getBoard().getColumns();
        int z = 400 - 2 * openImages;
        if (nButton == 24 || nButton == 16) {
            z = 400 - (2 * seconds) - (2 * openImages);
        }

        if (timer.isRunning())
            return;

        openImages++;

        for (int i = 0; i < nButton; i++) {
            if (e.getSource() == buttons[i]) {
                buttons[i].setIcon(icons[i]);
                clicks = i;
            }
        }
        if (openImages % 2 == 0) {
            if (clicks == oddClicks) {
                clicks--;
                return;
            }
            if (icons[clicks] != icons[oddClicks]) {
                timer.start();
                if (this.game.getCurrentUser() == 1) {
                    user2.setForeground(Color.WHITE);
                    user1.setForeground(Color.GRAY);
                    this.game.changeUser();
                } else {
                    user2.setForeground(Color.GRAY);
                    user1.setForeground(Color.WHITE);
                    this.game.changeUser();
                }
            } else {
                buttons[clicks].setEnabled(false);
                buttons[clicks].setDisabledIcon(icons[clicks]);
                buttons[oddClicks].setEnabled(false);
                buttons[oddClicks].setDisabledIcon(icons[oddClicks]);

                // Define score for each player
                if (this.game.getCurrentUser() == 1) {
                    this.game.setScorePlayer1(this.game.getScorePlayer1() + 1);
                    scoreBoardP1.setText(Integer.toString(this.game.getScorePlayer1() * z));
                } else {
                    this.game.setScorePlayer2(this.game.getScorePlayer2() + 1);
                    scoreBoardP2.setText(Integer.toString(this.game.getScorePlayer2() * z));
                }

                // Define winner
                if ((this.game.getScorePlayer1() + this.game.getScorePlayer2()) == nButton / 2) {
                    int score1 = Integer.parseInt(scoreBoardP1.getText());
                    int score2 = Integer.parseInt(scoreBoardP2.getText());
                    if(score1 > score2) {
                        JOptionPane.showMessageDialog(f, this.game.getPlayer1() + "won !");
                        saveWinner(this.game.getPlayer1(), score1, this.game.getLevel());
                    }
                    else {
                        JOptionPane.showMessageDialog(f, this.game.getPlayer2() + "won !");
                        saveWinner(this.game.getPlayer2(), score2, this.game.getLevel());
                    }
                }

            }
        } else {
            oddClicks = clicks;


        }
    }

    /**
     * Save winner
     * @param player
     * @param score
     * @param level
     */
    private void saveWinner(String player, int score, Level level) {
        if (this.game.getLevel() == EASY) {
            database.insertScore(new Player(player, Integer.toString(score), "EASY"));
        } else if (this.game.getLevel() == MEDIUM) {
            database.insertScore(new Player(player, Integer.toString(score), "MEDIUM"));
        } else if (this.game.getLevel() == GOODLUCK) {
            database.insertScore(new Player(player, Integer.toString(score), "GOODLUCK"));
        } else {
            System.err.println("Not yet implemented !");
        }
    }
}
