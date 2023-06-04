package presentation.view;


// This may go in another package/class, but i'll keep it here for testing purposes and ease of access
// This part is painful to read. Sorry in advanced
// I would suggest redoing it, as it is a big mess
// Still have to figure out how to add vertical labels

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;


public class BarChart extends JPanel {
    private int[] values;
    private String title;

    public BarChart(int[] v, String t) {
        values = v;
        title = t;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        Font titleFont = new Font("Serif", Font.BOLD, 20);
        Font labelFont = new Font("Serif", Font.PLAIN, 20);
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D titleBounds = titleFont.getStringBounds(title, context);
        double titleWidth = titleBounds.getWidth();
        double top = titleBounds.getHeight();
        double y = -titleBounds.getY();
        double x = (panelWidth - titleWidth)/2;
        LineMetrics labelMetrics = labelFont.getLineMetrics("", context);
        double bottom = labelMetrics.getHeight();
        double scale;
        int barWidth;

        if (values == null){
            return;
        }

        int minValue = 0;
        int maxValue = 0;

        for (int v : values) {
            if (minValue > v){
                minValue = v;
            }
            if (maxValue < v){
                maxValue = v;
            }
        }

        if (maxValue == minValue) {
            return;
        }

        g2.setFont(titleFont);
        g2.drawString(title, (float) x, (float) y);

        y = panelHeight - labelMetrics.getDescent();
        g2.setFont(labelFont);

        scale = (panelHeight - top - bottom)/(maxValue - minValue);
        barWidth = (panelWidth)/(values.length);

        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                double x1 = i * barWidth + 1;
                double y1 = top;
                double height = values[i] * scale;

                y1 += (maxValue - values[i]) * scale;

                Rectangle2D rect = new Rectangle2D.Double(x1, y1, barWidth/2, height);
                g2.setPaint(Color.BLUE);
                g2.fill(rect);
                g2.setPaint(Color.BLACK);
                g2.draw(rect);
                String numGames = Integer.toString(i);
                g2.drawString(numGames, (float)x1+10, (float)-1);;
                String numAttacks = Integer.toString(values[i]);
                g2.drawString(numAttacks, (float)x1+10, (float)y1- 5);
            }
        }

    }
}