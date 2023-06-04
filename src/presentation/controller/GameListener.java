package presentation.controller;

import presentation.view.BoardView;

public interface GameListener {

    void showAttacks(int shotType, int x, int y, int typePlayer, BoardView boardAttacked);
    void showSunkShip(String nameShip, int posPlayer);
    int getNumBoards();
    void addNumAttacks();
    BoardView getBoardByPos(int pos);
    boolean pauseResume();
    void endOfGame(boolean isDead);
    void showEndMessage(boolean isDead);
    void setTimer(int minutes, int seconds);
    void endTimer();
}
