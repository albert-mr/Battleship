package presentation.view;

import presentation.controller.MainFrameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class SetupGameView extends JPanel{
    private MainFrameController mfc;
    private JButton speedboat, submarine1, submarine2, destroyer, aircraft, startGame, saveBtn;
    private JTextField gameNameTxt;
    private JTable ships;
    private JPanel placementBtns;
    private JComboBox<Integer> comboBox;

    public SetupGameView() {
        initializePanel();
    }

    private void initializePanel(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width);
        y = (int) (screenSize.height);
        this.setName("SetupView");
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        listShips();
        add(placementBtns, gbc);

        JLabel game = new JLabel();
        game.setText("Choose name of the game: ");
        game.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        game.setBorder(new EmptyBorder(5,x/12,0,0));
        add(game, gbc);

        JLabel op = new JLabel();
        op.setText("Choose number of opponents: ");
        op.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        op.setBorder(new EmptyBorder(y/15,x/15,0,0));
        add(op, gbc);

        //TODO resize the text box as it is too small and in wrong position
        gameNameTxt = new JTextField(15);
        gameNameTxt.setFont(new Font("Serif", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(gameNameTxt, gbc);

        comboBox = new JComboBox<>();
        comboBox.setBorder(new EmptyBorder(y/15,(int)(x/3.8),0,0));
        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addItem(4);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;

        add(comboBox, gbc);

        startGame = new JButton("START GAME");
        startGame.setFont(new Font("Serif",Font.BOLD, 45));
        startGame.setName("BTN_START");
        startGame.setVisible(false);
        startGame.setBorder(new EmptyBorder(0,0,40,0));
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 2;
        add(startGame, gbc);

        gbc.anchor = GridBagConstraints.CENTER;

        ImageIcon speedboatIcon, submarinoIcon, destroyerIcon, aircraftIcon;
        try {
            Image img = ImageIO.read(new File("files/speedboar.png"));
            Image newImage = img.getScaledInstance((x/8), (y/15), java.awt.Image.SCALE_SMOOTH);
            speedboatIcon = new ImageIcon(newImage);
            img = ImageIO.read(new File("files/submarino.png"));
            newImage = img.getScaledInstance((x/8), (y/15), java.awt.Image.SCALE_SMOOTH);
            submarinoIcon = new ImageIcon(newImage);
            img = ImageIO.read(new File("files/destroyer.png"));
            newImage = img.getScaledInstance((x/10), (y/20), java.awt.Image.SCALE_SMOOTH);
            destroyerIcon = new ImageIcon(newImage);
            img = ImageIO.read(new File("files/aircraft.png"));
            newImage = img.getScaledInstance((x/8), (y/15), java.awt.Image.SCALE_SMOOTH);
            aircraftIcon = new ImageIcon(newImage);
            String[] columns = {"Name", "Size", "State"};
            Object[][] data = {
                    {"Speedboat", "◼◼", speedboatIcon },
                    {"Submarine", "◼◼◼", submarinoIcon },
                    {"Submarine", "◼◼◼", submarinoIcon },
                    {"Destroyer", "◼◼◼◼", destroyerIcon },
                    {"Aircraft Carrier", "◼◼◼◼◼", aircraftIcon }
            };
            DefaultTableModel model = new DefaultTableModel(data, columns);
            ships = new JTable(model){
                public Class getColumnClass(int column) {
                    return (column == 2) ? Icon.class : String.class;
                }
            };

            ships.setRowHeight((y/15));
            ships.setPreferredSize(new Dimension((x/4), (y/3)));
            ships.setPreferredScrollableViewportSize(ships.getPreferredSize());
            ships.setDefaultEditor(Object.class, null);
            JScrollPane scrollPane = new JScrollPane(ships);
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weightx = 0.5;
            add(scrollPane,gbc);

            this.add(ships, gbc);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("HELLO");
    }

    private void listShips(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width);
        y = (int) (screenSize.height);
        placementBtns = new JPanel();
        placementBtns.setLayout(new BoxLayout(placementBtns, BoxLayout.Y_AXIS));
        Font standard = new Font("Serif", Font.BOLD, 25);

        speedboat = new JButton("PLACE");
        speedboat.setFont(new Font("Serif", Font.BOLD, 30));
        speedboat.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        speedboat.setName("Speedboat");

        submarine1 = new JButton("PLACE");
        submarine1.setFont(new Font("Serif", Font.BOLD, 30));
        submarine1.setBorder(BorderFactory.createLineBorder(Color.PINK, 2));
        submarine1.setName("Submarine1");

        submarine2 = new JButton("PLACE");
        submarine2.setFont(new Font("Serif", Font.BOLD, 30));
        submarine2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        submarine2.setName("Submarine2");

        destroyer = new JButton("PLACE");
        destroyer.setFont(new Font("Serif", Font.BOLD, 30));
        destroyer.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        destroyer.setName("Destroyer");

        aircraft = new JButton("PLACE");
        aircraft.setFont(new Font("Serif", Font.BOLD, 30));
        aircraft.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        aircraft.setName("Aircraft Carrier");

        placementBtns.add(speedboat);
        placementBtns.add(Box.createRigidArea(new Dimension(0, (y/25))));
        placementBtns.add(submarine1);
        placementBtns.add(Box.createRigidArea(new Dimension(0, (y/25))));
        placementBtns.add(submarine2);
        placementBtns.add(Box.createRigidArea(new Dimension(0, (y/25))));
        placementBtns.add(destroyer);
        placementBtns.add(Box.createRigidArea(new Dimension(0, (y/25))));
        placementBtns.add(aircraft);
    }

    public void registerController(MouseListener sgc){
        speedboat.addMouseListener(sgc);
        submarine1.addMouseListener(sgc);
        submarine2.addMouseListener(sgc);
        destroyer.addMouseListener(sgc);
        aircraft.addMouseListener(sgc);
        startGame.addMouseListener(sgc);
        addMouseListener(sgc);
    }

    public void disableSetupView(MouseListener sgc){
        speedboat.removeMouseListener(sgc);
        submarine1.removeMouseListener(sgc);
        submarine2.removeMouseListener(sgc);
        destroyer.removeMouseListener(sgc);
        aircraft.removeMouseListener(sgc);

    }

    public void setBoardView(BoardView bv){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        this.add(bv, gbc);
    }


    public void setMainCnt(MainFrameController mfc){ this.mfc = mfc; }
    public void showCantPlaceShip(){ JOptionPane.showMessageDialog(null, "The ship can't be placed in this position.", "Error", JOptionPane.ERROR_MESSAGE); }

    public String getNameGame(){ return this.gameNameTxt.getText(); }

    public void showStartBtn(){
        startGame.setVisible(true);
    }

    public void hideStartBtn(){
        startGame.setVisible(false);
    }


    public void showRemoveBtn(int i, boolean isRemove){
        String state;
        state = isRemove?"REMOVE":"PLACE";
        switch(i){
            case 0 -> speedboat.setText(state);
            case 1 -> submarine1.setText(state);
            case 2 -> submarine2.setText(state);
            case 3 -> destroyer.setText(state);
            case 4 -> aircraft.setText(state);
        }
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

    public int getNumPlayers(){ return this.comboBox.getSelectedIndex() + 1; }
    public void showGameStage(){ mfc.getCl().show(mfc.getMainPanel(), "GameStage");}
    public void showWrongNameMsg(){ JOptionPane.showMessageDialog(null, "Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE); }

}
