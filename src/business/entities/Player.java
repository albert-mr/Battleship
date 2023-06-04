package business.entities;

public class Player {
    private String name;
    private Board board;
    private Ship[] ships;
    private int score;
    private int lives;
    private boolean isAlive;

    public Player() {
        this.name = "";
        this.score = 0;
        this.board = new Board();
        this.ships = new Ship[5];
        ships[0] = new Ship("SpeedBoat", 2, false, -1, -1);
        ships[1] = new Ship("Submarine1", 3, false,-1, -1);
        ships[2] = new Ship("Submarine2", 3, false, -1, -1);
        ships[3] = new Ship("Destroyer", 4, false, -1, -1);
        ships[4] = new Ship("AirCraft Carrier", 5, false, -1, -1);
        this.lives = 5;
        this.isAlive = true;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return this.score;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public Board getBoard() {
        return this.board;
    }
    public void setShips(Ship[] ships) {
        this.ships = ships;
    }
    public Ship[] getShips() {
        return this.ships;
    }
    public int getSpecificShip(int x, int y) {
        int[][] shipBoard = new int[15][15];
        int posX = 0;
        int posY = 0;
        for (int i = 0; i < ships.length; i++) {
            posX = ships[i].getX();
            posY = ships[i].getY();
            for (int j = 0; j < ships[i].getSize(); j++) {
                shipBoard[posX][posY] = i;
                switch (ships[i].getOrientation()) {
                    case 1 -> posX--;
                    case 2 -> posX++;
                    case 3 -> posY--;
                    case 4 -> posY++;
                }
            }
        }
        return shipBoard[x][y];
    }
    public String shipNameByPosition(int x, int y) {
        int ship = getSpecificShip(x, y);
        if (ship == -1) {
            return "";
        }
        return ships[ship].getName();
    }

    public int getMaxSizeOfShipAlive() {
        int max = 0;
        for (int i = 0; i < 5; i++) {
            if (!ships[i].isSunk() && ships[i].getSize() > max) {
                max = ships[i].getSize();
            }
        }
        return max;
    }

    public void removeHits(int shipPosition) {
        int x = getShips()[shipPosition].getX();
        int y = getShips()[shipPosition].getY();
        int size = getShips()[shipPosition].getSize();
        int orientation = getShips()[shipPosition].getOrientation();
        for (int i = 0; i < size; i++) {
            getGrid()[x][y] = 4;
            switch (orientation) {
                case 1 -> x--;
                case 2 -> x++;
                case 3 -> y--;
                case 4 -> y++;
            }
        }
    }

    public void reduceLive() {
        this.lives --;
        if (this.lives == 0) {
            this.isAlive = false;
        }
    }
    public int getLives() {
        return this.lives;
    }
    public boolean isAlive() {
        return this.isAlive;
    }

    public int[][] getGrid() {
        return this.board.getGrid();
    }
}
