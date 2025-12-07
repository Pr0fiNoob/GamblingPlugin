package net.profinoob.gamblingPlugin.games;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

    // Variables
    private final Map<String, Game> games = new HashMap<>();

    // Methods
    public void register(Game game) {
        games.put(game.getName().toLowerCase(), game);
    }

    public Game get(String name) {
        return games.get(name.toLowerCase());
    }

    public Map<String, Game> getAll() {
        return games;
    }

}
