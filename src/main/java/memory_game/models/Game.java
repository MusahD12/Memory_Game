package main.java.memory_game.models;

import static main.java.memory_game.models.Level.EASY;
import static main.java.memory_game.models.Level.GOODLUCK;
import static main.java.memory_game.models.Level.MEDIUM;

// Game creation based on settings

/**
 * Builder pattern is used to simplify the creation of Game's object.
 * With this pattern we can build multiple game we want, just by setting the builder see MenuFrame.java to understand.
 */
public class Game {

    private Level level;
    private OpponentType opponentType;
    private Board board;
    private String player1;
    private String player2;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;

    public int getCurrentUser() {
        return currentUser;
    }

    private int currentUser = 1;

    public void changeUser() {
        if (currentUser == 1) this.currentUser = 2;
        else this.currentUser = 1;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    public static class GameBuilder {

        private Level level;
        private OpponentType opponentType;
        private Board board;
        private String player1;
        private String player2;

        public GameBuilder() {
        }

        public GameBuilder addDefaultPlayer(String namePlayer1) {
            this.player1 = namePlayer1;
            return this;
        }

        public GameBuilder addCPUPlayer() {
            this.opponentType = OpponentType.CPU;
            return this;
        }

        public GameBuilder addHUMANPlayer(String namePlayer2) {
            this.opponentType = OpponentType.HUMAN;
            this.player2 = namePlayer2;
            return this;
        }

        public GameBuilder easyLevel() {
            this.level = EASY;
            this.board = new Board(4, 5);
            return this;
        }

        public GameBuilder mediumLevel() {
            this.level = MEDIUM;
            this.board = new Board(6, 4);

            return this;
        }

        public GameBuilder goodLuckLevel() {
            this.level = GOODLUCK;
            this.board = new Board(4, 4);
            return this;
        }

        public Game build() {
            Game game = new Game();
            game.board = this.board;
            game.opponentType = this.opponentType;
            game.level = this.level;
            game.player2 = this.player2;
            game.player1 = this.player1;
            return game;
        }
    }

    private Game() {
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public OpponentType getOpponentType() {
        return opponentType;
    }

    public void setOpponentType(OpponentType opponentType) {
        this.opponentType = opponentType;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }
}