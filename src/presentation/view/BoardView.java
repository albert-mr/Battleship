package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class BoardView extends JPanel {
    private Cell[][] cells;

    public BoardView(String nameBoard){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int y;
        y = (int) (screenSize.height * 0.8 * 0.35);
        this.setLayout(new GridLayout(15,15));
        this.cells = new Cell[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                this.cells[i][j] = new Cell(i, j);
                this.cells[i][j].setName(nameBoard);
                this.add(this.cells[i][j]);
            }
        }
        setName(nameBoard);
        setMinimumSize(new Dimension(y,y));

    }


    public void registerController(MouseListener bc){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                this.cells[i][j].registerController(bc);
            }
        }
    }

    public void disableBoard(MouseListener bc){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                this.cells[i][j].removeController(bc);
            }
        }
    }

    public void enterCell(int r, int c){
        if(this.cells[r][c].getBackground()!=Color.BLACK && this.cells[r][c].getBackground()!=Color.RED && this.cells[r][c].getBackground()!=Color.GREEN)
            this.cells[r ][c].setBackground(Color.GRAY);
    }
    public void exitCell(int r, int c){
        if(this.cells[r][c].getBackground()!=Color.BLACK && this.cells[r][c].getBackground()!=Color.RED && this.cells[r][c].getBackground()!=Color.GREEN)
            this.cells[r][c].setBackground(Color.WHITE);
    }

    public void markShip(int x, int y, int[] orientation, int size){
        for(int i = 0; i < size; i++) {
            this.cells[x + orientation[0]*i][y + orientation[1]*i].setBackground(Color.BLACK);
        }
    }

    public void resetOrientation(int[] orientation, int x, int y, int size, boolean alreadyPlaced){
        for(int i = 0; i < size; i++){
            if(this.cells[x + orientation[0]*i][y + orientation[1]*i].getBackground() != Color.BLACK || alreadyPlaced)
                this.cells[x + orientation[0]*i][y + orientation[1]*i].setBackground(Color.WHITE);
        }
    }

    public void shipOrientation(int[] orientation, int x, int y, int size){
        for(int i = 0; i < size; i++){
            if(this.cells[x + orientation[0]*i][y + orientation[1]*i].getBackground() != Color.BLACK)
                this.cells[x + orientation[0]*i][y + orientation[1]*i].setBackground(Color.GRAY);
        }
    }

    public void initializeBoardViewLoadedGame(int[][] board){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(board[i][j] == 3)
                    this.cells[i][j].setBackground(Color.RED);
                else if(board[i][j] == 4)
                    this.cells[i][j].setBackground(Color.GREEN);
                else if(board[i][j] == 2)
                    this.cells[i][j].setBackground(Color.GREEN);
            }
        }
    }

    public void markShot(int hitType, int r, int c, Color color){
        //TODO change color green to the other colors, changed for testing
        switch(hitType){
            case 0:
                this.cells[r][c].setBackground(Color.RED);
                break;
            case 1:
                this.cells[r][c].setBackground(Color.GREEN);
                break;
            case 2:
                this.cells[r][c].setBackground(Color.GREEN);
                //Indicate that ship was sunk !!!
                break;
        }
    }

    public void setLogout (LogoutView lov) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(lov, gbc);
    }
}
