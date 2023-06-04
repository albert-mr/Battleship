package presentation.controller;

import business.GameDTO_Manager;
import business.UserManager;
import presentation.view.LogoutView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutController implements ActionListener {
    private final LogoutView lov;
    private final UserManager manager;
    private final GameDTO_Manager gmdb;

    public LogoutController(LogoutView lov, UserManager manager, GameDTO_Manager gmdb) {
        this.lov = lov;
        this.lov.registerController(this);
        this.manager = manager;
        this.gmdb = gmdb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = lov.showOptionMsg();

        if (e.getActionCommand().equals("BTN_OUT")) {
            switch (i) {
                case 0 -> {
                    int n = lov.showDeleteOpt();
                    if (n == 0) {

                        String to_delete = manager.logged_user();
                        manager.change_status(to_delete, 0);
                        manager.deleteUser(to_delete);
                        gmdb.deleteGamesOfPlayer(to_delete);
                        this.lov.showMainFrame();
                    }
                }
                case 1 -> {

                    String to_delete = manager.logged_user();
                    manager.change_status(to_delete, 0);

                    this.lov.showMainFrame();
                }
            }
        }
    }
}