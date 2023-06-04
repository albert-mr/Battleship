package business.setup;

import business.entities.Board;
import business.entities.Player;
import business.entities.Ship;

import java.util.Random;

public class SetupAIGameModel {

    private final Player[] players;
    public SetupAIGameModel(Player[] players) {
        this.players = players;
    }

    public Player[] getAIPlayers() {
        return players;
    }

    public Player[] createSetup() {
        for (Player player : players) {
            placeAllShips(player);
        }
        return players;
    }

    public void placeAllShips(Player player) {
        Ship[] ships = player.getShips();
        for (int i = 0; i<ships.length; i++) {
            player = placeAIShip(player, ships[i], i);
        }
    }

    public boolean checkIfShipCanBePlacedOutOfBounds(int x, int y, int orientation, int size) {
        if (orientation == 1) {
            return x + size <= 14;
        } else if (orientation == 2) {
            return y + size <= 14;
        } else if (orientation == 3) {
            return x - size >= 0;
        } else if (orientation == 4) {
            return y - size >= 0;
        }
        return true;
    }

    public boolean checkIfShipCanBePlacedOnAnotherShip(Board board, int x, int y, int orientation, int size) {
        for (int i = 0; i < size; i++) {
            if (!board.checkBordersForPlace(x, y)) {
                return false;
            }
            switch (orientation) {
                case 1 -> x--;
                case 2 -> x++;
                case 3 -> y--;
                case 4 -> y++;
            }
        }
        return true;
    }

    public Player placeAIShip(Player player, Ship ship, int position) {
        boolean foundAPosition = false;
        int x = 0, y = 0, orientation = 0;
        Random rand = new Random();
        Board board = new Board();
        board.copyBoard(player.getBoard());

        int size = ship.getSize();
        while (!foundAPosition) {
            x = rand.nextInt(0,14);
            y = rand.nextInt(0,14);
            orientation = rand.nextInt(1, 4);
            System.out.println("x: " + x + " y: " + y + " orientation: " + orientation + " size: " + size);
            if (checkIfShipCanBePlacedOutOfBounds(x, y, orientation, size) && checkIfShipCanBePlacedOnAnotherShip(board, x, y, orientation, size)) {
                System.out.println("Found a position after x: " + x + " y: " + y + " orientation: " + orientation + " size: " + size);
                foundAPosition = true;
                for (int i = 0; i < size; i++) {
                    if (orientation == 1) { //up
                        board.getGrid()[x - i][y] = 1;
                    } else if (orientation == 2) { //down
                        board.getGrid()[x + i][y] = 1;
                    } else if (orientation == 3) { //left
                        board.getGrid()[x][y - i] = 1;
                    } else if (orientation == 4) { //right
                        board.getGrid()[x][y + i] = 1;
                    }
                }
            }
        }
        player.setBoard(board);
        player.getShips()[position].setOrientation(orientation);
        player.getShips()[position].setX(x);
        player.getShips()[position].setY(y);
        player.getShips()[position].setIsUsed(true);
        return player;
    }

    public void print2D(int[][] mat) {
        for (int[] booleans : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(booleans[j] + " ");
            }
            System.out.println();
        }
    }

    public void printeo(Player[] players) {
        Player player = players[0];
        int[][] grid;
        placeAllShips(player);
        grid = player.getBoard().getGrid();
        print2D(grid);
        System.out.println("\n/////////////////////////////////////////////////////////////");

    }
}

