package presentation.view;

//import presentation.controller.StatisticsController;

import javax.swing.*;
<<<<<<< HEAD
import javax.swing.border.Border;
=======
>>>>>>> 794bebdf048415a2f37ec7719f19515120c3fb76
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Arc2D;

public class StatisticsView extends JPanel {
    private static JButton otherPlayers;
    private static JLabel title, graph1_title, graph2_title;
    private Graphics graph1, graph2;
    private static int wins, losses;
    public static final String OTHER_PLAYERS = "OTHER_PLAYERS";


    private static JPanel stats() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width * 0.8);
        y = (int) (screenSize.height * 0.8);

        JPanel statsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Graphics g = null;

        // Declaring Pie chart

        title = new JLabel("STATISTICS");
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setBorder(new EmptyBorder(0,x/3,30,x/3));
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        statsPanel.add(title, gbc);

        graph1_title = new JLabel("GRAPH1");
        graph1_title.setFont(new Font("Serif", Font.BOLD, 25));
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.WEST;
        statsPanel.add(graph1_title, gbc);

        graph2_title = new JLabel("GRAPH2");
        graph2_title.setFont(new Font("Serif", Font.BOLD, 25));
        gbc.gridy = 1;
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        statsPanel.add(graph2_title, gbc);

        otherPlayers = new JButton("All players statistics");
        gbc.gridy = 1;
        gbc.gridx = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        statsPanel.add(otherPlayers, gbc);

        PieChart p = new PieChart(900,529);
        p.setBorder(new EmptyBorder(y/3,y/3,y/3,y/3));
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        statsPanel.add(p, gbc);

        int[] value = {32, 12, 32, 43, 65, 45, 10, 23, 21, 43, 2, 56, 12, 125};

        BarChart b = new BarChart(value, "TOTAL GAMES PLAYED");
        b.setBorder(new EmptyBorder(y/3,y/3,y/3,y/3));

        gbc.gridy = 2;
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.SOUTH;
        statsPanel.add(b, gbc);
        return statsPanel;
    }

    // Used for testing purposes
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width * 0.8);
        y = (int) (screenSize.height * 0.8);
        JFrame h = new JFrame();
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        h.add(stats());
        h.setSize(x, y);
        h.setResizable(false);
        h.setVisible(true);
    }

<<<<<<< HEAD
}

=======
    public void registerController(StatisticsController statisticsController) {
        otherPlayers.addActionListener(statisticsController);
    }
}
>>>>>>> 794bebdf048415a2f37ec7719f19515120c3fb76
