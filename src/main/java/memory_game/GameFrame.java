package main.java.memory_game;

import main.java.memory_game.models.Board;
import main.java.memory_game.models.Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;


public class GameFrame extends JFrame {

    private ImageIcon m1 = new ImageIcon(this.getClass().getResource("Icons/m1.jpg"));
    ImageIcon joker = new ImageIcon(this.getClass().getResource("Icons/m4.jpg"));
    static ImageIcon[] icons;
    private static ImageIcon icon;
    private Random rnd;
    static JLabel scoreBoard;
    static JLabel scoreBoard2;
    static JLabel clock;
    static JFrame f;
    static Timer timer;
    static int score;
    static int seconds;
    static JButton[] buttons;
    private static String[] pics = {"Icons/m2.jpg", "Icons/m3.jpg", "Icons/m4.jpg", "Icons/m5.jpg",
            "Icons/m6.jpg", "Icons/m7.jpg", "Icons/m8.jpg", "Icons/m9.jpg", "Icons/m10.jpg",
            "Icons/m11.jpg", "Icons/m12.jpg", "Icons/m13.jpg", "Icons/m14.jpg", "Icons/15.jpg",
            "Icons/16.jpg", "Icons/m17.jpg", "Icons/m18.jpg", "Icons/m19.jpg"};
    private Game game;
    private int nbButton = Board.columns * Board.rows;



    //Frame set up
    GameFrame(Game game) {
        setTitle("Anime Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.game = game;
        createButtons();
        createScoreBoard();
    }

    //Tile creation
    private void createButtons() {
        int nbButton = Board.columns * Board.rows;
        icons = new ImageIcon[nbButton];
        buttons = new JButton[nbButton];
        JPanel map = new JPanel();
        map.setLayout(new GridLayout(Board.rows, Board.columns, 10, 10));
        map.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        map.setBackground(Color.BLACK);
        for (int i = 0, j = 0; i < nbButton / 2; i++) {
            buttons[j] = new JButton("");
            icons[j] = new ImageIcon(this.getClass().getResource(pics[i]));
            buttons[j].setIcon(m1);
            buttons[j].addActionListener(new AddButtonListener());
            buttons[j].setBackground(Color.BLACK);
            map.add(buttons[j++]);

            icons[j] = icons[j - 1];
            buttons[j] = new JButton("");
            buttons[j].setIcon(m1);
            buttons[j].addActionListener(new AddButtonListener());
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
        timer = new Timer(1000, actionEvent -> {
            buttons[AddButtonListener.clicks].setIcon(m1);
            buttons[AddButtonListener.oddClicks].setIcon(m1);
            timer.stop();

        });

    }
// Setting up scoreboard
    private void createScoreBoard() {
        score = 0;
        seconds = 0;
        JPanel scoring = new JPanel();
        scoreBoard = new JLabel("Score");
        scoreBoard2 = new JLabel("" + score);
        JLabel time = new JLabel("Time passed: ");
        clock = new JLabel("Time passed: "+ seconds);
        scoreBoard2.setForeground(Color.RED);
        clock.setForeground(Color.WHITE);
        clock.setText(Integer.toString(seconds));
        scoring.setBackground(Color.BLACK);
        scoring.setBorder(BorderFactory.createTitledBorder(LineBorder.createBlackLineBorder(), "ScoreBoard"));
        JButton restart = new JButton("Restart");
        restart.setBackground(Color.RED);
        restart.setForeground(Color.BLACK);



        restart.addActionListener(e -> restart());
        scoring.add(scoreBoard);
        scoring.add(scoreBoard2);
        scoring.add(restart);
        scoring.add(time);
        scoring.add(clock);
        add(scoring, BorderLayout.NORTH);

    }
//restart method
    private void restart() {

        random();
        scoreBoard2.setText(Integer.toString(score = 0));
        clock.setText(Integer.toString(seconds = 0));
        score = 0;
        AddButtonListener.scoreC= 0;


    }
    // random shuffle method
    private void random(){
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


    public void random2(){
        for (int i = 0; i < nbButton; i++) {
            buttons[i].setIcon(icons[i]);}

        for (int i = 0; i < nbButton; i++) {
            buttons[i].setIcon(m1);

        }
        for (int i = 0; i < nbButton; i++) {
            buttons[i].setIcon(icons[i]);}

        for (int i = 0; i < nbButton; i++) {
            int j = rnd.nextInt(nbButton);
            icon = icons[i];
            icons[i] = icons[j];
            icons[j] = icon;
        }
    }

}











