import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.InputContext;
import java.util.ArrayList;
import java.util.TimerTask;

public class GUIKeysWin extends JFrame{

    protected JFrame keysWin;
    protected JPanel mainPan;
    protected JPanel lettersPan;
    protected JPanel extraKeysPan;
    protected Color numbersColor;
    protected Color choosedRowClr;

    protected Border border;

    public static ArrayList <JLabel> jLabelsArrayList;
    protected ArrayList <JLabel> numLabelsArrayList;
    public static ArrayList <JLabel> spareJLabelsArrayList;

    protected GridLayout gridLayoutWin;
    protected JLabel fRLbl;
    protected JLabel qLbl;
    protected JLabel wLbl;
    protected JLabel eLbl;
    protected JLabel rLbl;
    protected JLabel tLbl;
    protected JLabel yLbl;
    protected JLabel uLbl;
    protected JLabel iLbl;
    protected JLabel oLbl;
    protected JLabel pLbl;

    protected JLabel sRLbl;
    protected JLabel aLbl;
    protected JLabel sLbl;
    protected JLabel dLbl;
    protected JLabel fLbl;
    protected JLabel gLbl;
    protected JLabel hLbl;
    protected JLabel jLbl;
    protected JLabel kLbl;
    protected JLabel lLbl;
    protected JLabel peySLbl;

    protected JLabel tRLbl;
    protected JLabel zLbl;
    protected JLabel xLbl;
    protected JLabel cLbl;
    protected JLabel vLbl;
    protected JLabel bLbl;
    protected JLabel nLbl;
    protected JLabel mLbl;
    protected JLabel tafLbl;
    protected JLabel zadikSLbl;
    protected JLabel slashLbl;

    protected JLabel spaceLbl;
    protected JLabel sprLbl1;
    protected JLabel sprLbl2;
    protected JLabel sprLbl3;
    protected JLabel sprLbl4;

    protected Timer timer;
    protected TimerTask task;

    public GUIKeysWin (){
        SetInputLang.currentLang = InputContext.getInstance().getLocale().toString();

        this.keysWin = new JFrame("Easy type");
        this.keysWin.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.keysWin.setSize(500, 300);
        this.keysWin.setAlwaysOnTop(true);
        this.mainPan = new JPanel();
        this.mainPan.setLayout(new BoxLayout(this.mainPan, BoxLayout.Y_AXIS));
        this.lettersPan = new JPanel();
        this.extraKeysPan = new JPanel();
        this.extraKeysPan.setLayout(new BoxLayout(this.extraKeysPan, BoxLayout.LINE_AXIS));
        this.mainPan.add(this.lettersPan);
        this.mainPan.add(this.extraKeysPan);
        this.keysWin.add(this.mainPan);
        this.lettersPan.setBackground(Color.white);
        this.choosedRowClr = new Color(211, 0, 0);
        spareJLabelsArrayList = new ArrayList<JLabel>();

        this.border = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        this.numbersColor = new Color(0, 0, 124);
        this.fRLbl = new JLabel("1", SwingConstants.CENTER);

        this.qLbl = new JLabel("q", SwingConstants.CENTER);
        this.wLbl = new JLabel("w", SwingConstants.CENTER);
        this.eLbl = new JLabel("e", SwingConstants.CENTER);
        this.rLbl = new JLabel("r", SwingConstants.CENTER);
        this.tLbl = new JLabel("t", SwingConstants.CENTER);
        this.yLbl = new JLabel("y", SwingConstants.CENTER);
        this.uLbl = new JLabel("u", SwingConstants.CENTER);
        this.iLbl = new JLabel("i", SwingConstants.CENTER);
        this.oLbl = new JLabel("o", SwingConstants.CENTER);
        this.pLbl = new JLabel("p", SwingConstants.CENTER);


        this.sRLbl = new JLabel("2", SwingConstants.CENTER);

        this.aLbl = new JLabel("a", SwingConstants.CENTER);
        this.sLbl = new JLabel("s", SwingConstants.CENTER);
        this.dLbl = new JLabel("d", SwingConstants.CENTER);
        this.fLbl = new JLabel("f", SwingConstants.CENTER);
        this.gLbl = new JLabel("g", SwingConstants.CENTER);
        this.hLbl = new JLabel("h", SwingConstants.CENTER);
        this.jLbl = new JLabel("j", SwingConstants.CENTER);
        this.kLbl = new JLabel("k", SwingConstants.CENTER);
        this.lLbl = new JLabel("l", SwingConstants.CENTER);
        this.peySLbl = new JLabel(";", SwingConstants.CENTER);


        this.tRLbl = new JLabel("3", SwingConstants.CENTER);

        this.zLbl = new JLabel("z", SwingConstants.CENTER);
        this.xLbl = new JLabel("x", SwingConstants.CENTER);
        this.cLbl = new JLabel("c", SwingConstants.CENTER);
        this.vLbl = new JLabel("v", SwingConstants.CENTER);
        this.bLbl = new JLabel("b", SwingConstants.CENTER);
        this.nLbl = new JLabel("n", SwingConstants.CENTER);
        this.mLbl = new JLabel("m", SwingConstants.CENTER);
        this.tafLbl = new JLabel(",", SwingConstants.CENTER);
        this.zadikSLbl = new JLabel(".", SwingConstants.CENTER);
        this.slashLbl = new JLabel("/", SwingConstants.CENTER);

        this.spaceLbl = new JLabel("Space", SwingConstants.CENTER);
        this.sprLbl1 = new JLabel("", SwingConstants.CENTER);
        this.sprLbl2 = new JLabel("", SwingConstants.CENTER);
        this.sprLbl3 = new JLabel("", SwingConstants.CENTER);
        this.sprLbl4 = new JLabel("", SwingConstants.CENTER);

        this.jLabelsArrayList = new ArrayList<JLabel>();

        this.jLabelsArrayList.add(this.fRLbl);

        this.jLabelsArrayList.add(this.qLbl);
        this.jLabelsArrayList.add(this.wLbl);
        this.jLabelsArrayList.add(this.eLbl);
        this.jLabelsArrayList.add(this.rLbl);
        this.jLabelsArrayList.add(this.tLbl);
        this.jLabelsArrayList.add(this.yLbl);
        this.jLabelsArrayList.add(this.uLbl);
        this.jLabelsArrayList.add(this.iLbl);
        this.jLabelsArrayList.add(this.oLbl);
        this.jLabelsArrayList.add(this.pLbl);

        this.jLabelsArrayList.add(this.sRLbl);

        this.jLabelsArrayList.add(this.aLbl);
        this.jLabelsArrayList.add(this.sLbl);
        this.jLabelsArrayList.add(this.dLbl);
        this.jLabelsArrayList.add(this.fLbl);
        this.jLabelsArrayList.add(this.gLbl);
        this.jLabelsArrayList.add(this.hLbl);
        this.jLabelsArrayList.add(this.jLbl);
        this.jLabelsArrayList.add(this.kLbl);
        this.jLabelsArrayList.add(this.lLbl);
        this.jLabelsArrayList.add(this.peySLbl);

        this.jLabelsArrayList.add(this.tRLbl);

        this.jLabelsArrayList.add(this.zLbl);
        this.jLabelsArrayList.add(this.xLbl);
        this.jLabelsArrayList.add(this.cLbl);
        this.jLabelsArrayList.add(this.vLbl);
        this.jLabelsArrayList.add(this.bLbl);
        this.jLabelsArrayList.add(this.nLbl);
        this.jLabelsArrayList.add(this.mLbl);
        this.jLabelsArrayList.add(this.tafLbl);
        this.jLabelsArrayList.add(this.zadikSLbl);
        this.jLabelsArrayList.add(this.slashLbl);

        this.numLabelsArrayList = new ArrayList<JLabel>();
        for (int i=0; this.numLabelsArrayList.size()<30; i++) {
            if (i == 10){
                i = 0;
            }
            JLabel lbl1 = new JLabel(Integer.toString(i));
            lbl1.setFont(new Font("Serif", Font.BOLD, 18));
            lbl1.setSize(30, 20);
            lbl1.setForeground(Color.GRAY);
            this.numLabelsArrayList.add(lbl1);
            }
            int i = 0;

        for (JLabel lbl: this.jLabelsArrayList) {
            lbl.setFont(new Font("Serif", Font.BOLD, 24));
            lbl.setSize(60, 40);
            lbl.setOpaque(true);
            if (this.jLabelsArrayList.indexOf(lbl) == 0 || this.jLabelsArrayList.indexOf(lbl) == 11|| this.jLabelsArrayList.indexOf(lbl) == 22){
                lbl.setForeground(Color.black);
                lbl.setBackground(Color.white);
            }
            else {
                lbl.setBackground(Color.black);
                lbl.setForeground(Color.white);
                lbl.setText(lbl.getText());
                lbl.add(this.numLabelsArrayList.get(i));
                i++;
            }
            this.lettersPan.add(lbl);
        }

        for (i = 0; i<10; i++)
        {
            spareJLabelsArrayList.add(i == 5 ? new JLabel("Space", SwingConstants.CENTER) : i == 0 ? new JLabel("4", SwingConstants.CENTER) : new JLabel(Integer.toString(i), SwingConstants.CENTER));
            spareJLabelsArrayList.get(i).setFont(new Font("Serif", Font.BOLD, 24));
            if (i == 5){
                spareJLabelsArrayList.get(i).setSize(90, 40);
            }
            else
            {
                spareJLabelsArrayList.get(i).setSize(200, 40);

            }
            spareJLabelsArrayList.get(i).setOpaque(true);
            spareJLabelsArrayList.get(i).setBackground(i == 0 ? Color.white : Color.black);
            spareJLabelsArrayList.get(i).setForeground(i == 0  ? Color.black : Color.white);
            this.extraKeysPan.add(spareJLabelsArrayList.get(i));
        }            this.extraKeysPan.setBorder( this.border);

        this.gridLayoutWin = new GridLayout(3, 11, 5, 5);
        this.lettersPan.setLayout(this.gridLayoutWin);
        this.keysWin.setVisible(true);

        this.timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i=0;
                for (JLabel lbl: jLabelsArrayList) {
                    if (jLabelsArrayList.indexOf(lbl) != 0 && jLabelsArrayList.indexOf(lbl) != 11 && jLabelsArrayList.indexOf(lbl) != 22) {
                        lbl.setBackground(Color.black);
                    }
                    else {
                        continue;
                    }
                }
                for (JLabel lbl: numLabelsArrayList){
                    lbl.setForeground(Color.GRAY);
                }
                timer.stop();
            }
        });
    }

    public void choosedRowBgClrCh(int rowNum){

        for (int i = rowNum -10 + (11*rowNum) - rowNum; i < 11 * rowNum; i++){
            this.jLabelsArrayList.get(i).setBackground(this.choosedRowClr);
            this.numLabelsArrayList.get(i - rowNum).setForeground(this.numbersColor);
            }
            this.keysWin.repaint();
    }

    public void chooseKeyBgClrch(int rowNum, int keyNum, ArrayList <Integer> keysCodes){
        int keyCodeInd = keysCodes.indexOf(keyNum);
        this.jLabelsArrayList.subList(rowNum -10 + (11*rowNum) - rowNum, 11 * rowNum).get(keyCodeInd).setBackground(Color.blue);
        this.timer.start();

    }

    public void changeLblsLetters(){
        for (JLabel lbl: this.jLabelsArrayList) {
            lbl.setText(SetInputLang.chars.get(this.jLabelsArrayList.indexOf(lbl)));
        }
    }

}