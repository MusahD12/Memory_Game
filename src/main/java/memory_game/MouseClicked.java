package main.java.memory_game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import static main.java.memory_game.GameFrame.clock;
import static main.java.memory_game.GameFrame.seconds;
//setting up timer on scoreboard
public class MouseClicked implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Timer timer2 = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                clock.setText(Integer.toString(seconds));
                seconds++;
            }
        };
        timer2.scheduleAtFixedRate(task, 1000, 1000);

    }

    public void mousePressed(MouseEvent mouseEvent) {


    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


}



