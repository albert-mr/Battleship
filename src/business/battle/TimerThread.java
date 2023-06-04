package business.battle;

import presentation.controller.GameListener;

public class TimerThread extends Thread{
    private int seconds, minutes;
    private boolean count, isEnd;
    private GameListener gameListener;

    public TimerThread(int seconds, int minutes){
        this.seconds = seconds;
        this.minutes = minutes;
        this.count = true;
        this.isEnd = false;
    }
    @Override
    public void run() {
        while(true) {
            if (count) {
                seconds++;

                if (seconds == 59) {
                    seconds = 0;
                    minutes++;
                }
                gameListener.setTimer(minutes, seconds);
            }
            if(isEnd)
                break;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startTimer(){
        this.start();
    }
    public int[] getTimer(){
        return new int[]{minutes, seconds};
    }
    public void setGameListener(GameListener gameListener){ this.gameListener = gameListener; }
    public void pauseGame(){ this.count = !count; }
    public void endGame(){ this.isEnd = true; }
}