package main.java.memory_game;

import main.java.memory_game.core.MenuFrame;

import javax.swing.*;

public class AnimeMemoryGame {
    public static void main (String [] args){
        SwingUtilities.invokeLater(MenuFrame::new);
    }
}
