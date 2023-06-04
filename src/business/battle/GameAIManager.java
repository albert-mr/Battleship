package business.battle;

import business.entities.Game;
import business.entities.Player;
import presentation.controller.GameListener;

import java.util.ArrayList;
import java.util.Random;

public class GameAIManager{
    private Game game;
    private GameListener gameListener;
    private GameManager gameManager;
    private final int k;

    public GameAIManager(int i) {
        this.k = i;
    }

    public void playGame() {
        try {
            if (game.getAIPlayers()[k].isAlive() && gameListener.pauseResume() && gameManager.canAttack(300)) {
                int[] attack;
                int[] shotPos;
                shotPos = getBestMoveOfAllBoards(k);
                attack = applyToOtherPlayers(shotPos[0], shotPos[1], -1, k);
                gameListener.addNumAttacks();
                if (attack[0] == 2)
                    gameListener.showSunkShip(gameManager.shipNameByPos(attack[1], attack[2], -1), -1);
                gameListener.showAttacks(attack[0], attack[1], attack[2], k, gameListener.getBoardByPos(-1));
                for (int j = 0; j < gameListener.getNumBoards(); j++) {
                    if (j != k && game.getAIPlayers()[j].isAlive()) {
                        attack = applyToOtherPlayers(shotPos[0], shotPos[1], j, k);
                        if (attack[0] == 2)
                            gameListener.showSunkShip(gameManager.shipNameByPos(attack[1], attack[2], j), j);
                        gameListener.showAttacks(attack[0], attack[1], attack[2], k, gameListener.getBoardByPos(j));
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int posK(){ return this.k; }

    public boolean gameIsOver(){
        return this.gameManager.gameOver();
    }

    public void endMessageDisplay(){
        gameListener.endTimer();
        if (k == 0)
            gameListener.endOfGame(gameManager.playerIsDead());
    }



    public boolean moveNotOutOfBounds(int x, int y) {
        return x >= 0 && x <= 14 && y >= 0 && y <= 14;
    }

    public boolean shipSunk(Player player, int x, int y) {
        int shipPosition = player.getSpecificShip(x, y);
        return !player.getShips()[shipPosition].isSunk();
    }

    public void addGreatMoves(Player player, ArrayList<int[]> greatMoves, int i, int j) {
        int[] move = new int[2];
        if (moveNotOutOfBounds(i - 1, j) && player.getGrid()[i - 1][j] != 2 && player.getGrid()[i - 1][j] != 3 && player.getGrid()[i - 1][j] != 4) {
            if (moveNotOutOfBounds(i + 1, j) && player.getGrid()[i + 1][j] == 2) {
                move[0] = i - 1;
                move[1] = j;
                greatMoves.add(move);
            }
        }
        if (moveNotOutOfBounds(i + 1, j) && player.getGrid()[i + 1][j] != 2 && player.getGrid()[i + 1][j] != 3 && player.getGrid()[i + 1][j] != 4) {
            if (moveNotOutOfBounds(i - 1, j) && player.getGrid()[i - 1][j] == 2) {
                move[0] = i + 1;
                move[1] = j;
                greatMoves.add(move);
            }
        }
        if (moveNotOutOfBounds(i, j - 1) && player.getGrid()[i][j - 1] != 2 && player.getGrid()[i][j - 1] != 3 && player.getGrid()[i][j - 1] != 4) {
            if (moveNotOutOfBounds(i, j + 1) && player.getGrid()[i][j + 1] == 2) {
                move[0] = i;
                move[1] = j - 1;
                greatMoves.add(move);
            }
        }
        if (moveNotOutOfBounds(i, j + 1) && player.getGrid()[i][j + 1] != 2 && player.getGrid()[i][j + 1] != 3 && player.getGrid()[i][j + 1] != 4) {
            if (moveNotOutOfBounds(i, j - 1) && player.getGrid()[i][j - 1] == 2) {
                move[0] = i;
                move[1] = j + 1;
                greatMoves.add(move);
            }
        }
    }

    public void addGoodMoves(Player player, ArrayList<int[]> moves, int i, int j) {
        int[] move = new int[2];
        if (moveNotOutOfBounds(i - 1, j) && player.getGrid()[i - 1][j] != 2 && player.getGrid()[i - 1][j] != 3
                && shipSunk(player, i - 1, j) && player.getGrid()[i - 1][j] != 4) {
            move[0] = i - 1;
            move[1] = j;
            moves.add(move);
        }
        if (moveNotOutOfBounds(i + 1, j) && player.getGrid()[i + 1][j] != 2 && player.getGrid()[i + 1][j] != 3
                && shipSunk(player, i + 1, j) && player.getGrid()[i + 1][j] != 4) {
            move[0] = i + 1;
            move[1] = j;
            moves.add(move);
        }
        if (moveNotOutOfBounds(i, j - 1) && player.getGrid()[i][j - 1] != 2 && player.getGrid()[i][j - 1] != 3
                && shipSunk(player, i, j - 1) && player.getGrid()[i][j - 1] != 4) {
            move[0] = i;
            move[1] = j - 1;
            moves.add(move);
        }
        if (moveNotOutOfBounds(i, j + 1) && player.getGrid()[i][j + 1] != 2 && player.getGrid()[i][j + 1] != 3
                && shipSunk(player, i, j + 1) && player.getGrid()[i][j + 1] != 4) {
            move[0] = i;
            move[1] = j + 1;
            moves.add(move);
        }
    }

    public void getMoves(Player player, ArrayList<int[]> moves, ArrayList<int[]> moves2) {
        int[] move = new int[2];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (player.getGrid()[i][j] == 2) {
                    addGoodMoves(player, moves, i, j);
                    addGreatMoves(player, moves2, i, j);
                }
            }
        }
    }

    public int[] applyToOtherPlayers(int x, int y, int attackedPosition, int playerPosition) {
        int[] result = new int[3];
        if (attackedPosition == -1) {
            if (game.getPlayer().getGrid()[x][y] == 2 || game.getPlayer().getGrid()[x][y] == 3 || game.getPlayer().getGrid()[x][y] == 4) {
                result[0] = -1;
                return result;
            } else {
                result[1] = x;
                result[2] = y;
                if (game.getPlayer().getGrid()[x][y] == 1) { //if the opponent has hit
                    game.getPlayer().getGrid()[x][y] = 2; //set the shot to hit position
                    int shipPosition = game.getPlayer().getSpecificShip(x, y); //get the ship that was hit
                    game.getPlayer().getShips()[shipPosition].reduceHealth(); //reduce health of ship
                    if (game.getPlayer().getShips()[shipPosition].isSunk()) { //if the ship is dead
                        game.getPlayer().reduceLive(); //reduce 1 live
                        game.getPlayer().removeHits(shipPosition); //remove the hits from the board
                        result[0] = 2;
                        return result; //return 2 to indicate that the ship was sunk
                    } else {
                        result[0] = 1;
                        return result; //return 1 for hit
                    }
                } else {
                    game.getPlayer().getBoard().setMiss(x, y); //set the shot to miss position
                    return result; //return 0 for miss
                }
            }
        } else if (playerPosition != attackedPosition) {
            if (game.getAIPlayers()[attackedPosition].getGrid()[x][y] == 2 || game.getAIPlayers()[attackedPosition].getGrid()[x][y] == 3 || game.getAIPlayers()[attackedPosition].getGrid()[x][y] == 4) {
                result[0] = -1;
                return result;
            } else {
                result[1] = x;
                result[2] = y;
                if (game.getAIPlayers()[attackedPosition].getGrid()[x][y] == 1) {
                    game.getAIPlayers()[attackedPosition].getGrid()[x][y] = 2;
                    int shipPosition = game.getAIPlayers()[attackedPosition].getSpecificShip(x, y);
                    game.getAIPlayers()[attackedPosition].getShips()[shipPosition].reduceHealth();
                    if (game.getAIPlayers()[attackedPosition].getShips()[shipPosition].isSunk()) {
                        game.getAIPlayers()[attackedPosition].reduceLive();
                        game.getAIPlayers()[attackedPosition].removeHits(shipPosition); //remove the hits from the board
                        result[0] = 2;
                        return result; //return 2 to indicate that the ship was sunk
                    }
                    result[0] = 1;
                    return result; //return 1 for hit
                } else {
                    game.getAIPlayers()[attackedPosition].getGrid()[x][y] = 3;
                    return result; //return 0 for miss
                }
            }
        }
        result[0] = -1;
        return result;
    }

    public int[] getRandomShotAI(Player player) {
        Random rand = new Random();
        ArrayList<int[]> shots = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (player.getGrid()[i][j] != 2 && player.getGrid()[i][j] != 3 && player.getGrid()[i][j] != 4) {
                    shots.add(new int[]{i, j});
                }
            }
        }
        return (shots.get(rand.nextInt(0, shots.size())));
    }

    public int[] getBestMoveOfAllBoards(int playerPosition) {
        ArrayList<int[]> moves = new ArrayList<>();
        ArrayList<int[]> moves2 = new ArrayList<>();
        ArrayList<int[]> bestMoves = new ArrayList<>();
        ArrayList<int[]> bestMoves2 = new ArrayList<>();
        ArrayList<int[]> randomMoves = new ArrayList<>();
        Random rand = new Random();

        for (int i = -1; i< game.getNumberPlayers() - 1; i++) {
            if (i == -1) {
                getMoves(game.getPlayer(), moves, moves2);
                bestMoves.addAll(moves);
                bestMoves2.addAll(moves2);
                if (getRandomShot(game.getPlayer())[0] != -1) {
                    randomMoves.add(getRandomShot(game.getPlayer()));
                } else {
                    randomMoves.add(getRandomShotAI(game.getPlayer()));
                }
            } else if (i != playerPosition) {
                getMoves(game.getAIPlayers()[i], moves, moves2);
                bestMoves.addAll(moves);
                bestMoves2.addAll(moves2);
                if (getRandomShot(game.getAIPlayers()[i])[0] != -1) {
                    randomMoves.add(getRandomShot(game.getAIPlayers()[i]));
                } else {
                    randomMoves.add(getRandomShotAI(game.getAIPlayers()[i]));
                }
            }
        }
        if (bestMoves2.size() != 0) {
            return bestMoves2.get(rand.nextInt(0, moves2.size()));
        } else if (bestMoves.size() != 0) {
            return bestMoves.get(rand.nextInt(0, moves.size()));
        } else {
            return randomMoves.get(rand.nextInt(0, randomMoves.size()));
        }
    }

    public int[] getRandomShot(Player player) {
        Random rand = new Random();
        ArrayList<int[]> shots = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (player.getGrid()[i][j] != 2 && player.getGrid()[i][j] != 3 && player.getGrid()[i][j] != 4) {
                    if (i %2 == 0 && j % 2 == 0) {
                        shots.add(new int[]{i, j});
                    } else if (i % 2 == 1 && j % 2 == 1) {
                        shots.add(new int[]{i, j});
                    }
                }
            }
        }
        if (shots.size() == 0) {
            return new int[]{-1, -1};
        } else {
            return shots.get(rand.nextInt(0, shots.size()));
        }
    }

    public void setGameListener(GameListener gl){
        this.gameListener = gl;
    }
    public void setGame(Game game){ this.game = game;}

    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
    }
}

