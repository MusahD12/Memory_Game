package main.java.memory_game.database;


import main.java.memory_game.models.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighscoreDB {

    public static final String HIGHSCORE_DB = "src/main/java/memory_game/database/database.csv";

    public HighscoreDB() {
    }

    public Player[] getAllScore() {
        String line = "";
        List<Player> players = new ArrayList<Player>();
        // Retrieve all players in the memory_game.database (file memory_game.database.csv)

        File file = new File(HIGHSCORE_DB);
        System.out.println(file.exists());
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] l = line.split(";");
                System.out.println(l[0]);
                System.out.println(l[1]);
                players.add(new Player(l[0], l[1]));
            }
            // Sort the list of players by score
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player player1, Player player2) {
                    return player1.score > player2.score ? -1
                            : player1.score < player2.score ? 1
                            : 0;
                }
            });
            // to transform list to array (cause we can only sort with a list) (avoid implementation of our own algorithm to sort)
            return players.stream().toArray(Player[]::new);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return players.stream().toArray(Player[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return players.stream().toArray(Player[]::new);
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
