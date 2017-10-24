import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainFrame extends JFrame{
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmfF = new JMenu("File");
    private JMenu jmfS = new JMenu("Set");
    private JMenu jmfG = new JMenu("Game");
    private JMenu jmfA = new JMenu("About");
    private JMenuItem jmFexit = new JMenuItem("Exit");
    private JMenuItem jmGlotto = new JMenuItem("Lotto");
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 600,frmH = 450;
    private JDesktopPane jdp = new JDesktopPane();
    private JInternalFrame jif = new JInternalFrame();
    private Container jifCP;
    private JPanel jpn = new JPanel(new GridLayout(1,6,5,5));
    private JPanel jpn1 = new JPanel(new GridLayout(1,2,5,5));
    private JLabel jlbs[] = new JLabel[6];
    private int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());
    private JButton jbtnClose = new JButton("Close");
    private JButton jbtnRegen = new JButton("Regen");
    private LoginFrame loginFrame;

    public MainFrame(){
        init();
    }
    public void init(){
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        jifCP=getContentPane();
        
        jmb.add(jmfF);
        jmfF.add(jmFexit);
        jmFexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        jmFexit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmb.add(jmfS);
        jmb.add(jmfG);
        jmfG.add(jmGlotto);
        jmGlotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jif.setBounds(0,0,300,100);
                jdp.add(jif);
                jif.setVisible(true);
            }
        });
        jmb.add(jmfA);
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
                jlbs[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
}

