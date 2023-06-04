package presentation.view;


import presentation.controller.LogoutController;
import presentation.controller.MainFrameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel {

    private JButton startGame, loadGame, stats;
    private MainFrameController mfc;
    private LogoutView lov;

    public MainMenuView() {
        menu();
    }

    private void menu() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width);
        y = (int) (screenSize.height);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        JPanel options = new JPanel();
        options.setBorder(BorderFactory.createEmptyBorder((y/12), 0, (y/3), 0));
        options.setOpaque(false);

        startGame = new JButton("Start New Game");
        startGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGame.setFont(new Font("Serif", Font.BOLD, 35));

        loadGame = new JButton("Load Previous Game");
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGame.setFont(new Font("Serif", Font.BOLD, 35));

        stats = new JButton("Show Statistics");
        stats.setAlignmentX(Component.CENTER_ALIGNMENT);
        stats.setFont(new Font("Serif", Font.BOLD, 35));

        options.add(startGame);
        options.add(loadGame);
        options.add(stats);

        JPanel title = new JPanel();
        title.setLayout(new FlowLayout(FlowLayout.CENTER));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        title.setOpaque(false);

        JLabel t = new JLabel("BATTLESHIPS");
        t.setFont(new Font("Serif", Font.BOLD, 100));

        title.add(t);

        mainPanel.setBorder(BorderFactory.createEmptyBorder((y/5), 0, 0, 0));
        mainPanel.setOpaque(false);
        mainPanel.add(title);
        mainPanel.add(options);

        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.SOUTH;
        this.add(mainPanel, gbc);

        startGame.setActionCommand("BTN_START");
        loadGame.setActionCommand("BTN_LOAD");
        stats.setActionCommand("BTN_STATS");
    }

    public void setMainCnt(MainFrameController mfc){
        this.mfc = mfc;
    }

    public void showLoadGame(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"LoadGame");
    }

    public void showSetup(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"Setup");
    }

    public void registerController(ActionListener mmc){
        startGame.addActionListener(mmc);
        loadGame.addActionListener(mmc);
        stats.addActionListener(mmc);
    }

    public void setLogoutView (LogoutView lov) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        lov.setBorder(new EmptyBorder(0,screenSize.width/20,0,0));
        lov.setMainCnt(this.mfc);
        this.add(lov, gbc);
    }

}