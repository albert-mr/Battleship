package business.setup;

import business.entities.Board;
import business.entities.Game;
import business.entities.Ship;

import java.util.Arrays;

public class SetupGameModel {

    private Game game;

    public SetupGameModel() {
        game = new Game();
    }


    public void resetModel(){
        this.game = new Game();
    }
    public Game getGame() {
        return game;
    }

    public boolean startGame() {
        Ship[] ships = game.getPlayer().getShips();
        for (Ship ship : ships) {
            if (!ship.isUsed()) {
                return false;
            }
        }
        return true;
    }

    public void setGame(int num, String name){
        game.setGameName(name);
        this.game.setGame(num);
    }

    public boolean shipCount(String name) {
        Ship[] ships = game.getPlayer().getShips();
        int shipType = getShipType(name);
        if (shipType == -1) {
            System.out.println("Invalid ship name");
            return false;
        } else if (ships[shipType].isUsed()) {
            System.out.println("No more ships of that size");
            return false;
        } else {
            return true;
        }
    }

    public boolean placeShip(int x, int y, int orientation, int size, String name) { //orientation: 1 for up, 2 for down, 3 for left, 4 for right
        int shipType = getShipType(name);
        Board board = new Board();
        board.copyBoard(game.getPlayer().getBoard());
        Ship[] ships = game.getPlayer().getShips();
        ships[shipType].setOrientation(orientation);
        ships[shipType].setX(x);
        ships[shipType].setY(y);
        for(int i = 0; i<size; i++) {
            if (!game.getPlayer().getBoard().checkBordersForPlace(x, y)) {
                return false;
            }
            board.addShip(x, y);
            switch (orientation) {
                case 1 -> x--;
                case 2 -> x++;
                case 3 -> y--;
                case 4 -> y++;
            }
        }
        game.getPlayer().setBoard(board);
        ships[shipType].setIsUsed(true);
        game.getPlayer().setShips(ships);
        return true;
    }
    public boolean shipIsUsed(String name){
        int shipType = getShipType(name);
        return(game.getPlayer().getShips()[shipType].isUsed());
    }


    public int[] removeShip(String name) {
        int[] pos = new int[5];
        Ship[] ships = game.getPlayer().getShips();
        int shipType = getShipType(name);
        pos[0] = ships[shipType].getX();
        pos[1] = ships[shipType].getY();
        switch(ships[shipType].getOrientation()){
            case 1:
                pos[2] = -1;
                pos[3] = 0;
                break;
            case 2:
                pos[2] = 1;
                pos[3] = 0;
                break;
            case 3:
                pos[2] = 0;
                pos[3] = -1;
                break;
            case 4:
                pos[2] = 0;
                pos[3] = 1;
                break;
        }
        pos[4] = ships[shipType].getSize();
        for (int i = 0; i < ships[shipType].getSize(); i++) {
            switch(ships[shipType].getOrientation()){
                case 1 -> game.getPlayer().getBoard().deleteShip(pos[0] - i,pos[1]);
                case 2 -> game.getPlayer().getBoard().deleteShip(pos[0] + i, pos[1]);
                case 3 -> game.getPlayer().getBoard().deleteShip(pos[0],pos[1] - i);
                case 4 -> game.getPlayer().getBoard().deleteShip(pos[0] ,pos[1] + i);
            }
        }
        game.getPlayer().getShips()[shipType].setIsUsed(false);
        return pos;
    }

    public int getShipSize(String name) {
        return switch (name) {
            case "Speedboat" -> 2;
            case "Submarine1" -> 3;
            case "Submarine2" -> 3;
            case "Destroyer" -> 4;
            case "Aircraft Carrier" -> 5;
            default -> -1;
        };
    }

    public int getShipType(String name) {
        return switch (name) {
            case "Speedboat" -> 0;
            case "Submarine1" -> 1;
            case "Submarine2" -> 2;
            case "Destroyer" -> 3;
            case "Aircraft Carrier" -> 4;
            default -> -1;
        };
    }

    public boolean isShip(String name){
        String[] ships = {"Speedboat", "Submarine1", "Submarine2", "Destroyer", "Aircraft Carrier"};
        return (Arrays.asList(ships).contains(name));
    }

    public int[] shipOrientation(int x, int y, int r, int c){
        int[] orientation = new int[2];
        int k = x - r;
        int l = y - c;
        if (l > 0 && r < r + l && r >= r - l + 1) {
            orientation[1] = -1;
            return orientation;
        } else if (l < 0 && r > r + l && r <= r - l + 1) {
            orientation[1] = 1;
            return orientation;
        } else if(k > 0 &&  c < c + k && c >= c - k + 1){
            orientation[0] = -1;
            return orientation;
        } else if(k < 0 &&  c > c + k && c <= c - k + 1){
            orientation[0] = 1;
            return orientation;
        }
        orientation[0] = 1;
        return orientation;
    }

    public boolean inBounds(int x, int y, int size, int orientation){
        switch(orientation){
            case 1 -> {
                return x - size+1 >= 0;
            }
            case 2 -> {
                return x + size <= 15 ;
            }
            case 3 -> {
                return y - size+1 >= 0;
            }
            case 4 -> {
                return y + size <= 15 ;
            }
        }
        return true;
    }

    public boolean isPlaced(String text){ return text.equals("PLACE"); }
}
