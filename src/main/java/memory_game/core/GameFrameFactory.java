package main.java.memory_game.core;

import main.java.memory_game.models.Game;
import main.java.memory_game.models.OpponentType;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.TimerTask;

/**
 * This is the main factory, this factory as the other one (GameFactory) will create a specific board depending on the ennemy.
 * this factory is use just for the user interface, you can add specific user interface related to the board.
 */
public abstract class GameFrameFactory extends JFrame {

    private final GameFactory gameStrategy;
    public Game game;

    /**
     * ************************************
     **/

    private ImageIcon m1 = new ImageIcon(this.getClass().getResource("../Icons/m1.jpg"));
    static ImageIcon[] icons;
    private static ImageIcon icon;
    private Random rnd;

    static JLabel user1;
    static JLabel labelScoreP1;
    static JLabel scoreBoardP1;
    static JLabel clockP1;

    static JFrame f;
    static Timer timer;
    public static int seconds;
    static JButton[] buttons;

    private static String[] pics = {"../Icons/m2.jpg", "../Icons/m3.jpg", "../Icons/m4.jpg", "../Icons/m5.jpg",
            "../Icons/m6.jpg", "../Icons/m7.jpg", "../Icons/m8.jpg", "../Icons/m9.jpg", "../Icons/m10.jpg",
            "../Icons/m11.jpg", "../Icons/m12.jpg", "../Icons/m13.jpg", "../Icons/m14.jpg", "../Icons/15.jpg",
            "../Icons/16.jpg", "../Icons/m17.jpg", "../Icons/m18.jpg", "../Icons/m19.jpg"};
    private int nbButton;

    /**
     * ************************************
     **/

    public GameFrameFactory(Game game) {
        this.game = game;
        this.nbButton = this.game.getBoard().getColumns() * this.game.getBoard().getRows();
        this.gameStrategy = GameFactory.getGameStrategy(this.game);
        this.createButtons();
        this.startTimer();
    }


    public static GameFrameFactory getGameFrame(Game game) {

        if (game.getOpponentType() == OpponentType.CPU)
            return new GameFrameCPU(game);
        else if (game.getOpponentType() == OpponentType.HUMAN) {
            return new GameFrameHUMAN(game);
        } else {
            System.err.println("This game strategy is not implemented yet !");
            return null;
        }
    }

    public void createButtons() {
        int nbButton = this.game.getBoard().getColumns() * this.game.getBoard().getRows();
        icons = new ImageIcon[nbButton];
        buttons = new JButton[nbButton];
        JPanel map = new JPanel();
        map.setLayout(new GridLayout(this.game.getBoard().getRows(), this.game.getBoard().getColumns(), 10, 10));
        map.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        map.setBackground(Color.BLACK);
        for (int i = 0, j = 0; i < nbButton / 2; i++) {
            buttons[j] = new JButton("");
            icons[j] = new ImageIcon(this.getClass().getResource(pics[i]));
            buttons[j].setIcon(m1);
            buttons[j].addActionListener(this.gameStrategy );
            buttons[j].setBackground(Color.BLACK);
            map.add(buttons[j++]);

            icons[j] = icons[j - 1];
            buttons[j] = new JButton("");
            buttons[j].setIcon(m1);
            buttons[j].addActionListener(this.gameStrategy);
            buttons[j].setBackground(Color.BLACK);
            map.add(buttons[j++]);

        }
        rnd = new Random();
        for (int i = 0; i < nbButton; i++) {
            int j = rnd.nextInt(nbButton);
            icon = icons[i];
            icons[i] = icons[j];
            icons[j] = icon;
        }

        add(map);
        pack();


    }

    //restart method
    public void restart() {

        random();
        this.game.setScorePlayer1(0);
        scoreBoardP1.setText(Integer.toString(this.game.getScorePlayer1()));
        clockP1.setText(Integer.toString(seconds = 0));


    }

    // random shuffle method
    private void random() {
        for (int i = 0; i < nbButton; i++) {
            buttons[i].setIcon(m1);
            buttons[i].setEnabled(true);
        }
        for (int i = 0; i < nbButton; i++) {
            int j = rnd.nextInt(nbButton);
            icon = icons[i];
            icons[i] = icons[j];
            icons[j] = icon;
        }
    }

    public abstract void createScoreBoard();

    public void startTimer() {
        java.util.Timer timer2 = new java.util.Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                clockP1.setText(Integer.toString(seconds));
                seconds++;
            }
        };
        timer2.scheduleAtFixedRate(task, 1000, 1000);
    }

}
