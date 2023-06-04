import business.GameDTO_Manager;
import business.UserManager;
import business.battle.GameManager;
import business.setup.SetupGameModel;
import presentation.controller.*;
import presentation.view.*;

import javax.swing.SwingUtilities;

public class Main {
    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializePresentation();
            }
        });

    }

    private static void initializePresentation(){
        //Views instantiation
        LoginView loginView = new LoginView();
        SignupView signupView = new SignupView();
        MainFrameView mainFrameView = new MainFrameView();
        MainMenuView mainMenuView = new MainMenuView();
        SetupGameView setupGameView = new SetupGameView();
        GameStageView gameStageView = new GameStageView();
        LogoutView logoutView = new LogoutView();
        LoadGameView loadGameView = new LoadGameView();

        //Models instantiation
        GameManager gameManager = new GameManager();
        GameDTO_Manager gameDTO_manager = new GameDTO_Manager();
        SetupGameModel setupGameModel = new SetupGameModel();
        UserManager userManager = new UserManager();


        //Controller instantiations
        LogoutController logoutController = new LogoutController(logoutView, userManager, gameDTO_manager);
        SignupController signupController = new SignupController(signupView, userManager);
        LoginController loginController = new LoginController(loginView, userManager);
        MainFrameController mainFrameController = new MainFrameController(mainFrameView, loginView, signupView, mainMenuView, setupGameView, gameStageView, logoutView, loadGameView);
        GameStageController gameStageController = new GameStageController(gameStageView, gameManager, gameDTO_manager, userManager, mainFrameController);
        LoadGameController loadGameController = new LoadGameController(loadGameView, gameManager, gameStageController, gameDTO_manager, userManager);
        MainMenuController mainMenuController = new MainMenuController(mainMenuView, loadGameController, logoutView, logoutController);
        SetupGameController setupGameController = new SetupGameController(setupGameView, logoutView, setupGameModel, gameManager, gameStageController, gameDTO_manager, userManager, mainFrameController);

    }
}