package presentation.controller;

import business.GameDTO_Manager;
import business.UserManager;
import business.battle.GameManager;
import presentation.view.LoadGameView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadGameController implements ListSelectionListener, ActionListener {
    private LoadGameView loadGameView;
    private final GameManager gameManager;
    private final GameDTO_Manager gameDTO_manager;
    private final GameStageController gameStageController;
    private final UserManager userManager;
    private String gameName;


    public LoadGameController(LoadGameView loadGameView, GameManager gameManager, GameStageController gameStageController, GameDTO_Manager gameDTO_manager, UserManager userManager) {
        this.gameManager = gameManager;
        this.loadGameView = loadGameView;
        this.gameStageController = gameStageController;
        this.gameDTO_manager = gameDTO_manager;
        this.userManager = userManager;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        gameName = loadGameView.getRowName(loadGameView.getTable().getSelectedRow());
        loadGameView.showStartBtn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("BTN_START")){
            gameManager.loadGame(gameName);
            gameManager.deleteGameFile(gameName);
            gameStageController.resetGame();
            gameStageController.initializeData();
            loadGameView.showGameStage();
        }
        if(e.getActionCommand().equals("BTN_BACK")){
            loadGameView.showMainMenu();
        }
    }

    public void setGameValues(){
        String[] names = gameDTO_manager.getAllPlayableGamesNames(userManager.logged_user());
        String[] dates = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            dates[i] = gameDTO_manager.getDate(names[i]);
        }


        this.loadGameView.setValues(names, dates);
        this.loadGameView.registerController(this);
    }
}
