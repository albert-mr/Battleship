package presentation.controller;

import business.UserManager;
import presentation.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private final LoginView lv;
    private UserManager manager;

    public LoginController(LoginView lv, UserManager manager){
        this.lv = lv;
        this.manager = manager;
        this.lv.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "BTN_SUBMIT": //until the database is full working
                //lv.showMainMenu();
                if(!manager.loginVerification(lv.getUserTxt(), lv.getUserTxt(), lv.getPassTxt())){
                    lv.resetTxt();
                    lv.showWrongLogin();
                }else {
                    manager.change_status(lv.getUserTxt(), 1);
                    lv.showMainMenu();
                }
                break;
            case "BTN_BACK":
                lv.showMainFrame();
                break;
            default:
                System.err.println("Unknown action command " + e.getActionCommand());
        }
    }
}
