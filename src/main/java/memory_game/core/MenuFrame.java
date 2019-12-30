package main.java.memory_game.core;


import main.java.memory_game.models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;

public class MenuFrame {

    private JTextField namePlayer2;
    private JRadioButton b1;
    private JRadioButton b2;
    private JRadioButton b3;
    private JRadioButton b4;
    private JRadioButton b5;
    private JPanel menu;
    private JFrame f;

    // Set Up Configuration Game

    public MenuFrame() {
        f = new JFrame("Menu");
        menu = new JPanel();
        JPanel menu2 = new JPanel();
        JPanel menu3 = new JPanel();
        JPanel menu4 = new JPanel();
        JLabel players = new JLabel("Player:", JLabel.LEFT);
        JTextField namePlayer1 = new JTextField();
        namePlayer2 = new JTextField("CPU");
        JLabel setDifficulty = new JLabel("Difficulty:", JLabel.LEFT);
        JLabel oponnent = new JLabel("Select oponnent:", JLabel.LEFT);
        JButton about = new JButton("About Game");
        JButton highscore = new JButton("High Score");
        JButton start = new JButton("Start");
        ButtonGroup g1 = new ButtonGroup();
        ButtonGroup g2 = new ButtonGroup();
        b1 = new JRadioButton("Easy", true);
        b2 = new JRadioButton("Medium");
        b3 = new JRadioButton("Goodluck");
        g1.add(b1);
        g1.add(b2);
        g1.add(b3);
        b4 = new JRadioButton("Computer", true);
        b5 = new JRadioButton("Another Player");
        g2.add(b4);
        g2.add(b5);
        b5.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                menu.add(namePlayer2);
                f.pack();
            } else {
                menu.remove(namePlayer2);
                f.pack();
            }
        });

        start.addActionListener(actionEvent -> {
            Game.GameBuilder gb = new Game.GameBuilder();
            f.pack();
            if (b1.isSelected() && b4.isSelected())
                gb.addCPUPlayer().addDefaultPlayer(namePlayer1.getText()).easyLevel();
            else if (b2.isSelected() && b4.isSelected())
                gb.addCPUPlayer().addDefaultPlayer(namePlayer1.getText()).mediumLevel();
            else if (b3.isSelected() && b4.isSelected())
                gb.goodLuckLevel().addDefaultPlayer(namePlayer1.getText()).addCPUPlayer();
            else if (b1.isSelected() && b5.isSelected())
                gb.addHUMANPlayer(namePlayer2.getText()).addDefaultPlayer(namePlayer1.getText()).easyLevel();
            else if (b2.isSelected() && b5.isSelected())
                gb.addHUMANPlayer(namePlayer2.getText()).addDefaultPlayer(namePlayer1.getText()).mediumLevel();
            else if (b3.isSelected() && b5.isSelected())
                gb.addHUMANPlayer(namePlayer2.getText()).addDefaultPlayer(namePlayer1.getText()).goodLuckLevel();
            else
                gb.addCPUPlayer().addDefaultPlayer(namePlayer1.getText()).easyLevel();

            GameFrameFactory.getGameFrame(gb.build());
        });
        about.addActionListener(actionEvent -> {
            try {
                Desktop.getDesktop().open(new File(getClass().getResource("../assets/instructions.txt").getFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        highscore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        menu2.setLayout(new GridLayout(1, 4));
        menu4.setLayout(new GridLayout(1, 3));
        menu3.setLayout(new GridLayout(1, 3));
        f.setLayout(new GridLayout(4, 1));
        f.pack();
        f.setVisible(true);
    }
}
