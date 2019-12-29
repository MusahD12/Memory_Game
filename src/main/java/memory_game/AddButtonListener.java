package main.java.memory_game;

import main.java.memory_game.GameFrame;
import main.java.memory_game.models.Board;
import main.java.memory_game.database.HighscoreDB;
import main.java.memory_game.models.Game;
import main.java.memory_game.models.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.java.memory_game.GameFrame.*;

public class AddButtonListener implements ActionListener {
   public static int oddClicks;
    public static int clicks;
    static int openImages;
    static int score;
    Game game;
    GameFrame gameFrame;
    Board board;


    public void actionPerformed(ActionEvent e) {
        int nButton = Board.columns * Board.rows;;

        if (timer.isRunning())
            return;


        openImages++;
        System.out.println(openImages);
        System.out.println(timer.isRunning());
        System.out.println(clicks);

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
            } else {
                buttons[clicks].setEnabled(false);
                buttons[clicks].setDisabledIcon(icons[clicks]);
                buttons[oddClicks].setEnabled(false);
                buttons[oddClicks].setDisabledIcon(icons[oddClicks]);
                score++;
                scoreBoard2.setText(Integer.toString(score * 100));
                if (score == nButton/2) {

                    scoreBoard.setText(" You won!");
                    JOptionPane.showMessageDialog(f, "You won!");
                    HighscoreDB database = new HighscoreDB();
                    Player p = new Player("Me", scoreBoard2.getText());
                    database.insertScore(p);
                }
            }
        } else {
            oddClicks = clicks;

        }



    }


}
