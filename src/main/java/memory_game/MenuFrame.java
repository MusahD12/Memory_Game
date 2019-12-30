package main.java.memory_game;

import main.java.memory_game.models.Board;
import main.java.memory_game.models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static main.java.memory_game.models.Level.*;
import static main.java.memory_game.models.OpponentType.CPU;
import static main.java.memory_game.models.OpponentType.HUMAN;

public class MenuFrame {

    JLabel  setDifficulty;
    JLabel players;
    JTextField namePlayer1;
    JTextField namePlayer2;
    JLabel oponnent;
    JButton about;
    JButton highscore;
    JButton start;
    JRadioButton b1;
    JRadioButton  b2;
    JRadioButton b3;
    JRadioButton  b4;
    JRadioButton  b5;
    ButtonGroup g1;
    ButtonGroup g2;
    JPanel menu;
    JPanel menu2;
    JPanel menu3;
    JPanel menu4;
    JFrame f;

    // Set Up Configuration Game

    public MenuFrame (){
        f = new JFrame("Menu");
        menu = new JPanel();
        menu2 = new JPanel();
        menu3 = new JPanel();
        menu4 = new JPanel();
        //menu = new JLabel("Game Setting",JLabel.RIGHT);
        players = new JLabel("Player:", JLabel.LEFT);
        namePlayer1 = new JTextField();
        namePlayer2 = new JTextField("CPU");
        setDifficulty= new JLabel("Difficulty:", JLabel.LEFT);
        oponnent = new JLabel("Select oponnent:",JLabel.LEFT);
        about = new JButton("About Game");
        //about.setBounds(10,10,20,20);
        //about.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        highscore = new JButton("High Score");
        start = new JButton("Start");
        g1 = new ButtonGroup();
        g2 = new ButtonGroup();
        b1 = new JRadioButton ("Easy", true);
        b2 = new JRadioButton ("Medium");
        b3 = new JRadioButton ("Goodluck");
        g1.add(b1);
        g1.add(b2);
        g1.add(b3);
        b4 = new JRadioButton ("Computer",true);
        b5 = new JRadioButton ("Another Player");
        g2.add(b4);
        g2.add(b5);
        b5.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == 1 ){
                    menu.add(namePlayer2);
                    f.pack();
                } else {
                    menu.remove(namePlayer2);
                    f.pack();
                }
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Game g;
                f.pack();
                if(b1.isSelected() && b4.isSelected())
                    g = new Game(EASY,CPU);
                else if(b2.isSelected() && b4.isSelected())
                    g = new Game(MEDIUM,CPU);
                else if(b3.isSelected() && b4.isSelected())
                    g = new Game(GOODLUCK,CPU);
                else if(b1.isSelected() && b5.isSelected())
                    g = new Game(GOODLUCK,HUMAN);
                else if(b1.isSelected() && b5.isSelected())
                    g = new Game(GOODLUCK,HUMAN);
                else if(b1.isSelected() && b5.isSelected())
                    g = new Game(GOODLUCK,HUMAN);
                else
                    g = new Game(EASY,CPU);

                new GameFrame(g);
            }
        });
        start.addMouseListener(new MouseClicked());

        highscore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new HighScoreFrame();
            }
        });

        f.add(menu);
        f.add(menu2);
        f.add(menu3);
        f.add(menu4);
        menu.add(players);
        menu.add(namePlayer1);
        menu2.add(setDifficulty);
        menu2.add(b1);
        menu2.add(b2);
        menu2.add(b3);
        menu3.add(oponnent);
        menu3.add(b4);
        menu3.add(b5);
        menu4.add(about);
        menu4.add(highscore);
        menu4.add(start);
        menu.setLayout(new GridLayout(1, 2));
        menu2.setLayout(new GridLayout(1,4));
        menu4.setLayout(new GridLayout(1,3));
        menu3.setLayout(new GridLayout(1,3));
        //f.setSize(400,200);
        f.setLayout(new GridLayout(4,1));
        f.pack();
        f.setVisible(true);
    }
}
