package business.battle;

import business.entities.Game;
import persistence.LoadGameDAO;

import java.io.IOException;

public class GameManager {
    private Game game;
    private LoadGameDAO gameDAO;
    public GameManager() {gameDAO = new LoadGameDAO(game);}

    public String getGamePath(String gameName) {
        return gameDAO.getGamePath(gameName);
    }

    public void saveGame(Game game, String gameName){
        gameDAO.saveGame(game, gameName);
    }

    public void deleteGameFile(String gameName){
        gameDAO.deleteGame(gameName);
    }

    public void loadGame(String gameName){
        this.game = gameDAO.loadGame(gameName);
    }

    public String getDateOfGame(String gameName) throws IOException {
        return gameDAO.getDateOfGame(gameName);
    }

    public void setIsLoaded() {
        game.setIsLoaded();
    }

    public void setGame(Game game){ this.game = game;}

    public int kaboomOtherPlayer(int x, int y, int attackedPosition) {
        if (game.getAIPlayers()[attackedPosition].getGrid()[x][y] == 2 || game.getAIPlayers()[attackedPosition].getGrid()[x][y] == 3 || game.getAIPlayers()[attackedPosition].getGrid()[x][y] == 4) {
            return -1; // return -1 if the position has already been shot at
        } else {
            if (game.getAIPlayers()[attackedPosition].getGrid()[x][y] == 1) {
                game.getAIPlayers()[attackedPosition].getGrid()[x][y] = 2;
                int shipPosition = game.getAIPlayers()[attackedPosition].getSpecificShip(x, y);
                game.getAIPlayers()[attackedPosition].getShips()[shipPosition].reduceHealth();
                if (game.getAIPlayers()[attackedPosition].getShips()[shipPosition].isSunk()) {
                    game.getAIPlayers()[attackedPosition].reduceLive();
                    game.getAIPlayers()[attackedPosition].removeHits(shipPosition); //remove the hits from the board
                    return 2; //return 2 to indicate that the ship was sunk
                }
                return 1; //return 1 for hit
            } else {
                game.getAIPlayers()[attackedPosition].getGrid()[x][y] = 3;
                return 0; //return 0 for miss
            }
        }
    }

    public boolean verifyShotFromPlayer(int x, int y, int attackedPlayerPosition) {
        return game.getAIPlayers()[attackedPlayerPosition].getGrid()[x][y] == 1 || game.getAIPlayers()[attackedPlayerPosition].getGrid()[x][y] == 0;
    }

    public boolean gameOver() {
        int playersAlive = 0;
        if (game.getPlayer().isAlive()) { //add 1 to players alive if player is alive
            playersAlive++;
        }else{
            return true;
        }

        for (int i = 0; i < game.getNumberPlayers() - 1; i++) {
            if (game.getAIPlayers()[i].isAlive()) { //if the AI is alive
                playersAlive++; //add 1 to players alive
            }
        }
        return playersAlive == 1; //if only 1 player is alive return true
    }

    public boolean canAttack(int i) throws InterruptedException {
        Thread.sleep(300); //wait 3 seconds until next attack
        return true;
    }

    public int getNumberPlayers(){ return this.game.getNumberPlayers() - 1; }

    public Game getGame(){ return this.game; }

    public boolean aiIsDead(int pos){
        return game.getAIPlayers()[pos].isAlive();
    }

    public boolean playerIsDead(){ return !game.getPlayer().isAlive(); }

    public String shipNameByPos(int x, int y, int posPlayer){
        return this.game.getSpecificPlayer(posPlayer).shipNameByPosition(x,y);
    }


}
