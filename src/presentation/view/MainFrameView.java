package presentation.view;

import presentation.controller.MainFrameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrameView extends JPanel {
    private JButton login, signup;
    private BufferedImage image;
    public static final String BTN_LOGIN = "BTN_LOGIN";
    public static final String BTN_SIGNUP = "BTN_SIGNUP";
    private MainFrameController mfc;


    public MainFrameView(){
        configureView();
    }

    private void configureView() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width * 0.6);
        y = (int) (screenSize.height * 0.6);
        login = new JButton("LOG IN");
        login.setFont(new Font("Serif", Font.BOLD, 35));
        signup = new JButton("SIGN UP");
        signup.setFont(new Font("Serif", Font.BOLD, 35));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        try {
            image = ImageIO.read(new File("files/battleships_01.png"));
            Image newImage = image.getScaledInstance(x,y, java.awt.Image.SCALE_SMOOTH);

            JLabel im = new JLabel(new ImageIcon(newImage));
            im.setBorder(new EmptyBorder(0,0,(y/11),0));
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.gridwidth = 3;
            add(im, gbc);

        }catch(IOException e){
            //Exception
        }

        login.setActionCommand(BTN_LOGIN);
        signup.setActionCommand(BTN_SIGNUP);
        login.setBorder(new EmptyBorder(0,(x/3),(y/8),(x/12)));
        signup.setBorder(new EmptyBorder(0,(x/12),(y/8),(x/3)));

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(login, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 1;

        add(signup, gbc);
    }


    public void registerController(ActionListener mfc){
        this.login.addActionListener(mfc);
        this.signup.addActionListener(mfc);

    }

    public void setMainCnt(MainFrameController mfc){
        this.mfc = mfc;
    }

    public void showLogin(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"Login");
    }

    public void showSignup(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"Signup");
    }

}
