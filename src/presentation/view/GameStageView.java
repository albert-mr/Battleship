package presentation.view;

import presentation.controller.MainFrameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseListener;


public class GameStageView extends JPanel {
    private int numBoards;
    private BoardView player;
    private BoardView[] boardAIs;
    private MainFrameController mfc;
    private JPanel jpBoardsAIs, log, timer;
    private JScrollBar scrollBar;
    private JButton startBtn, backBtn;
    private JTable[] shipsState;
    private JLabel timerMinutes, timerSeconds, reload;


    public GameStageView(){
        initializePanel();
    }


    private void initializePanel(){

        this.shipsState = new JTable[numBoards + 1];
        this.setName("GameStageView");
        this.setLayout(new GridBagLayout());
        addLog();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.SOUTH;
        startBtn = new JButton("START/PAUSE");
        startBtn.setFont(new Font("Serif", Font.BOLD, 25));
        startBtn.setName("BTN_START");
        add(startBtn, gbc);

        addTimer(0,0);

        backBtn = new JButton("GO BACK");
        backBtn.setFont(new Font("Serif", Font.BOLD, 25));
        backBtn.setName("BTN_BACK");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backBtn, gbc);

        reload = new JLabel("O");
        reload.setForeground(Color.green);
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        add(reload, gbc);
    }

    public void changeReload() {
        if (reload.getForeground() == Color.green) {
            reload.setForeground(Color.red);
        } else {
            reload.setForeground(Color.green);
        }
    }

    public void registerController (MouseListener gsc) {
        startBtn.addMouseListener(gsc);
        backBtn.addMouseListener(gsc);
    }

    public void setPlayerBoard(BoardView p){
        this.player = p;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        shipStateTable(0);
        add(boardAndShips(this.player, new JLabel("YOUR BOARD"), shipsState[0]), gbc);
    }

    public void setNumBoard(int numBoards){
        this.numBoards = numBoards;
        this.shipsState = new JTable[(numBoards+1)];
    }

    public void setAIsBoards(BoardView[] ai){
        this.boardAIs = ai;
        this.jpBoardsAIs = new JPanel(new GridLayout(2,2));
        GridBagConstraints gbc = new GridBagConstraints();
        for(int i = 0; i <numBoards; i++) {
            shipStateTable(i+1);
            this.jpBoardsAIs.add(boardAndShips(this.boardAIs[i], new JLabel("OPPONENT " + (i+1)),shipsState[i+1]));;
        }
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        add(this.jpBoardsAIs, gbc);
    }

    private void shipStateTable(int playerPos) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width * 0.8 * 0.35 * 0.25);
        y = (int) (screenSize.height / 30);
        String[] columns = {"Name", "Size"};
        String[][] data = {
                {"Speedboat", "Sailing"},
                {"Submarine", "Sailing"},
                {"Submarine", "Sailing"},
                {"Destroyer", "Sailing"},
                {"Aircraft Carrier", "Sailing"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columns);
        shipsState[playerPos] = new JTable(model);

        shipsState[playerPos].setRowHeight(y);
        shipsState[playerPos].setMinimumSize(new Dimension(x,y*5));
    }

    private JPanel boardAndShips(BoardView board,JLabel title, JTable shipState){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width * 0.8 * 0.35);
        y = (int) (screenSize.height * 0.8 * 0.45);

        JPanel b = new JPanel(new GridBagLayout());
        b.setBackground(Color.lightGray);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        b.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridx = 5;
        gbc.insets = new Insets(10,10,10,10);
        b.add(shipState, gbc);

        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.gridheight = 5;

        b.setMinimumSize(new Dimension(x, y));
        b.add(board, gbc);

        return b;
    }


    private void addLog(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width * 0.8 * 0.23);
        y = (int) (screenSize.height * 0.8 * 0.3);

        JScrollPane jsp;

        JPanel auxPanel = new JPanel(new BorderLayout());
        log = new JPanel();
        log.setLayout(new BoxLayout(log, BoxLayout.Y_AXIS));
        log.setBackground(Color.lightGray);

        auxPanel.add(log, BorderLayout.SOUTH);
        auxPanel.setBackground(Color.lightGray);

        jsp = new JScrollPane(auxPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setMinimumSize(new Dimension(x,y));
        scrollBar = jsp.getVerticalScrollBar();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        add(jsp, gbc);

    }
    public void writeLog(int shotType, String attacked){
        JPanel auxPanel = new JPanel(new BorderLayout());
        auxPanel.setBackground(Color.lightGray);
        JLabel action = new JLabel();
        switch (shotType) {
            case 0 -> action.setText("You missed your shot on " + attacked + ".");
            case 1 -> action.setText("You hit " + attacked + "'s ship !");
            case 2 -> action.setText("You sunk " + attacked + "'s ship ! ☺");
        }
        action.setForeground(Color.BLUE);
        auxPanel.add(action, BorderLayout.EAST);
        log.add(auxPanel);

        revalidate();
        scrollBar.setValue(scrollBar.getMaximum());
    }

    public void writeLogAI(int shotType, String attacker, String attacked){
        JPanel auxPanel = new JPanel(new BorderLayout());
        auxPanel.setBackground(Color.lightGray);
        JLabel action = new JLabel();
        switch(shotType){
            case 0:
                if(attacked.equals(this.player.getName()))
                    action.setText(attacker + " missed his shot on you.");
                else
                    action.setText(attacker + " missed his shot on " + attacked + ".");
                break;
            case 1:
                if(attacked.equals(this.player.getName()))
                    action.setText(attacker + " hit your ship.");
                else
                    action.setText(attacker + " hit " + attacked + "'s ship.");
                break;
            case 2:
                if(attacked.equals(this.player.getName()))
                    action.setText(attacker + " sunk your ship.");
                else
                    action.setText(attacker + " sunk " + attacked + "'s ship.");
                break;
        }
        switch(attacker){
            case "Opponent1" -> action.setForeground(Color.black);
            case "Opponent2" -> action.setForeground(Color.red);
            case "Opponent3" -> action.setForeground(Color.pink);
            case "Opponent4" -> action.setForeground(Color.magenta);
        }
        auxPanel.add(action, BorderLayout.WEST);
        log.add(auxPanel);

        revalidate();
        scrollBar.setValue(scrollBar.getMaximum());
    }

    private void addTimer(int minutes, int seconds){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int y, x;
        x = (int) (screenSize.width * 0.8);
        y = (int) (screenSize.height * 0.8 * 0.03);
        timer = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel name = new JLabel("TIME ELAPSED");
        name.setFont(new Font("Serif", Font.BOLD, 15));
        name.setBorder(BorderFactory.createEmptyBorder(0, 2, 10, 2));
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 3;

        timer.add(name, constraints);

        timerMinutes = new JLabel();
        timerMinutes.setFont(new Font("Serif", Font.PLAIN, 12));
        timerMinutes.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        timerMinutes.setText(Integer.toString(minutes));

        JLabel space = new JLabel(" : ");
        space.setFont(new Font("Serif", Font.PLAIN, 12));
        space.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        timerSeconds = new JLabel();
        timerSeconds.setFont(new Font("Serif", Font.PLAIN, 12));
        timerSeconds.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        timerSeconds.setText(Integer.toString(seconds));

        constraints.gridy = 1;
        constraints.gridheight = 2;
        constraints.anchor = GridBagConstraints.WEST;

        timer.add(timerMinutes, constraints);

        constraints.anchor = GridBagConstraints.CENTER;
        timer.add(space, constraints);

        constraints.anchor = GridBagConstraints.EAST;
        timer.add(timerSeconds, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        timer.setBorder(new EmptyBorder(y,x/15,0,0));
        add(timer, constraints);
    }

    public void setMainCnt(MainFrameController mfc){ this.mfc = mfc; }


    public void showPosHitMsg(){ JOptionPane.showMessageDialog(null, "This position was already hit !", "Error", JOptionPane.ERROR_MESSAGE); }
    public void showDeadMsg(){ JOptionPane.showMessageDialog(null, "All this player's ships are sunk !", "Error", JOptionPane.ERROR_MESSAGE); }

    public void showLostMsg() { JOptionPane.showMessageDialog(null, "You lost all of your ships... :( ", "Loss", JOptionPane.INFORMATION_MESSAGE);}

    public void showWinMsg() { JOptionPane.showMessageDialog(null, "Congratulations, you won ! :)", "Win", JOptionPane.INFORMATION_MESSAGE); }
 public boolean showSaveGameQuestion(){ return JOptionPane.showConfirmDialog(null, "Do you want to save this game?", "Save", JOptionPane.YES_NO_OPTION) == 1;}


    public void sunkShip(int numPlayer, String nameShip){
        switch (nameShip) {
            case "SpeedBoat" -> this.shipsState[numPlayer+1].setValueAt("Sunk", 0,1);
            case "Submarine1" -> this.shipsState[numPlayer+1].setValueAt("Sunk", 1,1);
            case "Submarine2" -> this.shipsState[numPlayer+1].setValueAt("Sunk", 2,1);
            case "Destroyer" -> this.shipsState[numPlayer+1].setValueAt("Sunk", 3,1);
            case "AirCraft Carrier" -> this.shipsState[numPlayer+1].setValueAt("Sunk", 4,1);
        }
    }

    public void showMainMenu(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"MainMenu");
    }

    public void setLogout (LogoutView lov) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 0;
        gbc.gridx = 0;
        lov.setBorder(new EmptyBorder(0,screenSize.width/102,0,0));
        add(lov, gbc);
    }

    public void setTimer(int minutes, int seconds){
        this.timerSeconds.setText(Integer.toString(seconds));
        this.timerMinutes.setText(Integer.toString(minutes));
    }



}
