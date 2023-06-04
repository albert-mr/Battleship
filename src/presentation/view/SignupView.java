package presentation.view;

import presentation.controller.MainFrameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignupView extends JPanel {
    private JLabel u, e, p, pc;
    private JTextField usertxt, passtxt, emailtxt, passconftxt;
    private JButton back, submit;
    public static final String BTN_SUBMIT = "BTN_SUBMIT";
    public static final String BTN_BACK = "BTN_BACK";
    private MainFrameController mfc;

    public SignupView(){
        configureView();
    }
    private void configureView(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width);
        y = (int) (screenSize.height);
        u = new JLabel("Username:");
        u.setBorder(new EmptyBorder(0,(x/8),0,0));
        u.setFont(new Font("Serif", Font.BOLD, 20));
        e = new JLabel("Email:");
        e.setBorder(new EmptyBorder(0,(x/8),0,0));
        e.setFont(new Font("Serif", Font.BOLD, 20));
        p = new JLabel("Password:");
        p.setBorder(new EmptyBorder(0,(x/8),0,0));
        p.setFont(new Font("Serif", Font.BOLD, 20));
        pc = new JLabel("Password confirmation:");
        pc.setBorder(new EmptyBorder(0,(x/8),0,0));
        pc.setFont(new Font("Serif", Font.BOLD, 20));

        usertxt = new JTextField(15);
        usertxt.setFont(new Font("Serif", Font.PLAIN, 20));
        passtxt = new JTextField(15);
        passtxt.setFont(new Font("Serif", Font.PLAIN, 20));
        emailtxt = new JTextField(15);
        emailtxt.setFont(new Font("Serif", Font.PLAIN, 20));
        passconftxt = new JTextField(15);
        passconftxt.setFont(new Font("Serif", Font.PLAIN, 20));
        submit = new JButton("SUBMIT");
        back = new JButton("BACK");
        submit.setActionCommand(BTN_SUBMIT);
        submit.setFont(new Font("Serif", Font.BOLD, 20));
        back.setActionCommand(BTN_BACK);
        back.setFont(new Font("Serif", Font.BOLD, 20));

        new JPanel();
        setLayout(new GridLayout(5, 1));
        setBorder(new EmptyBorder((y/4),(x/8),(y/4),(x/8)));
        add(u);
        add(usertxt);
        add(e);
        add(emailtxt);
        add(p);
        add(passtxt);
        add(pc);
        add(passconftxt);
        add(submit);
        add(back);
    }

    public void resetTxt(){
        passconftxt.setText("");
        passtxt.setText("");
    }
    public void resetDataTxt(){
        usertxt.setText("");
        emailtxt.setText("");
        resetTxt();
    }

    public String getUserTxt(){ return usertxt.getText(); }
    public String getPassTxt(){ return passtxt.getText(); }
    public String getPassConfTxt(){ return passconftxt.getText(); }
    public String getEmailTxt(){ return emailtxt.getText(); }
    public void showWelcome(){ JOptionPane.showMessageDialog(null, "Welcome " + usertxt.getText() + " !"); }
    public void showWrongPassword(){ JOptionPane.showMessageDialog(null, "Password does not match its confirmation. Please try again", "Error", JOptionPane.ERROR_MESSAGE); }
    public void showWrongUsername(){ JOptionPane.showMessageDialog(null, "Username or email already exist. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);}

    public void setMainCnt(MainFrameController mfc){
        this.mfc = mfc;
    }

    public void showMainFrame(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"MainFrame");
    }

    public void showMainMenu(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"MainMenu");
    }

    public void registerController(ActionListener suc){
        this.back.addActionListener(suc);
        this.submit.addActionListener(suc);
    }
}
