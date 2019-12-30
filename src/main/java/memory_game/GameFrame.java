package main.java.memory_game;

import main.java.memory_game.models.Board;
import main.java.memory_game.models.Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//import static main.java.memory_game.TimePassed.seconds;

public class GameFrame extends JFrame {

    ImageIcon m1 = new ImageIcon(this.getClass().getResource("Icons/m1.jpg"));
    ImageIcon joker = new ImageIcon(this.getClass().getResource("Icons/m4.jpg"));
    public static ImageIcon[] icons;
    public static ImageIcon icon;
    JButton x;
    Random rnd;
    int numOfClicks;
   // public int oddClicks;
   // public int clicks;
    public int openImages;
    public static JLabel scoreBoard;
    public static JLabel scoreBoard2;
    JLabel time;
    public static JLabel clock;
    JButton settings;
    JPanel scoring;
    JPanel map;
    JPanel buttonPnl;
    JButton restart;
    static JLabel number;
    public static JFrame f;
    public static Timer timer;
    static int score;
    public static int seconds;
    static int score2;
    boolean gameOver;
    public static JButton[] buttons;
   public static String[] pics = {"Icons/m2.jpg", "Icons/m3.jpg", "Icons/m4.jpg", "Icons/m5.jpg",
            "Icons/m6.jpg", "Icons/m7.jpg", "Icons/m8.jpg", "Icons/m9.jpg", "Icons/m10.jpg",
            "Icons/m11.jpg", "Icons/m12.jpg", "Icons/m13.jpg", "Icons/m14.jpg", "Icons/15.jpg",
            "Icons/16.jpg", "Icons/m17.jpg", "Icons/m18.jpg", "Icons/m19.jpg"};
    Game game;
    Board board;
    AddButtonListener but;
    MenuFrame menu;



    //Frame set up
    public GameFrame(Game game) {
        setTitle("Anime Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //setBackground(Color.BLACK);
        //setSize(400, 400);
        this.game = game;
        createButtons();
        createScoreBoard();
    }

    //buttons
    public void createButtons() {
        int nbButton = Board.columns * Board.rows;
        icons = new ImageIcon[nbButton];
        buttons = new JButton[nbButton];
        map = new JPanel();
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
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[AddButtonListener.clicks].setIcon(m1);
                buttons[AddButtonListener.oddClicks].setIcon(m1);
                timer.stop();

            }
        });

    }

    public void createScoreBoard() {
        score = 0;
        seconds = 0;
        scoring = new JPanel();
        scoreBoard = new JLabel("Score");
        scoreBoard2 = new JLabel("" + score);
        time = new JLabel("Time passed: ");
        clock = new JLabel("Time passed: "+ seconds);
        //clock.setText("Time passed: " );
        scoreBoard2.setForeground(Color.RED);
        clock.setForeground(Color.WHITE);
        clock.setText(Integer.toString(seconds));
        //scoreBoard.setFont(new Font("TimesRoman", Font.BOLD, 36));
        scoring.setBackground(Color.BLACK);
        //scoring.setLayout( new BorderLayout.EAST);
        scoring.setBorder(BorderFactory.createTitledBorder(LineBorder.createBlackLineBorder(), "ScoreBoard"));
        restart = new JButton("Restart");
        restart.setBackground(Color.RED);
        restart.setForeground(Color.BLACK);
        /*
        settings = new JButton("Settings");
        settings.setBackground(Color.RED);
        settings.setForeground(Color.BLACK);
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menu.f.toFront();

            }
        });

         */


        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart();
                //createButtons();
            }
        });
        scoring.add(scoreBoard);
        scoring.add(scoreBoard2);
        scoring.add(restart);
        //scoring.add(settings);
        scoring.add(time);
        scoring.add(clock);

        add(scoring, BorderLayout.NORTH);

    }

    public void restart() {
        int nbButton = Board.columns * Board.rows;



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
        scoreBoard2.setText(Integer.toString(score = 0));
        clock.setText(Integer.toString(seconds = 0));
        score = 0;
        AddButtonListener.scoreC= 0;


    }


    public void restart2(){

       removeAll();
       setVisible(false);
       createButtons();
       setVisible(true);


    }


}








