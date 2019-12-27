package main.java.memory_game;

import main.java.memory_game.database.HighscoreDB;
import main.java.memory_game.models.Board;
import main.java.memory_game.models.Game;
import main.java.memory_game.models.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameFrame extends JFrame {

    ImageIcon m1 = new ImageIcon(this.getClass().getResource("/main/java/memory_game/Icons/m1.jpg"));
    ImageIcon[] icons;
    ImageIcon icon;
    JButton x;
    Random rnd;
    int numOfClicks;
    int oddClicks;
    int clicks;
    int openImages;
    JLabel scoreBoard;
    JLabel scoreBoard2;
    JPanel scoring;
    JPanel map;
    JPanel buttonPnl;
    JButton restart;
    static JLabel number;
    JFrame f;
    Timer timer = null;
    static int score;
    static int score2;
    boolean gameOver;
    static JButton[] buttons;
    static String pics[] = {"Icons/m2.jpg", "Icons/m3.jpg", "Icons/m4.jpg", "Icons/m5.jpg",
            "Icons/m6.jpg", "Icons/m7.jpg", "Icons/m8.jpg", "Icons/m9.jpg", "Icons/m10.jpg",
            "Icons/m11.jpg", "Icons/m12.jpg", "Icons/m13.jpg","Icons/m14.jpg", "Icons/15.jpg",
            "Icons/16.jpg", "Icons/m17.jpg", "Icons/m18.jpg", "Icons/m19.jpg"};
    Game game;
    Board board;
    

    //Frame set up
    public GameFrame(Game game) {
        setTitle("Anime Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 400);
        this.game = game;
        createButtons();
        createScoreBoard();


    }

    //buttons
    public void createButtons() {
        int nbButton = this.game.board.columns * this.game.board.rows;
        icons = new ImageIcon[nbButton];
        buttons = new JButton[nbButton];
        map = new JPanel();
        map.setLayout(new GridLayout(this.game.board.rows, this.game.board.columns, 10, 10));
        map.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[clicks].setIcon(m1);
                buttons[oddClicks].setIcon(m1);
                timer.stop();

            }
        });

    }

    public void createScoreBoard() {
        score = 0;
        scoring = new JPanel();
        scoreBoard = new JLabel("Score");
        scoreBoard2 = new JLabel("" + score);
        scoreBoard2.setForeground(Color.RED);
        //scoreBoard.setFont(new Font("TimesRoman", Font.BOLD, 36));
        scoring.setBackground(Color.BLACK);
        //scoring.setLayout( new BorderLayout.EAST);
        scoring.setBorder(BorderFactory.createTitledBorder(LineBorder.createBlackLineBorder(), "ScoreBoard"));
        restart = new JButton("Restart");
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        scoring.add(scoreBoard);
        scoring.add(scoreBoard2);
        scoring.add(restart);

        add(scoring, BorderLayout.NORTH);

    }


    public void restart() {
        int nbButton = this.game.board.columns * this.game.board.rows;


        for (int i = 0; i < nbButton; i++) {
            buttons[i].setIcon(m1);
        }
        for (int i = 0; i < nbButton; i++) {
            int j = rnd.nextInt(nbButton);
            icon = icons[i];
            icons[i] = icons[j];
            icons[j] = icon;
        }
        scoreBoard2.setText(Integer.toString(score = 0));

    }
//J'ai pas su mettre addbuttonlistener dans une classe a part -> problem avec le timer
    class AddButtonListener implements ActionListener {
        int nbButton = this.game.board.columns * this.game.board.rows;
        //jeux se lance si tu remplace nbButton par 16
        public void actionPerformed(ActionEvent e) {
            if (timer.isRunning())
                return;

            openImages++;
            System.out.println(openImages);

            for (int i = 0; i < nbButton; i++) {
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
                    score++;
                    scoreBoard2.setText(Integer.toString(score * 100));
                    if (score == nbButton/2) {

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

            /*class TimerListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    buttons[clicks].setIcon(m1);
                    buttons[oddClicks].setIcon(m1);
                    timer.stop();

             */


        }


    }
}




