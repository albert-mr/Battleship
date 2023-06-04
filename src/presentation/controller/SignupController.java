package presentation.controller;

import business.UserManager;
import presentation.view.SignupView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupController implements ActionListener {
    private final SignupView suv;
    private UserManager manager;


    public SignupController(SignupView suv, UserManager manager) {
        this.suv = suv;
        this.manager = manager;
        this.suv.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "BTN_SUBMIT":
                if (!manager.verifyPassword(suv.getPassConfTxt(), suv.getPassTxt())) {
                    suv.showWrongPassword();
                    suv.resetTxt();
                }else if(!manager.signUpVerification(suv.getUserTxt(),suv.getEmailTxt(),suv.getPassTxt())){
                    suv.showWrongUsername();
                    suv.resetDataTxt();
                }else{
                   manager.addUser(suv.getUserTxt(),suv.getEmailTxt(),suv.getPassTxt(),1);
                   this.suv.showMainMenu();
                }
                break;
            case "BTN_BACK":
                this.suv.showMainFrame();
                break;
            default:
                System.err.println("Unknown action command " + e.getActionCommand());
        }
    }
}
