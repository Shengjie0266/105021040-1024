import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JLabel jlabID = new JLabel("ID");
    private JLabel jlabPW = new JLabel("Password");
    private JTextField jtfID = new JTextField();
    private JPasswordField jpfPW = new JPasswordField();
    private JButton jbtLogin = new JButton("Login");
    private JButton jbtExit = new JButton("Exit");
    private Container cp;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int frmW = 300,frmH = 150,screenW,screenH;

    public LoginFrame(){
        init();
    }
    public void init(){
        screenW=dim.width;
        screenH=dim.height;
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH,frmW,frmH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp=this.getContentPane();
        cp.setLayout(new GridLayout(3,2,3,3));
        cp.add(jlabID);
        cp.add(jtfID);
        cp.add(jlabPW);
        cp.add(jpfPW);
        cp.add(jbtLogin);
        cp.add(jbtExit);
        jlabID.setHorizontalAlignment(JLabel.RIGHT);
        jlabPW.setHorizontalAlignment(JLabel.RIGHT);
        jbtLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtfID.getText().equals("h304") && new String(jpfPW.getPassword()).equals("23323456")){
                    MainFrame mf = new MainFrame(LoginFrame.this);
                    mf.setVisible(true);
                    LoginFrame.this.setVisible(false);
                }else{
                    System.out.println("No" + jtfID.getText() + "\tpw");
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }
        });
        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void reset() {
        jtfID.setText("");
        jpfPW.setText("");

    }
}
