package presentation.view;

import presentation.controller.MainFrameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LogoutView extends JPanel{
    private JButton outB;
    private MainFrameController mfc;

    public LogoutView(){
        outBut();
    }

    private void outBut() {
        try {
            Image img = ImageIO.read(new File("files/ico.png"));
            Image newImage = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newImage);

            outB = new JButton();
            outB.setIcon(icon);

        } catch (IOException e) {
            //exception
        }

        outB.setActionCommand("BTN_OUT");
        outB.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(outB);
    }

    public int showOptionMsg() {
        String[] options = new String[2];
        options[1] = "Logout";
        options[0] = "Delete";
        return JOptionPane.showOptionDialog(null, "Would you like to logout or delete account?", "", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
    }

    public int showDeleteOpt(){
        return JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?", "Deletion", JOptionPane.YES_NO_OPTION);
    }

    public void registerController(ActionListener loc){
        outB.addActionListener(loc);
    }
    public void setMainCnt(MainFrameController mfc){
        this.mfc = mfc;
    }

    public void showMainFrame(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"MainFrame");
    }

}

