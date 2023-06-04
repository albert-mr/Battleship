package business.entities;

public class Ship {
    private String name;
    private int size;
    private int x;
    private int y;
    private int health;
    private boolean isSunk;
    private boolean used;
    private int orientation; //orientation: 1 for up, 2 for down, 3 for left, 4 for right
    //int x and int y
    //calcular size identificar barcos

    public Ship(String name, int size, boolean used, int x, int y) {
        this.name = name;
        this.size = size;
        this.used = used;
        this.x = x;
        this.y = y;
        this.health = size;
        this.isSunk = false;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isUsed() {
        return used;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setIsUsed(boolean used) {
        this.used = used;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }
    public void reduceHealth() {
        this.health--;
        if (this.health == 0) {
            this.isSunk = true;
        }
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void setIsSunk(boolean isSunk) {
        this.isSunk = isSunk;
    }
}
