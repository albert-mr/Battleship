package presentation.view;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.EventListener;

public class Cell extends JPanel {
    private int row;
    private int column;

    public Cell(int r, int c){
        this.row = r;
        this.column = c;
        this.setPreferredSize(new Dimension(50, 50));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setBackground(Color.WHITE);
    }

    public int getRow(){ return this.row; }
    public int getColumn(){ return this.column; }

    public void registerController(MouseListener ml){
        this.addMouseListener(ml);
    }

    public void removeController(MouseListener ml){
        this.removeMouseListener(ml);
    }
}
