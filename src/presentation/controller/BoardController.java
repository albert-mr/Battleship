package presentation.controller;

import presentation.view.BoardView;
import presentation.view.Cell;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardController implements MouseListener {
    private BoardView bv;

    public BoardController(BoardView bv){
        this.bv = bv;
        this.bv.registerController(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
