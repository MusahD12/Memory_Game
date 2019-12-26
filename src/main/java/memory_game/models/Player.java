package main.java.memory_game.models;

public class Player {
    public String name;
    public int score;

    public Player(String name, String score) {
        this.name = name;
        this.score = Integer.parseInt(score);
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
        return name +"\t"+ score;
    }
}
