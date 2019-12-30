package main.java.memory_game.models;

public class Player {
    public String name;
    public int score;
    public String level;

    public Player(String name, String score, String level) {
        this.name = name;
        this.score = Integer.parseInt(score);
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " | " + score + " | " + level ;
    }

    public String formatToSave(){
        return name+";"+score+";"+level;
    }

}
