package ach7nbh2game.server;

import ach7nbh2game.main.Constants.Directions;
import ach7nbh2game.server.map.Map;

import java.util.ArrayList;
import java.util.Set;

class Game extends APlayerContainer {

    private Map map;

    public Game (Set<Integer> playerIDsIn, int height, int width) {

        playerIDs = playerIDsIn;

        map = new Map(height, width);

        for (int playerID : playerIDs) {
            map.addNewPlayer(playerID);
        }

    }

    public ArrayList<ArrayList<Integer>> getMapView (int clientID) {

        return map.getMapView(clientID);

    }

    public void move (int clientID, Directions direction) {

        map.move(clientID, direction);

    }

}
