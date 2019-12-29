package main.java.memory_game.models;

public class Game {
    public Level level;
    public OpponentType opponentType;
    public Board board;
    public String player1;
    public String player2;

    public Game(Level level, OpponentType opponentType) {
        switch (level) {
            case EASY:
                this.board = new Board(4,4);
                break;
            case MEDIUM:
                this.board = new Board(6, 4);
                break;
            case GOODLUCK:
                this.board = new Board(4, 5);
                break;
        }
        //switch (opponentType);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
