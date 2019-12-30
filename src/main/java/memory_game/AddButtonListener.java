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
    //static int score;
    public static int scoreC;
    Game game;
    GameFrame gameFrame;
    Board board;
    HighscoreDB database;
    Player p1;
    Player p2;
    Player p3;


    public void actionPerformed(ActionEvent e) {
        database = new HighscoreDB();
        p1 = new Player("Your highest score in easy: ", scoreBoard2.getText());
        p2 = new Player("Your highest score in medium: ", scoreBoard2.getText());
        p3 = new Player("Your highest score in Goodluck: ", scoreBoard2.getText());
        int nButton = Board.columns * Board.rows;
        int z = 400 - 2 * openImages;
        if (nButton == 24 || nButton == 16){
            z =  400 - (2 * seconds) - (2*openImages);
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
/*
                if (nButton == 16){
                    this.gameFrame.random2();
                }

 */



            }
            else {
                buttons[clicks].setEnabled(false);
                buttons[clicks].setDisabledIcon(icons[clicks]);
                buttons[oddClicks].setEnabled(false);
                buttons[oddClicks].setDisabledIcon(icons[oddClicks]);
                score++;
                scoreC++;
                System.out.println(scoreC);
                scoreBoard2.setText(Integer.toString(score*z));
                if (scoreC == nButton/2 && nButton == 20) {
                    scoreBoard.setText(" You won!");
                    JOptionPane.showMessageDialog(f, "You won!");
                    database.insertScore(p1);
                }
                else if (scoreC == nButton/2 && nButton == 24) {
                    scoreBoard.setText(" You won!");
                    JOptionPane.showMessageDialog(f, "You won!");
                    database.insertScore(p2);
                }
                else if (scoreC == nButton/2 && nButton == 16) {
                    scoreBoard.setText(" You won!");
                    JOptionPane.showMessageDialog(f, "You won!");
                    database.insertScore(p3);
                }
                if (nButton == 16){
                    if  (icons[oddClicks] == icons[2]){
                        score++;
                    }
                }


            }

        }
        else {
            oddClicks = clicks;


        }




    }


}
