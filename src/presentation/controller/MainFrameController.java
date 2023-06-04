package presentation.controller;

import presentation.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController implements ActionListener {
    private CardLayout cl;
    private JPanel mainPanel;
    private MainFrameView mfv;
    private SetupGameView setupGameView;
    private GameStageView gameStageView;
    public MainFrameController(MainFrameView mfv, LoginView lv, SignupView suv, MainMenuView mmv, SetupGameView sgv, GameStageView gsv, LogoutView lov, LoadGameView lgv){
        initializeFrame();
        initializeCardLayout(mfv,lv,suv,mmv,sgv,gsv,lov, lgv);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "BTN_LOGIN" -> mfv.showLogin();
            case "BTN_SIGNUP" -> mfv.showSignup();
            default -> System.err.println("Unknown action command " + e.getActionCommand());
        }
    }


    private void initializeFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width * 0.85);
        y = (int) (screenSize.height * 0.85);
        JFrame f = new JFrame();
        f.setResizable(false);
        f.setTitle("BATTLESHIPS");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(x,y);
        f.setLocationRelativeTo(null);
        f.getContentPane().setLayout(null);
        this.cl = new CardLayout();
        this.mainPanel = new JPanel(cl);
        this.mainPanel.setSize(x,y);
        f.getContentPane().add(mainPanel);
        f.setVisible(true);
    }

    private void initializeCardLayout(MainFrameView mfv, LoginView lv, SignupView suv, MainMenuView mmv, SetupGameView sgv, GameStageView gsv, LogoutView lov, LoadGameView lgv){
        this.mfv = mfv;
        this.mfv.registerController(this);
        this.mfv.setMainCnt(this);
        this.setupGameView = sgv;
        this.gameStageView = gsv;
        lgv.setMainCnt(this);
        lv.setMainCnt(this);
        suv.setMainCnt(this);
        mmv.setMainCnt(this);
        this.setupGameView.setMainCnt(this);
        lov.setMainCnt(this);
        this.gameStageView.setMainCnt(this);
        this.mainPanel.add(this.mfv, "MainFrame");
        this.mainPanel.add(lv, "Login");
        this.mainPanel.add(suv, "Signup");
        this.mainPanel.add(mmv, "MainMenu");
        this.mainPanel.add(setupGameView, "Setup");
        this.mainPanel.add(gameStageView, "GameStage");
        this.mainPanel.add(lgv, "LoadGame");
    }
    public CardLayout getCl(){ return this.cl; }
    public JPanel getMainPanel(){ return this.mainPanel; }

    public void resetSetup(SetupGameView setupGameView){
        mainPanel.remove(this.setupGameView);
        setupGameView.setMainCnt(this);
        this.setupGameView = setupGameView;
        mainPanel.add(this.setupGameView, "Setup");

    }
    public void resetGame(GameStageView gameStageView){
        mainPanel.remove(this.gameStageView);
        gameStageView.setMainCnt(this);
        this.gameStageView = gameStageView;
        mainPanel.add(this.gameStageView, "GameStage");

    }
}
