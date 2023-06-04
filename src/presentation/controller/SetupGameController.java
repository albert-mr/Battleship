package presentation.controller;

import business.GameDTO_Manager;
import business.UserManager;
import business.battle.GameManager;
import business.setup.SetupGameModel;
import com.mysql.cj.log.Log;
import presentation.view.BoardView;
import presentation.view.Cell;
import presentation.view.SetupGameView;
import presentation.view.LogoutView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SetupGameController implements MouseListener {
    private SetupGameView sgv;
    private final SetupGameModel sgm;
    private final GameManager gm;
    private final GameStageController gsm;
    private final GameDTO_Manager gameDTO_manager;
    private final UserManager userManager;
    private final LogoutView lov;
    private MainFrameController mainFrameController;
    private BoardView bv;
    private boolean clicked;
    private int[] orientation;
    private int x, y;
    private int size;
    private String shipName;

    public SetupGameController(SetupGameView sgv, LogoutView lov, SetupGameModel sgm, GameManager gm, GameStageController gsm, GameDTO_Manager gdm, UserManager um, MainFrameController mfc){
        this.mainFrameController = mfc;
        this.gameDTO_manager = gdm;
        this.userManager = um;
        this.gsm = gsm;
        this.sgm = sgm;
        this.gm = gm;
        this.lov = lov;
        this.bv = new BoardView("");
        this.sgv = sgv;
        this.sgv.setLogout(lov);
        this.sgv.setBoardView(bv);
        this.sgv.registerController(this);
        this.clicked = false;
        this.orientation = new int[2];
    }

    private void resetSetup(){
        this.bv = new BoardView("");
        this.sgv = new SetupGameView();
        this.sgm.resetModel();
        this.mainFrameController.resetSetup(sgv);
        this.sgv.setLogout(lov);
        this.sgv.setBoardView(bv);
        this.sgv.registerController(this);
        this.clicked = false;
        this.orientation = new int[2];
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() instanceof Cell) {
            //boolean to set the ship
            clicked = !clicked;
            if (!clicked) {
                if (sgm.placeShip(x, y, retrieveOrientation(orientation), size, shipName)) {
                    if(sgm.inBounds(x,y,size,retrieveOrientation(orientation)))
                        bv.markShip(x, y,  orientation, size);
                    sgv.showRemoveBtn(sgm.getShipType(shipName), true);
                    if(sgm.startGame())
                        sgv.showStartBtn();
                } else {
                    sgv.showCantPlaceShip();
                    if(sgm.inBounds(x,y,size,retrieveOrientation(orientation)))
                        bv.resetOrientation(orientation, x, y, size, false);
                }
                bv.disableBoard(this);
                sgv.registerController(this);
            } else {
                x = ((Cell) e.getSource()).getRow();
                y = ((Cell) e.getSource()).getColumn();
                sgv.disableSetupView(this);
            }
        }else if(sgm.isShip(e.getComponent().getName())) {
            if (sgm.isPlaced(((JButton)e.getSource()).getText())) {
                placeShip(e.getComponent().getName());
            } else {
                removeBtn(e.getComponent().getName());
            }
        }else if(e.getComponent().getName().equals("BTN_START")) {
            String newGame = sgv.getNameGame();
            if(!gameDTO_manager.uniqueGameName(userManager.logged_user(), newGame) || newGame.equals(""))
                this.sgv.showWrongNameMsg();
            else {
                sgm.setGame(sgv.getNumPlayers() + 1, newGame);
                gm.setGame(sgm.getGame());
                gsm.resetGame();
                gsm.initializeData();
                sgv.showGameStage();
                resetSetup();
            }
            String name = "joacogame";
            sgm.setGame(sgv.getNumPlayers() + 1, name);
            gm.setGame(sgm.getGame());
            gsm.initializeData();
            sgv.showGameStage();
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
        if(e.getSource() instanceof Cell){
            if(clicked) {
                orientation = sgm.shipOrientation(x, y, ((Cell) e.getSource()).getRow(), ((Cell) e.getSource()).getColumn());
                if(sgm.inBounds(x,y,size,retrieveOrientation(orientation)))
                    bv.shipOrientation(orientation, x, y, size);
            }else
                this.bv.enterCell(((Cell) e.getSource()).getRow(), ((Cell) e.getSource()).getColumn());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof Cell){
            if(clicked && sgm.inBounds(x,y,size,retrieveOrientation(orientation)))
                bv.resetOrientation(orientation, x, y, size, false);
            else
                this.bv.exitCell(((Cell) e.getSource()).getRow(), ((Cell) e.getSource()).getColumn());
        }
    }


    private void placeShip(String nameShip){
        bv.registerController(this);
        size = sgm.getShipSize(nameShip);
        shipName = nameShip;
    }

    private void removeBtn(String btnName) {
        int[] removePos;
        sgv.showRemoveBtn(sgm.getShipType(btnName), false);
        removePos = sgm.removeShip(btnName);
        sgv.hideStartBtn();
        int[] removePosAux = {removePos[2], removePos[3]};
        bv.resetOrientation(removePosAux, removePos[0], removePos[1], removePos[4], true);
    }

    private int retrieveOrientation(int[] o){
        switch(o[0]){
            case -1:
                return 1;
            case 1:
                return 2;
            default:
                switch (o[1]) {
                    case -1 -> {
                        return 3;
                    }
                    case 1 -> {
                        return 4;
                    }
                    default -> {
                        return 0;
                    }
                }
        }
    }
}
