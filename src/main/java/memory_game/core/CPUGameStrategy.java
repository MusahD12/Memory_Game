package main.java.memory_game.core;

import main.java.memory_game.models.Game;
import main.java.memory_game.models.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static main.java.memory_game.core.GameFrameFactory.*;
import static main.java.memory_game.models.Level.EASY;
import static main.java.memory_game.models.Level.GOODLUCK;
import static main.java.memory_game.models.Level.MEDIUM;

public class CPUGameStrategy extends GameFactory implements ActionListener {

    public CPUGameStrategy(Game game) {
        super(game);
        timer = new Timer(1000, actionEvent -> {
            buttons[clicks].setIcon(m1);
            buttons[oddClicks].setIcon(m1);
            timer.stop();

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int nButton =  this.game.getBoard().getRows() *  this.game.getBoard().getColumns();
        int z = 400 - 2 *  openImages;
        if (nButton == 24 || nButton == 16) {
            z = 400 - (2 * seconds) - (2 * openImages);
        }

        if (this.timer.isRunning())
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
//                if (this.game.getLevel() == GOODLUCK) {
//                    System.out.println("LEVEL GOODLUCK");
//                    Problem cause the object isn't the same ( but it's really the same )
//                    System.out.println(icons[clicks] == joker);
//                    if (icons[clicks] == joker || icons[oddClicks] == joker) {
//                        int oldScore = Integer.parseInt(scoreBoardP1.getText());
//                        System.out.println("Joker found " + oldScore);
//                        int newScore = oldScore - 200;
//                        System.out.println("Joker found " + newScore);
//                        scoreBoardP1.setText(Integer.toString(newScore));
//                    }
//                }
                timer.start();
            } else {
//                if (this.game.getLevel() == GOODLUCK) {
//                    if (icons[clicks] == joker) {
//                        int oldScore = Integer.parseInt(scoreBoardP1.getText());
//                        scoreBoardP1.setText(Integer.toString(oldScore + 1000));
//                    }
//                }

                buttons[clicks].setEnabled(false);
                buttons[clicks].setDisabledIcon(icons[clicks]);
                buttons[oddClicks].setEnabled(false);
                buttons[oddClicks].setDisabledIcon(icons[oddClicks]);


                this.game.setScorePlayer1(this.game.getScorePlayer1() + 1);
                System.out.println(this.game.getScorePlayer1());
                scoreBoardP1.setText(Integer.toString(this.game.getScorePlayer1() * z));
                if (this.game.getScorePlayer1() == nButton / 2) {
                    labelScoreP1.setText(" You won !");
                    JOptionPane.showMessageDialog(f, "You won!");
                    System.out.println(scoreBoardP1.getText());
                    if (this.game.getLevel() == EASY) {
                        database.insertScore(new Player(this.game.getPlayer1(), scoreBoardP1.getText(), "EASY"));
                    } else if (this.game.getLevel() == MEDIUM) {
                        database.insertScore(new Player(this.game.getPlayer1(), scoreBoardP1.getText(), "MEDIUM"));
                    } else if (this.game.getLevel() == GOODLUCK) {
                        database.insertScore(new Player(this.game.getPlayer1(), scoreBoardP1.getText(), "GOODLUCK"));
                    } else {
                        System.err.println("Not yet implemented !");
                    }
                }
            }
        }else {
            oddClicks = clicks;


        }
    }
}
