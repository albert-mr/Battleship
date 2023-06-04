package business.entities;

import business.setup.SetupAIGameModel;

public class Game {
    private String name;
    private int[] timer;
    private int numberPlayers;
    private boolean isLoaded;
    private Player player;
    private Player[] AIPlayers;

    public Game() {
        this.numberPlayers = 0;
        this.player = new Player();
    }

    public void setGame(int numberPlayers) {
        this.numberPlayers = numberPlayers;
        this.AIPlayers = new Player[numberPlayers - 1];
        for (int i = 0; i < numberPlayers - 1; i++) {
            this.AIPlayers[i] = new Player();
        }
        this.AIPlayers = new SetupAIGameModel(this.AIPlayers).createSetup();
        this.isLoaded = false;
        this.timer = new int[]{0, 0};
    }

    /*public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Timer getTimer() {
        return this.timer;
    }*/

    public void setIsLoaded() {
        this.isLoaded = true;
    }

    public boolean getIsLoaded() {
        return this.isLoaded;
    }

    public void setGameName(String name) {
        this.name = name;
    }

    public String getGameName() {
        return this.name;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }

    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player[] getAIPlayers() {
        return AIPlayers;
    }

    public void setAIPlayer(Player player, int playerPosition) {
        this.AIPlayers[playerPosition] = player;
    }

    public void setAIPlayers(Player[] AIPlayers) {
        this.AIPlayers = AIPlayers;
    }

    public Player setPlayers(Player player) {
        return this.player = player;
    }

    public Player getSpecificPlayer(int pos){
        return pos==-1?this.player:this.getAIPlayers()[pos];
    }

    public int[] getTimer() {
        return timer;
    }

    public void setTimer(int[] timer) {
        this.timer = timer;
    }

    //public startGame() {}
        //set ships used to true
        //unable movement
        //save position of ships

}
