package presentation.controller;
import business.GameDTO_Manager;
import business.UserManager;
import business.battle.AIthreadManager;
import business.battle.GameAIManager;
import business.battle.GameManager;
import business.battle.TimerThread;
import presentation.view.BoardView;
import presentation.view.Cell;
import presentation.view.GameStageView;
import presentation.view.LogoutView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameStageController implements MouseListener, GameListener {
    private GameStageView gsv;
    private BoardView[] boardAIs;
    private BoardView player;
    private final GameManager gm;
    private GameAIManager gmAI;
    private AIthreadManager[] aIthreadManagers;
    private TimerThread timerThread;
    private final GameDTO_Manager gameDTO_manager;
    private final UserManager userManager;
    private MainFrameController mainFrameController;

    private Color[] colors;
    private int numBoards;
    private int playerNumAttacks;
    private boolean start, resume;

    public GameStageController(GameStageView gsv, GameManager gm, GameDTO_Manager gdm, UserManager um, MainFrameController mfc) {
        //Game Manager initialization
        this.mainFrameController = mfc;
        this.gm = gm;
        this.colors = new Color[]{Color.green, Color.cyan, Color.pink, Color.orange, Color.magenta};
        this.gameDTO_manager = gdm;
        this.userManager = um;
        this.gsv = gsv;
        LogoutView lov = new LogoutView();
        LogoutController loc = new LogoutController(lov, um, gdm);
        gsv.setLogout(lov);
        this.gsv.registerController(this);
        this.start = true;
        this.resume = true;
    }

    public void resetGame(){
        this.gsv = new GameStageView();
        this.mainFrameController.resetGame(this.gsv);
        LogoutView lov = new LogoutView();
        LogoutController loc = new LogoutController(lov, this.userManager, this.gameDTO_manager);
        gsv.setLogout(lov);
        this.gsv.registerController(this);
        this.start = true;
        this.resume = true;
    }

    public void initializeData(){
        //Creation of boards
        this.numBoards = this.gm.getNumberPlayers();//gm.getNumberPlayers();
        boardAIs = new BoardView[numBoards];
        player = new BoardView("Player");

        this.aIthreadManagers = new AIthreadManager[numBoards];

        for(int i = 0; i <numBoards; i++) {
            this.boardAIs[i] =new BoardView("Opponent" + (i+1));
        }

        player.initializeBoardViewLoadedGame(gm.getGame().getPlayer().getGrid());
        for (int i = 0; i < numBoards; i++) {
            boardAIs[i].initializeBoardViewLoadedGame(gm.getGame().getAIPlayers()[i].getGrid());
        }

        //Game Stage view initialization
        this.gsv.setNumBoard(numBoards);
        this.gsv.setPlayerBoard(player);
        this.gsv.setAIsBoards(boardAIs);

        for(int i = 0; i < numBoards; i++){
            //AI threads initialization
            this.gmAI = new GameAIManager(i);
            this.gmAI.setGameListener(this);
            this.gmAI.setGameManager(this.gm);
            this.gmAI.setGame(this.gm.getGame());
            this.aIthreadManagers[i] = new AIthreadManager(gmAI);

        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        /* if(((JComponent) e.getSource()).getName().equals("BTN_SAVE")){
            String gameName = gsv.getGameNameTxt();
            System.out.println(gameName);
            //gm.saveGame(gm.getGame(), gameName);
            if (gdbm.uniqueGameName(um.logged_user(), gameName)) {
                gm.saveGame(gm.getGame(), gameName);
                gdbm.addGame(gameName, um.logged_user(), gm.getGamePath(gameName), gdbm.currentDate(), "playing", playerNumAttacks, "null");
                gsv.showGameSavedMsg();
                this.gsv.showMainFrame();
            } else {
                gsv.showGameNameError();
            }
        } */
        if(((JComponent) e.getSource()).getName().equals("BTN_START")){

            if(start){

                try {
                    enableBoards();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                for(AIthreadManager ai: aIthreadManagers){
                    ai.startGame();
                }

                timerThread = new TimerThread(gm.getGame().getTimer()[1],gm.getGame().getTimer()[0]);
                timerThread.setGameListener(this);
                timerThread.startTimer();
                start = false;
            }else{
                resume = !resume;
                this.timerThread.pauseGame();
            }
        }
        if(((JComponent) e.getSource()).getName().equals("BTN_BACK")){
            if(!gm.gameOver()) {
                if (!gsv.showSaveGameQuestion()) {
                    //SAVE GAME
                    if (gm.getGame().getIsLoaded()) {
                        gm.deleteGameFile(gm.getGame().getGameName());
                        gm.saveGame(gm.getGame(), gm.getGame().getGameName());
                        gameDTO_manager.updateGame(gm.getGame().getGameName(), userManager.logged_user(), gm.getGamePath(gm.getGame().getGameName()), gameDTO_manager.currentDate(), "playing", playerNumAttacks, "null");
                    } else {
                        gm.setIsLoaded();
                        gm.saveGame(gm.getGame(), gm.getGame().getGameName());
                        gameDTO_manager.addGame(gm.getGame().getGameName(), userManager.logged_user(), gm.getGamePath(gm.getGame().getGameName()), gameDTO_manager.currentDate(), "playing", playerNumAttacks, "null");
                    }
                }
            }
            gsv.showMainMenu();
            resetGame();
        }
        if(e.getSource() instanceof Cell) {
            /*
            try {
                enableBoards();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

             */
            try {
                if(gm.canAttack(0) && pauseResume()){
                    if(!gm.aiIsDead(getAIposition(((Cell)e.getSource()).getName()))){
                        gsv.showDeadMsg();
                    }else if(!gm.verifyShotFromPlayer(((Cell) e.getSource()).getRow(), ((Cell) e.getSource()).getColumn(), getAIposition(((Cell) e.getSource()).getName()))) {
                        gsv.showPosHitMsg();
                    }else{
                        gsv.changeReload();
                        playerNumAttacks++;
                        for (int i = 0; i < numBoards; i++) {
                            int shotType;
                            shotType = gm.kaboomOtherPlayer(((Cell) e.getSource()).getRow(), ((Cell) e.getSource()).getColumn(), i);
                            if(shotType == 2)
                                showSunkShip(gm.shipNameByPos(((Cell) e.getSource()).getRow(), ((Cell) e.getSource()).getColumn(), i), i);
                            showAttacks(shotType, ((Cell) e.getSource()).getRow(), ((Cell) e.getSource()).getColumn(), -1, boardAIs[i]);
                        }
                    }
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof Cell) {
            this.boardAIs[getAIposition(((Cell) e.getSource()).getName())].enterCell(((Cell) e.getSource()).getRow(), ((Cell) e.getSource()).getColumn());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof Cell) {
            this.boardAIs[getAIposition(((Cell) e.getSource()).getName())].exitCell(((Cell) e.getSource()).getRow(), ((Cell) e.getSource()).getColumn());
        }
    }

    private void enableBoards() throws InterruptedException {
        for (int i = 0; i < numBoards; i++) {
            this.boardAIs[i].disableBoard(this);
        }
        if(gm.canAttack(100)) {
            for (int i = 0; i < numBoards; i++) {
                this.boardAIs[i].registerController(this);
            }
        }
    }


    private int getAIposition(String nameAI){
        switch (nameAI) {
            case "Opponent1" -> {
                return 0;
            }
            case "Opponent2" -> {
                return 1;
            }
            case "Opponent3" -> {
                return 2;
            }
            case "Opponent4" -> {
                return 3;
            }
            default -> {
                return -1;
            }
        }
    }


    @Override
    public void showAttacks(int shotType, int x, int y, int typePlayer, BoardView boardAttacked) {
        if(shotType != -1) {
            boardAttacked.markShot(shotType, x, y, colors[typePlayer + 1]);
            if (typePlayer == -1) {
                gsv.writeLog(shotType, boardAttacked.getName());
            } else {
                gsv.writeLogAI(shotType, boardAIs[typePlayer].getName(), boardAttacked.getName());
            }
        }
    }

    @Override
    public void showSunkShip(String nameShip, int posPlayer) {
        gsv.sunkShip(posPlayer, nameShip);
    }

    @Override
    public int getNumBoards() {
        return numBoards;
    }

    @Override
    public void addNumAttacks() {
        playerNumAttacks++;
    }

    @Override
    public BoardView getBoardByPos(int pos) {
        if(pos == -1){
            return this.player;
        }else{
            return this.boardAIs[pos];
        }
    }

    @Override
    public boolean pauseResume() {
        return this.resume;
    }

    @Override
    public void showEndMessage(boolean isDead) {
        this.timerThread.endGame();
    }

    @Override
    public void endOfGame(boolean isDead) {
        if(isDead) {
            gsv.showLostMsg();
            if (gm.getGame().getIsLoaded()) {
                gameDTO_manager.updateGame(gm.getGame().getGameName(), userManager.logged_user(), gm.getGamePath(gm.getGame().getGameName()), gameDTO_manager.currentDate(), "finished", playerNumAttacks, "bot");
            } else {
                gameDTO_manager.addGame(gm.getGame().getGameName(), userManager.logged_user(), gm.getGamePath(gm.getGame().getGameName()), gameDTO_manager.currentDate(), "finished", playerNumAttacks, "bot");
            }
        } else {
            this.gsv.showWinMsg();
            if (gm.getGame().getIsLoaded()) {
                gameDTO_manager.updateGame(gm.getGame().getGameName(), userManager.logged_user(), gm.getGamePath(gm.getGame().getGameName()), gameDTO_manager.currentDate(), "finished", playerNumAttacks, userManager.logged_user());
            } else {
                gameDTO_manager.addGame(gm.getGame().getGameName(), userManager.logged_user(), gm.getGamePath(gm.getGame().getGameName()), gameDTO_manager.currentDate(), "finished", playerNumAttacks, userManager.logged_user());
            }
        }
    }

    @Override
    public void setTimer(int minutes, int seconds) {
        this.gsv.setTimer(minutes, seconds);
    }

    @Override
    public void endTimer() {
        this.timerThread.endGame();
    }
}
