package ach7nbh2game.server.map;

import ach7nbh2game.main.Constants.Directions;
import ach7nbh2game.server.map.components.Ground;
import ach7nbh2game.server.map.components.IMapComponent;
import ach7nbh2game.server.map.components.Player;
import ach7nbh2game.server.map.components.Wall;
import com.googlecode.blacken.grid.Grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Map {

    private Grid<IMapComponent> grid;
    private int height;
    private int width;

    private java.util.Map<Integer, Player> players;

    private Random rand;

    public Map (int heightIn, int widthIn) {

        height = heightIn;
        width = widthIn;

        grid = new Grid<IMapComponent>(new Ground(), height, width);
        grid.clear();

        players = new HashMap<Integer, Player>();

        rand = new Random();

        for (int i = 0; i < height * width * 0.05; i++) {
            int y = rand.nextInt(height);
            int x = rand.nextInt(width);
            grid.set(y, x, new Wall());
        }

    }

    public void addNewPlayer (int playerID) {

        int y = rand.nextInt(height);
        int x = rand.nextInt(width);
        Player newPlayer = new Player(playerID, y, x);
        players.put(playerID, newPlayer);
        grid.set(y, x, newPlayer);

    }

    public ArrayList<ArrayList<Integer>> getMapView (int x, int y, int height, int width) {

        ArrayList<ArrayList<Integer>> mapView = new ArrayList<ArrayList<Integer>>();
        for (int j = y; j < y + height; j++) {
            ArrayList<Integer> newRow = new ArrayList<Integer>();
            for (int i = x; i < x + width; i++) {
                newRow.add(grid.get(j, i).getMapChar());
            }
            mapView.add(newRow);
        }
        return mapView;

    }

    public void move (int playerID, Directions direction) {

        if (players.containsKey(playerID)) {

            Player player = players.get(playerID);
            int curX = player.getX();
            int curY = player.getY();

            int newX = curX;
            int newY = curY;
            switch (direction) {
                case UP:
                    newX = curX;
                    newY = curY - 1;
                    break;
                case DOWN:
                    newX = curX;
                    newY = curY + 1;
                    break;
                case LEFT:
                    newX = curX - 1;
                    newY = curY;
                    break;
                case RIGHT:
                    newX = curX + 1;
                    newY = curY;
                    break;
            }

            if (grid.get(newY, newX) instanceof Ground) {
                player.setY(newY);
                player.setX(newX);
                grid.set(newY, newX, player);
                grid.set(curY, curX, new Ground());
            }

        }

    }

}
