package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

// This may go in another package/class, but i'll keep it here for testing purposes and ease of access

public class PieChart extends JPanel {
    private int victories;
    private int losses;

    public PieChart(int victories, int losses){
        this.victories = victories;
        this.losses = losses;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        arc.setFrame(125, 125, 100, 100);

        // Win percentage is scaled between [0, 1]
        int totalGames = victories + losses;
        // Plotting win percentage in pie chart
        arc.setAngleStart(0);
        arc.setAngleExtent((float) 360 * victories / totalGames); // The extent of the pie chart that must be painted with green
        g2.setStroke(new BasicStroke((float) 5));
        g2.setColor(Color.black);
        g2.draw(arc);// Drawing the arc defined
        g2.setColor(Color.green);               // Win ratio: Green
        g2.fill(arc);                           // Filling with color green

        // Plotting loss percentage in pie chart
        arc.setAngleStart((float) 360 * victories / totalGames);
        arc.setAngleExtent((float) 360 * losses / totalGames);   // The extent that has to be painted with red is the rest of the pie chart

<<<<<<< HEAD
       //public void paintComponent (Graphics g){
       //    float win_percentage;
       //    Graphics2D g2 = (Graphics2D) g;
       //
       //    Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
       //    arc.setFrame(125, 125, 400, 400);
       //
       //    /*
       //     *    Determining the portion of each side of the graph:
       //     *        - Basically, getting the number of wins/losses of the player
       //     *        - Computing the percentage to assign to each part from 360 degrees
       //     *
       //     *       W+L  ->  100%
       //     *        W   ->   X
       //     *
       //     *       X = (100*W)/(W+L) -> W%
       //     *       L% = 100 - W%
       //     */
       //
       //    // Calculating W%. No need to compute loss percentage, as we can do 100% - W% to get it
       //    //win_percentage = (100*wins)/(wins + losses);
       //
       //    // Test variables. Change values to verify that pie works
       //    float total_wins = 6834;         // Float even though that will effectively be always integer values
       //    float total_losses = 9003;
       //
       //    // Win percentage is scaled between [0, 1]
       //    win_percentage = ((100 * total_wins) / (total_wins + total_losses)) / 100;
       //
       //    // Plotting loss percentage in pie chart
       //    arc.setAngleStart(0);
       //    arc.setAngleExtent(2); // The extent of the pie chart that must be painted with green
       //    g2.setStroke(new BasicStroke((float) 5));
       //    g2.setColor(Color.black);
       //    g2.draw(arc);                           // Drawing the arc defined
       //    g2.setColor(Color.green);               // Win ratio: Green
       //    g2.fill(arc);                           // Filling with color green
       //
       //    // Plotting win percentage in pie chart
       //    arc.setAngleStart(2);
       //    arc.setAngleExtent(360 - (2));   // The extent that has to be painted with red is the rest of the pie chart
       //    g2.setStroke(new BasicStroke((float) 5));
       //    g2.setColor(Color.black);
       //    g2.draw(arc);                           // Drawing the arc defined
       //    g2.setColor(Color.red);                 // Loss ratio: Red
       //    g2.fill(arc);                           // Filling with color red
       //
       //}
=======
        //public void paintComponent (Graphics g){
        //    float win_percentage;
        //    Graphics2D g2 = (Graphics2D) g;
        //
        //    Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        //    arc.setFrame(125, 125, 400, 400);
        //
        //    /*
        //     *    Determining the portion of each side of the graph:
        //     *        - Basically, getting the number of wins/losses of the player
        //     *        - Computing the percentage to assign to each part from 360 degrees
        //     *
        //     *       W+L  ->  100%
        //     *        W   ->   X
        //     *
        //     *       X = (100*W)/(W+L) -> W%
        //     *       L% = 100 - W%
        //     */
        //
        //    // Calculating W%. No need to compute loss percentage, as we can do 100% - W% to get it
        //    //win_percentage = (100*wins)/(wins + losses);
        //
        //    // Test variables. Change values to verify that pie works
        //    float total_wins = 6834;         // Float even though that will effectively be always integer values
        //    float total_losses = 9003;
        //
        //    // Win percentage is scaled between [0, 1]
        //    win_percentage = ((100 * total_wins) / (total_wins + total_losses)) / 100;
        //
        //    // Plotting loss percentage in pie chart
        //    arc.setAngleStart(0);
        //    arc.setAngleExtent(2); // The extent of the pie chart that must be painted with green
        //    g2.setStroke(new BasicStroke((float) 5));
        //    g2.setColor(Color.black);
        //    g2.draw(arc);                           // Drawing the arc defined
        //    g2.setColor(Color.green);               // Win ratio: Green
        //    g2.fill(arc);                           // Filling with color green
        //
        //    // Plotting win percentage in pie chart
        //    arc.setAngleStart(2);
        //    arc.setAngleExtent(360 - (2));   // The extent that has to be painted with red is the rest of the pie chart
        //    g2.setStroke(new BasicStroke((float) 5));
        //    g2.setColor(Color.black);
        //    g2.draw(arc);                           // Drawing the arc defined
        //    g2.setColor(Color.red);                 // Loss ratio: Red
        //    g2.fill(arc);                           // Filling with color red
        //
        //}
>>>>>>> 794bebdf048415a2f37ec7719f19515120c3fb76
    }
}