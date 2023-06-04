package business.battle;


public class AIthreadManager extends Thread{
    private final GameAIManager gameAIManager;

    public AIthreadManager(GameAIManager gameAIManager) {
        this.gameAIManager = gameAIManager;
    }


    @Override
    public void run() {
        try {
            Thread.sleep((long) 50*gameAIManager.posK());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!gameAIManager.gameIsOver()) {
            synchronized (gameAIManager) {
                gameAIManager.playGame();
            }
        }
        gameAIManager.endMessageDisplay();
    }

    public void startGame(){
        this.start();
    }
}
