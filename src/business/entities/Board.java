package business.entities;

public class Board {
    private int[][] grid; //0 = water, 1 = ship, 2 = hit

    public Board() {
        this.grid = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void setShot(int x, int y) {
        grid[x][y] = 2;
    }
    public void setMiss(int x, int y) {
        grid[x][y] = 3;
    }
    public void setShipSunk(int x, int y) {
        grid[x][y] = 4;
    }
    public boolean hasHit(int x, int y) {
        return grid[x][y] == 1;
    }

    public boolean checkBordersForPlace(int x, int y) {
        if (x < 0 || x > 14 || y < 0 || y > 14) {
            return false;
        } else if (x == 0 && y == 0) {
            return (!(grid[x][y] == 1) && !(grid[x + 1][y] == 1) && !(grid[x][y + 1] == 1));
        } else if (x == 14 && y == 0) {
            return (!(grid[x][y] == 1) && !(grid[x - 1][y] == 1) && !(grid[x][y + 1] == 1));
        } else if (x == 0 && y == 14) {
            return (!(grid[x][y] == 1) && !(grid[x + 1][y] == 1) && !(grid[x][y - 1] == 1));
        } else if (x == 14 && y == 14) {
            return (!(grid[x][y] == 1) && !(grid[x - 1][y] == 1) && !(grid[x][y - 1] == 1));
        } else if (x == 0) {
            return (!(grid[x][y] == 1) && !(grid[x + 1][y] == 1) && !(grid[x][y - 1] == 1) && !(grid[x][y + 1] == 1));
        } else if (x == 14) {
            return (!(grid[x][y] == 1) && !(grid[x - 1][y] == 1) && !(grid[x][y - 1] == 1) && !(grid[x][y + 1] == 1));
        } else if (y == 0) {
            return (!(grid[x][y] == 1) && !(grid[x - 1][y] == 1) && !(grid[x + 1][y] == 1) && !(grid[x][y + 1] == 1));
        } else if (y == 14) {
            return (!(grid[x][y] == 1) && !(grid[x - 1][y] == 1) && !(grid[x + 1][y] == 1) && !(grid[x][y - 1] == 1));
        } else {
            return (!(grid[x][y] == 1) && !(grid[x - 1][y] == 1) && !(grid[x + 1][y] == 1) && !(grid[x][y - 1] == 1) && !(grid[x][y + 1] == 1));
        }
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public void addShip(int x, int y){
        this.grid[x][y] = 1;
    }

    public void deleteShip(int x, int y){
        this.grid[x][y] = 0;
    }

    public void copyBoard(Board board){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                grid[i][j] = board.getGrid()[i][j];
            }
        }
    }
}
