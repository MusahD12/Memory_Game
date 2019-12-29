package main.java.memory_game.models;

public class Board {

    public static int rows;
    public static int columns;
    public int interval;

    public Board(int rows, int columns) {
        Board.rows = rows;
        Board.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
