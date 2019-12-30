package main.java.memory_game.database;


import main.java.memory_game.models.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighscoreDB {

    private static final String HIGHSCORE_DB = "src/main/java/memory_game/database/database.csv";

    public HighscoreDB() {
    }

    public Player[] getAllScore() {
        String line;
        List<Player> players = new ArrayList<>();
        // Retrieve all players in the memory_game.database (file memory_game.database.csv)

        File file = new File(HIGHSCORE_DB);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] l = line.split(";");
                System.out.println(l[0]);
                System.out.println(l[1]);
                players.add(new Player(l[0], l[1]));
            }
            // Sort the list of players by score
            players.sort((player1, player2) -> Integer.compare(player2.score, player1.score));
            // Transform list to array since we can only sort with a list
            return players.toArray(new Player[0]);

        } catch (IOException e) {
            e.printStackTrace();
            return players.toArray(new Player[0]);
        }

    }

    /**
     * Insert score with player name in the highscore board
     * @param player
     */
    public void insertScore(Player player) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_DB));
            String line = player.name.concat(";").concat(Integer.toString(player.score));
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
