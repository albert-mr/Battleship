package presentation.controller;

import presentation.view.MainMenuView;
import presentation.view.LogoutView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {
    private final MainMenuView mmv;
    private LoadGameController lgc;
    private LogoutController loc;
    private LogoutView lov;

    public MainMenuController(MainMenuView mmv, LoadGameController lgc, LogoutView lov, LogoutController loc) {
        this.mmv = mmv;
        this.lov = lov;
        this.loc = loc;
        this.mmv.setLogoutView(lov);
        this.mmv.registerController(this);
        this.lgc = lgc;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()) {
            case "BTN_START":
                mmv.showSetup();
                break;
            case "BTN_LOAD":
                lgc.setGameValues();
                mmv.showLoadGame();
                break;
            case "BTN_STATS":
                break;
            default:
                System.err.println("Unknown action command " + e.getActionCommand());

        }
    }
}