package main.java.memory_game;

import javax.swing.*;

public class AnimeMemoryGame {
    public static void main (String [] args){
        SwingUtilities.invokeLater(new Runnable(){
           public void run(){
                //new GameFrame();
               new MenuFrame();
            }
        });
    }
}
