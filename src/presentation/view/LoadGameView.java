package presentation.view;

import presentation.controller.LoadGameController;
import presentation.controller.MainFrameController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoadGameView extends JPanel {
    private MainFrameController mainFrameController;
    private DefaultTableModel model;
    private String[][] gameDataNames;
    private JTable games;
    private JButton startGame, back;

    public LoadGameView(){}

    public void initializeTable(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width * 0.6);
        y = (int) (screenSize.height * 0.6);
        back = new JButton("GO BACK");
        back.setFont(new Font("Serif", Font.BOLD, 35));
        startGame = new JButton("START");
        startGame.setFont(new Font("Serif", Font.BOLD, 35));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] columns = {"Name", "Date"};
        model = new DefaultTableModel(gameDataNames, columns);
        games = new JTable(model);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        JScrollPane scrollPane = new JScrollPane(games);
        add(scrollPane);

        add(games, gbc);


        back.setActionCommand("BTN_BACK");
        startGame.setActionCommand("BTN_START");
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridheight = 0;
        gbc.insets = new Insets(40,40,40,40);
        add(back, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.insets = new Insets(40,40,40,40);
        add(startGame, gbc);
    }

    public void registerController(LoadGameController lgc){
        this.games.getSelectionModel().addListSelectionListener(lgc);
        this.startGame.addActionListener(lgc);
        this.back.addActionListener(lgc);
    }

    public void setValues(String[] gameNames, String[] dates){
        this.gameDataNames = new String[gameNames.length][2];
        for(int i = 0; i < gameNames.length ; i++){
            this.gameDataNames[i][0] = gameNames[i];
            this.gameDataNames[i][1] = dates[i];
        }
        initializeTable();
    }

    public void showStartBtn(){
        this.startGame.setVisible(true);
    }

    public String getRowName(int row){
        return this.gameDataNames[row][0];
    }
    public void setMainCnt(MainFrameController mfc){ this.mainFrameController = mfc; }

    public JTable getTable(){ return this.games; }

    public void showGameStage(){ this.mainFrameController.getCl().show(this.mainFrameController.getMainPanel(), "GameStage");}
    public void showMainMenu(){ this.mainFrameController.getCl().show(this.mainFrameController.getMainPanel(), "MainMenu");}
}