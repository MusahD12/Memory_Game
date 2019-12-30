package main.java.memory_game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import static main.java.memory_game.GameFrame.clock;
import static main.java.memory_game.GameFrame.seconds;

public class MouseClicked implements MouseListener {
    Timer timer2;
    TimerTask task;

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        timer2 = new Timer();
        task = new TimerTask() {
            @Override
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



