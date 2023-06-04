package presentation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowPlayersView extends JPanel {
    private static JButton back, startGame;
    private static DefaultTableModel model;
    private static String[][] gameDataNames;

    public ShowPlayersView(){};

    public static JPanel initializePanel () {
        JPanel a = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width * 0.6);
        y = (int) (screenSize.height * 0.6);
        back = new JButton("GO BACK");
        back.setFont(new Font("Serif", Font.BOLD, 35));
        startGame = new JButton("START");
        startGame.setFont(new Font("Serif", Font.BOLD, 35));

        a.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] columns = {"Players", "Number of games"};
        model = new DefaultTableModel(gameDataNames, columns);
        JTable players = new JTable(model);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        JScrollPane scrollPane = new JScrollPane(players);
        a.add(scrollPane);

        a.add(players, gbc);


        back.setActionCommand("BTN_BACK");
        startGame.setActionCommand("BTN_START");
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridheight = 0;
        gbc.insets = new Insets(40,40,40,40);
        a.add(back, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.insets = new Insets(40,40,40,40);
        a.add(startGame, gbc);

        return a;
    }

    public static void main(String[] args) {
        JFrame h = new JFrame();
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        h.add(initializePanel());
        h.setSize(1280, 720);
        h.setVisible(true);
    }
}
