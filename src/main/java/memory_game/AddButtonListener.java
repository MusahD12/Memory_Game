package main.java.memory_game;

import main.java.memory_game.GameFrame;
import main.java.memory_game.models.Board;
import main.java.memory_game.database.HighscoreDB;
import main.java.memory_game.models.Game;
import main.java.memory_game.models.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
   public static int oddClicks;
    public static int clicks;
    static int openImages;
    static int score;
    Game game;
    GameFrame gameFrame;
    Board board;


    //jeux se lance si tu remplace nButton par 16
    public void actionPerformed(ActionEvent e) {
        int nButton = Board.columns * Board.rows;;


        if (GameFrame.timer.isRunning())
            return;


        openImages++;
        System.out.println(openImages);
        System.out.println(GameFrame.timer.isRunning());
        System.out.println(clicks);

        for (int i = 0; i < nButton; i++) {
            if (e.getSource() == GameFrame.buttons[i]) {
                GameFrame.buttons[i].setIcon(GameFrame.icons[i]);
                clicks = i;
            }
        }
        if (openImages % 2 == 0) {
            if (clicks == oddClicks) {
                clicks--;
                return;
            }
            if (GameFrame.icons[clicks] != GameFrame.icons[oddClicks]) {
                GameFrame.timer.start();
            } else {
                score++;
                GameFrame.scoreBoard2.setText(Integer.toString(score * 100));
                if (score == nButton/2) {

                    GameFrame.scoreBoard.setText(" You won!");
                    JOptionPane.showMessageDialog(GameFrame.f, "You won!");
                    HighscoreDB database = new HighscoreDB();
                    Player p = new Player("Me", GameFrame.scoreBoard2.getText());
                    database.insertScore(p);
                }
            }
        } else {
            oddClicks = clicks;
        }



    }


}
