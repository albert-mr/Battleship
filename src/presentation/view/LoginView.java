package presentation.view;

import presentation.controller.MainFrameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JPanel{

    private JLabel jlusername, jlpassword;
    private JTextField jtusertxt;
    private JPasswordField jppasstxt;
    private JButton jbsubmit, jbback;
    public static final String BTN_SUBMIT = "BTN_SUBMIT";
    public static final String BTN_BACK = "BTN_BACK";
    private MainFrameController mfc;

    public LoginView(){
        configureView();
    }

    private void configureView(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) (screenSize.width);
        y = (int) (screenSize.height);

        jlusername = new JLabel("Username: ");
        jlusername.setBorder(new EmptyBorder(0,(x/8),0,0));
        jlusername.setFont(new Font("Serif", Font.BOLD, 20));
        jlpassword = new JLabel("Password: ");
        jlpassword.setBorder(new EmptyBorder(0,(x/8),0,0));
        jlpassword.setFont(new Font("Serif", Font.BOLD, 20));
        jtusertxt = new JTextField(15);
        jtusertxt.setFont(new Font("Serif", Font.PLAIN, 20));
        jppasstxt = new JPasswordField(15);
        jppasstxt.setEchoChar('*');
        jbsubmit = new JButton("SUBMIT");
        jbsubmit.setFont(new Font("Serif", Font.BOLD, 20));
        jbback = new JButton("BACK");
        jbback.setFont(new Font("Serif", Font.BOLD, 20));

        //set buttons' action commands
        jbsubmit.setActionCommand(BTN_SUBMIT);
        jbback.setActionCommand(BTN_BACK);

        //define panel and add components
        new JPanel();
        setLayout(new GridLayout(3, 1));
        setBorder(new EmptyBorder((y/3),(x/8),(y/3),(x/8)));
        add(jlusername);
        add(jtusertxt);
        add(jlpassword);
        add(jppasstxt);
        add(jbsubmit);
        add(jbback);
    }

    public void resetTxt(){
        jtusertxt.setText("");
        jppasstxt.setText("");
    }
    public void showWelcome(){ JOptionPane.showMessageDialog(null, "Welcome " + jtusertxt.getText() + " !"); }
    public String getUserTxt(){ return jtusertxt.getText(); }
    public String getPassTxt(){ return String.valueOf(jppasstxt.getPassword()); }
    public void showWrongLogin(){ JOptionPane.showMessageDialog(null, "Wrong username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);}

    public void registerController(ActionListener lc){
        this.jbsubmit.addActionListener(lc);
        this.jbback.addActionListener(lc);
    }

    public void setMainCnt(MainFrameController mfc){
        this.mfc = mfc;
    }

    public void showMainFrame(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"MainFrame");
    }

    public void showMainMenu(){
        this.mfc.getCl().show(this.mfc.getMainPanel(),"MainMenu");
    }
}
