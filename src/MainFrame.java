import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame{
    private JMenuBar jmb = new JMenuBar();

    private JMenu jmfF = new JMenu("File");
    private JMenu jmfS = new JMenu("Set");
    private JMenu jmfG = new JMenu("Game");
    private JMenu jmfA = new JMenu("About");

    private JMenuItem jmiFexit = new JMenuItem("Exit");
    private JMenuItem jmiGlotto = new JMenuItem("Lotto");
    private JMenuItem jmiSetFont = new JMenuItem("Font");

    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 600,frmH = 450;

    private JDesktopPane jdp = new JDesktopPane();
    private JInternalFrame jif = new JInternalFrame();
    private Container cp;

    private JPanel jpn = new JPanel(new GridLayout(1,6,5,5));
    private JPanel jpn1 = new JPanel(new GridLayout(1,2,5,5));
    private JLabel jlb[] = new JLabel[6];
    private int data[] = new int[6];

    private JPanel jpanell = new JPanel(new GridLayout(2,3,5,5));
    private JLabel jlbFamily = new JLabel("Family");
    private JLabel jlbStyle = new JLabel("Style");
    private JLabel jlbSize = new JLabel("Size");
    private JTextField jtfFamily = new JTextField("");
    private JTextField jtfStyle = new JTextField("BOLD");
    private JTextField jtfSize = new JTextField("24");
    private String[] options = {"PLAIN","BOLD","ITALIC","BOLD+ITALIC"};
    private JComboBox jcbFStyle = new JComboBox(options);
    private Random rnd = new Random(System.currentTimeMillis());
    private JButton jbtnClose = new JButton("Close");
    private JButton jbtnRegen = new JButton("Regen");
    private LoginFrame loginFrame;

    public MainFrame(LoginFrame log){
        loginFrame=log;
        init();
    }
    public void init(){
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                loginFrame.reset();
                loginFrame.setVisible(true);
            }
        });
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        cp=jif.getContentPane();
        cp.setLayout(new BorderLayout(5,5));
        cp.add(jpn,BorderLayout.CENTER);
        cp.add(jpn1,BorderLayout.SOUTH);
        jpn1.add(jbtnClose);
        jpn1.add(jbtnRegen);

        jmb.add(jmfF);
        jmb.add(jmfS);
        jmb.add(jmfG);
        jmb.add(jmfA);

        jmfS.add(jmiSetFont);
        jmfG.add(jmiGlotto);
        jmfF.add(jmiFexit);

        jpanell.add(jlbFamily);
        jpanell.add(jlbStyle );
        jpanell.add(jlbSize);
        jpanell.add(jtfFamily);
        jpanell.add(jcbFStyle);
        jpanell.add(jtfSize);
        for(int i=0;i<6;i++){
            jlb[i]=new JLabel();
            jlb[i].setOpaque(true);
            jlb[i].setBackground(new Color(255,255,0));
            jpn.add(jlb[i]);
        }

        jmiFexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loginFrame.setVisible(true);
                dispose();
                loginFrame.reset();
            }
        });
        jmiFexit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiGlotto.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        jmiGlotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jdp.add(jif);
                jif.setBounds(20,20,300,100);
                jif.setVisible(true);
                lottoGenerate();
            }
        });
        jmiSetFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this,jpanell,null,JOptionPane.OK_CANCEL_OPTION);
                int fontstyle = 0;
                switch (jcbFStyle.getSelectedIndex()){
                    case 0:
                        fontstyle=Font.PLAIN;
                        break;
                    case 1:
                        fontstyle=Font.BOLD;
                        break;
                    case 2:
                        fontstyle=Font.ITALIC;
                        break;
                    case 3:
                        fontstyle=Font.BOLD+Font.ITALIC;
                        break;
                }
                if(result == JOptionPane.OK_OPTION){
                    UIManager.put("Menu.font",new Font(jtfFamily.getText(),fontstyle,Integer.parseInt(jtfSize.getText())));
                    UIManager.put("MenuItem.font",new Font(jtfFamily.getText(),fontstyle,Integer.parseInt(jtfSize.getText())));
                }
            }
        });
        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif.dispose();
            }
        });
        jbtnRegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lottoGenerate();
            }
        });
    }

    private void lottoGenerate(){
        int i =0;
        while(i<6){
            data[i]=rnd.nextInt(42)+1;
            int j =0;
            boolean flag = true;
            while(j<i && flag){
                if(data[i] == data[j]){
                    flag=false;
                }
                j++;
            }
            if(flag){
                jlb[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
}

