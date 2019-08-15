package ManageKeyboardTapAndPerformClicks.mainWinGUIManage;

import LanguageManagePackage.SetInputLang;
import OptionsAndSystemTray.OptionsWin;
import ManageKeyboardTapAndPerformClicks.CatchClickedKeys;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.im.InputContext;
import java.util.ArrayList;
import java.util.Arrays;

public class MainlyGUIWin extends JFrame{
    public static JFrame mainlyKeysJFrame;
    protected JPanel mainPan;
    public static JPanel lettersPan;
    protected JPanel spaceLblPan;
    public static Color numbersColor;
    protected Color selectedRowColor;

    public static int winWidth;
    public static int winHeight;
    protected int letterLblWidth;
    protected int lettersLblHeight;
    protected int lettersFontSize;
    protected int numbersFontSize;
    protected int numLblsWidth;
    protected int numLblsHeight;
    protected static int specialActionsLblsWidth;
    protected static int specialActionsLblsHeight;

    protected int spaceLblWidth;
    protected int spaceLblHeight;
    public static int specialLastLblsPos = 0;
    public static int spaceBetweenSpecialLbls;
    public static ArrayList <CustomJLabel> threeRowsJLabelsArrayList;
    public static ArrayList <CustomJLabel> lettersXIndexesArrayList;
    public static ArrayList <String> helpBalloonTipTextsArrList = new ArrayList<String>(Arrays.asList(" row number (to make type letter of this row press numpad ", "row (special actions), tap on numpad 8 key when no on row selected ", "Space key (to make space type tap on numpad 5 wheen no one row is selected"));
    protected static GridLayout gridLayoutWin;
    public static JLabel spaceLbl;
    protected JLabel spaceIndexLbl;
    protected Timer resetAllJlabelsColorTimer;

    protected BoxLayout generalLayout;

    protected Border border;
    public static Color rowNumsColor;
    public static JPanel specialActionsPan;
    public static ArrayList <JLabel> specialActionsLblsArrayList;
    protected GridLayout specialActionsLayout;

    protected JLabel specialRowNumLbl;
    protected JLabel copyLbl;
    protected JLabel cutLbl;
    protected JLabel pasteLbl;
    protected JLabel menuLbl;
    protected JLabel undoLbl;
    protected JLabel redoLbl;
    protected JLabel prtScLbl;
    protected JLabel changeWinSizeLbl;
    protected JLabel mvDnLbl;
    protected JLabel mvUpLbl;
    public static Shape winShape;
    protected ArrayList <JLabel> specialActionsIndexes;
    protected int mouseXLocation;
    protected int mouseYLocation;
    public static Dimension screenSize;
    public MainlyGUIWin(){
        SetInputLang.setSameInputMethodForAllApps();
        //set current system input lang
        SetInputLang.currentInputLang = InputContext.getInstance().getLocale().toString();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Set window width and height adjust to the computer screen resolution
        this.winWidth = (int) ((screenSize.getWidth() * 36.603221083455344070278184480234) / 100);
        this.winHeight = (int) ((screenSize.getHeight() * 42) / 100);
        //Set letters labels width and height adjust to the computer screen resolution
        this.letterLblWidth = (int) ((screenSize.getWidth() *
                4.3923865300146412884333821376281) / 100);
        this.lettersLblHeight = (int) ((screenSize.getHeight() *
                5.2083333333333333333333333333333) / 100);
        //Set letters labels font size adjust to the computer screen resolution
        this.lettersFontSize = (int) ((screenSize.getWidth() *
                1.7569546120058565153733528550512) / 100);
        //Set numbers labels font size adjust to the computer screen resolution
        this.numbersFontSize = (int) ((screenSize.getWidth() *
                1.3177159590043923865300146412884) / 100);
        //Set numbers labels width and height adjust to the computer screen resolution
        this.numLblsWidth = (int) ((screenSize.getWidth() * 2.1961932650073206442166910688141) / 100);
        this.numLblsHeight = (int) ((screenSize.getHeight() *
                2.6041666666666666666666666666667) / 100);
        //Set special actions labels width and height adjust to the computer screen resolution
        specialActionsLblsWidth = (int) ((screenSize.getWidth() *
                2.9282576866764275256222547584187) / 100);
        specialActionsLblsHeight = (int) ((screenSize.getHeight() *
                4.5572916666666666666666666666667) / 100);
        //Set spaceLbl label width and height adjust to the computer screen resolution
        this.spaceLblWidth = (int) ((screenSize.getWidth() * 7.3206442166910688140556368960469) / 100);
        this.spaceLblHeight = (int) ((screenSize.getHeight() *
                5.2083333333333333333333333333333) / 100);
        //Setting Up the win (JFrame) properties
        mainlyKeysJFrame = new JFrame("Easy type");
        mainlyKeysJFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainlyKeysJFrame.setSize(this.winWidth, this.winHeight);
        mainlyKeysJFrame.setLocation(100, 100);
        mainlyKeysJFrame.setAlwaysOnTop(true);
        mainlyKeysJFrame.setUndecorated(true);

        winShape = new RoundRectangle2D.Double(0,0, mainlyKeysJFrame.getWidth(), mainlyKeysJFrame.getHeight(), 20, 20);
        mainlyKeysJFrame.setShape(winShape);
        //Setting Up the panels and some of layouts that will be added to the win (JFrame)
        this.mainPan = new JPanel();
        this.generalLayout = new BoxLayout(this.mainPan, BoxLayout.Y_AXIS);
        this.mainPan.setLayout(this.generalLayout);
        lettersPan = new JPanel();
        this.spaceLblPan = new JPanel();
        this.mainPan.add(lettersPan);
        this.mainPan.add(this.spaceLblPan);
        mainlyKeysJFrame.add(this.mainPan);
        lettersPan.setBackground(Color.white);
        this.spaceLblPan.setBackground(Color.white);
        this.selectedRowColor = new Color(211, 0, 0);
        rowNumsColor = new Color(13, 158, 158);
        //create and set up empty border to the letters panel, to have spaceLbl between letters labels and the win edges
        border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        lettersPan.setBorder(this.border);
        //initialize the special keys panel
        specialActionsPan = new JPanel();
        specialActionsPan.setBackground(Color.white);
        specialActionsLayout = new GridLayout(1, 11, 5, 5);
        specialActionsLblsArrayList = new ArrayList<JLabel>();
        specialActionsPan.setLayout(null);
        //set Up the spaceLbl between the special labels adjust to the computer screen resolution
        spaceBetweenSpecialLbls = (int) ((screenSize.getWidth() * 0.36603221083455344070278184480234) / 100);
        //Setting Up the special labels text and add them to special keys array list
        specialActionsLblsArrayList.add(this.specialRowNumLbl = new JLabel("8", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.copyLbl = new JLabel("⎘", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.cutLbl = new JLabel("✂", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.pasteLbl = new JLabel("✎", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.menuLbl = new JLabel("⌸", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.undoLbl = new JLabel("⤾", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.redoLbl = new JLabel("⤿", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.prtScLbl = new JLabel("\uD83D\uDCF7\n", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.changeWinSizeLbl = new JLabel("⤡", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.mvDnLbl = new JLabel("Mv Up", SwingConstants.CENTER));
        specialActionsLblsArrayList.add(this.mvUpLbl = new JLabel("Mv Dn", SwingConstants.CENTER));
        //initialize the array list that will store the numbers labels of the special labels
        this.specialActionsIndexes = new ArrayList<>();
        //Setting Up the special labels size, background and text color
        int i = 0;
        for (JLabel lbl: specialActionsLblsArrayList) {
            lbl.setSize(specialActionsLblsWidth, specialActionsLblsHeight);
            lbl.setOpaque(true);
            lbl.setBackground(lbl.getText().equals("8") ? rowNumsColor : Color.black);
            lbl.setForeground(lbl.getText().equals("8") ? Color.black : Color.white);
            //create the numbers labels that will be shown on the special labels, set their size, font and
            // text color
            if (!lbl.getText().equals("8")) {
                JLabel numLbl = new JLabel(String.valueOf(i++));
                numLbl.setSize(this.numLblsWidth, this.numLblsHeight);
                numLbl.setFont(new Font("Serif", Font.BOLD, this.numbersFontSize));
                numLbl.setForeground(Color.GRAY);
                //adding the number label to the specialNumsArrayList
                this.specialActionsIndexes.add(numLbl);
                //adding the numbers labels to the special labels
                lbl.add(numLbl);
                //set the special label invisible - visibility to false
                lbl.setVisible(false);
            }
            //set the font size of the special labels
            if (lbl.getText().equals("Mv Dn") || lbl.getText().equals("Mv Up")) {
                lbl.setFont(new Font("Serif", Font.BOLD, 8));
            }
            // if it is a 88 row number JLabel check if the option show help balloon tips turned on add balloon tip
            // when mouse cursor is hover this lbl
            else {
                lbl.setFont(new Font("Serif", Font.BOLD, this.lettersFontSize));
            }
        }
        //set the location of the first label (the label that hims text is 8)
        this.specialActionsLblsArrayList.get(0).setLocation(5, 0);
        // the color object that will be the text color of numbers JLabels
        this.numbersColor = new Color(0, 0, 124);
        // create and initialize the number JLabels (which will shown in letters JLabels of the 3 rows) and add them
        // to lettersXIndexesArrayList
        lettersXIndexesArrayList = new ArrayList<>();
        for (i=0; lettersXIndexesArrayList.size()<30; i++) {
            if (i == 10){
                i = 0;
            }
            CustomJLabel numLbl = new CustomJLabel(" " + Integer.toString(i), this.numLblsWidth,
                    this.numLblsHeight, new Font("Serif", Font.BOLD, this.numbersFontSize), Color.GRAY);
            lettersXIndexesArrayList.add(numLbl);
        }
        int j = 0;
        // create and initialize the JLabels of 1-3 letters rows and add them to threeRowsJLabelsArrayList
        threeRowsJLabelsArrayList = new ArrayList<CustomJLabel>();
        int rowNum = 1;
        for (i = 0; i < 33; i++) {
            CustomJLabel lbl;
            if (i == 0 || i == 11 || i == 22) {
                lbl = new CustomJLabel(String.valueOf(rowNum), letterLblWidth, lettersLblHeight,
                        true, new Font("Serif", Font.BOLD, this.lettersFontSize), rowNumsColor,
                        Color.BLACK, SwingConstants.CENTER);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = toolkit.getImage("help_cursor.png");
                Cursor a = toolkit.createCustomCursor(image , new Point(this.getX(),this.getY()), "");
                lbl.setCursor(a);
            }
            else {
                lbl = new CustomJLabel("", letterLblWidth, lettersLblHeight, true,
                        new Font("Serif", Font.BOLD, this.lettersFontSize), Color.BLACK, Color.WHITE,
                        SwingConstants.CENTER);
                lbl.add(this.lettersXIndexesArrayList.get(j++));
            }
            threeRowsJLabelsArrayList.add(lbl);
            lettersPan.add(lbl);
        }
        // add mouse motion listener: when the user will tap on mouse left button down and dragging the mouse move
        // him move the window consistently
        mainlyKeysJFrame.addMouseMotionListener(new MouseAdapter() {
            @Override
            // when the mouse moves in the Easy Type main window: for move the Easy Type win consistently when mouse
            // left button will tapped down and when the mouse will be dragged
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mouseXLocation = e.getX();
                mouseYLocation = e.getY();
            }
            // when the mouse dragged while the left mouse button tapped down move the window consistently to the side
            // which the mouse moved t: whereby the difference of position it was before clicking and dragging to the
            // location where it is now
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                mainlyKeysJFrame.setLocation(mainlyKeysJFrame.getX()+e.getX()- mouseXLocation,
                        mainlyKeysJFrame.getY()+(e.getY()- mouseYLocation));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
        });
        // add the special actions JLabels to special keys JPanel
        for (JLabel lbl : specialActionsLblsArrayList) {
            specialActionsPan.add(lbl);
        }
        this.spaceLbl = new JLabel("Space", SwingConstants.CENTER);
        this.spaceIndexLbl = new JLabel(" 5");
        this.spaceIndexLbl.setFont(new Font("Serif", Font.BOLD, this.numbersFontSize));
        this.spaceIndexLbl.setForeground(this.numbersColor);
        this.spaceIndexLbl.setSize(this.numLblsWidth, this.numLblsHeight);
        this.spaceLbl.setFont(new Font("Serif", Font.BOLD, this.lettersFontSize - 4));
        this.spaceLbl.setOpaque(true);
        this.spaceLbl.setPreferredSize(new Dimension(this.spaceLblWidth, this.spaceLblHeight));
        this.spaceLbl.setBackground(Color.orange);
        this.spaceLbl.setForeground(new Color(4, 77, 71));
        this.spaceLbl.add(this.spaceIndexLbl);
        this.spaceLblPan.add(spaceLbl, BorderLayout.CENTER);
        this.lettersPan.setPreferredSize(new Dimension(this.winWidth, (int) (this.winHeight*0.5)));
        this.gridLayoutWin = new GridLayout(3, 11, 5, 5);
        this.lettersPan.setLayout(this.gridLayoutWin);

        this.mainPan.add(this.specialActionsPan);
        mainlyKeysJFrame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        this.mainlyKeysJFrame.setVisible(true);
        // resetAllJlabelsColorTimer which returning the color of all rows (JLabels) background colors to them default background colors
        // after delay of 500 milli seconds
        this.resetAllJlabelsColorTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (OptionsWin.lockSelectedRow && CatchClickedKeys.ind != -1) {
                    selectedRowBgClrCh(CatchClickedKeys.ind);
                    resetAllJlabelsColorTimer.stop();
                    return;
                }
                for (JLabel lbl: threeRowsJLabelsArrayList) {
                    if (lbl.getBackground() != rowNumsColor) {
                        lbl.setBackground(Color.black);
                    }
                    else {
                        continue;
                    }
                    spaceLbl.setBackground(Color.orange);
                }
                for (JLabel lbl: lettersXIndexesArrayList){
                    lbl.setForeground(Color.GRAY);
                }
                for (int i = 1; i < 11; i++) {
                    specialActionsLblsArrayList.get(i).setBackground(Color.black);
                    specialActionsIndexes.get(i-1).setForeground(Color.GRAY);
                }
                if (specialActionsLblsArrayList.get(1).isVisible() && !OptionsWin.extraLblsOnOff) {
                    hideSpecialActionsLbls();
                }
                resetAllJlabelsColorTimer.stop();
            }
        });
    }

    private static void addToolTipToComponent(JLabel jLabel, String text) {
        jLabel.setToolTipText(text);
    }

    private static void removeToolTipFromJlabel(JLabel jLabel) {
        jLabel.setToolTipText("");
    }

    public static void enableHelpBalloonTips() {
        int rowsNum = gridLayoutWin.getRows();
        while (rowsNum-- != 0)
            addToolTipToComponent(threeRowsJLabelsArrayList.get(rowsNum * 11), helpBalloonTipTextsArrList.get(rowsNum));
    }
    public static void disableHelpBalloonTips() {
        int rowsNum = gridLayoutWin.getRows();
        while (rowsNum-- != 0)
            removeToolTipFromJlabel(threeRowsJLabelsArrayList.get(rowsNum * 11));
        removeToolTipFromJlabel(specialActionsLblsArrayList.get(0));
        removeToolTipFromJlabel(spaceLbl);
    }
    // set the selected row (1-3) background color to red (by type using 0-9 keys typing method or by using scanning
    // keys typing method)
    protected void selectedRowBgClrCh(int rowNum){
        for (int i = rowNum -10 + (11*rowNum) - rowNum; i < 11 * rowNum; i++){
            this.threeRowsJLabelsArrayList.get(i).setBackground(this.selectedRowColor);
            this.lettersXIndexesArrayList.get(i - rowNum).setForeground(numbersColor);
            }
            this.mainlyKeysJFrame.repaint();
    }
    // set the background color of the selected (by type using 0-9 keys typing method or by using scanning keys typing
    // method) letter to be typed in the 4 row to blue

    // set the background color of the selected (by type using 0-9 keys typing method or by using scanning keys typing
    // method) letter to be typed in the selected row (1-3) to blue
    protected void selectedKeyBgClrCh(int rowNum, int keyNum, ArrayList <Integer> keysCodes){
        int keyCodeInd = keysCodes.indexOf(keyNum);
        this.threeRowsJLabelsArrayList.subList(rowNum -10 + (11*rowNum) - rowNum, 11 * rowNum).get(keyCodeInd).setBackground(Color.blue);
        this.resetAllJlabelsColorTimer.start();
    }
    public static void setLettersRowsNum(int rowsNum) {
        int rowsToAdd = 0;
        if (rowsNum > gridLayoutWin.getRows()) rowsToAdd = rowsNum - gridLayoutWin.getRows();
        if (rowsToAdd != 0) {
            Font lettersLblsFont = threeRowsJLabelsArrayList.get(0).getFont();
            int lettersLblsWidth = threeRowsJLabelsArrayList.get(0).getWidth();
            int lettersLblsHeight = threeRowsJLabelsArrayList.get(0).getHeight();
            Boolean lettersLblsOpaque = threeRowsJLabelsArrayList.get(0).isOpaque();

            Font numsLblsFont = lettersXIndexesArrayList.get(0).getFont();
            int numsLblsWidth = lettersXIndexesArrayList.get(0).getWidth();
            int numsLblsHeight = lettersXIndexesArrayList.get(0).getHeight();

            int j = 0;
            for (int i = 0; i< rowsToAdd * 11; i++) {
                threeRowsJLabelsArrayList.add(new CustomJLabel(i % 11 == 0 ? String.valueOf(i / 11 + rowsNum - rowsToAdd + 1) : "",
                        lettersLblsWidth, lettersLblsHeight, lettersLblsOpaque, lettersLblsFont,
                        i % 11 == 0 ? rowNumsColor : Color.BLACK, i % 11 == 0 ? Color.BLACK : Color.WHITE, SwingConstants.CENTER));
                if (i % 11 != 0) {
                    lettersXIndexesArrayList.add(new CustomJLabel(String.valueOf(j), numsLblsWidth, numsLblsHeight,
                            numsLblsFont, Color.gray));
                    threeRowsJLabelsArrayList.get(threeRowsJLabelsArrayList.size() - 1).
                            add(lettersXIndexesArrayList.get(lettersXIndexesArrayList.size() - 1));
                    j = j == 9 ? 0 : j + 1;
                }
                lettersPan.add(threeRowsJLabelsArrayList.get(threeRowsJLabelsArrayList.size() - 1));
            }
        } else if (rowsNum < gridLayoutWin.getRows()) {
            for (int i = 0; i < (gridLayoutWin.getRows() - rowsNum) * 11; i++) {
                lettersPan.remove(threeRowsJLabelsArrayList.get(threeRowsJLabelsArrayList.size() - 1));
                threeRowsJLabelsArrayList.remove(threeRowsJLabelsArrayList.size() - 1);
            }
        }
        gridLayoutWin.setRows(rowsNum);
        mainlyKeysJFrame.repaint();
        mainlyKeysJFrame.validate();
    }

    // set the background color of the scanned now letter (JLabel) in the selected row (1-3) to green (when type using
    // scanning keys mode is turned on)
    protected void scanRowKeysBgClrCh(int rowNum, int keyNum){
        this.threeRowsJLabelsArrayList.subList(rowNum -10 + (11*rowNum) - rowNum, 11 * rowNum).get(keyNum).setBackground(Color.green);
    }
    // change the spaceLbl label background color to blue when the user choosing to type spaceLbl (tapping on 5 key when no
    // one row is selected)
    protected void changeSpaceLblColor(){
        MainlyGUIWin.spaceLbl.setBackground(Color.blue);
        this.resetAllJlabelsColorTimer.start();
    }
    // change the text on the 3 letters labels rows to special signs when the user switching to special signs type mode
    protected void setSpecialSignsKeys() {
        char i = 33;
        for (JLabel lbl: threeRowsJLabelsArrayList) {
            if (lbl.getText().equals("1") || lbl.getText().equals("2") || lbl.getText().equals("3")) {
                continue;
            }
            lbl.setText(String.valueOf(i++));
            if (i > 47 && (i == 48 || i == 65 || i == 97)) {
                i = (char) (i == 48 ? 58 : i == 65 ? 91 : 123);
            }
            else if (i == 43 || i == 45) {
                i++;
            }
        }
    }
    // change the background color of the special action row (special actions labels which in the special actions row)
    // to red
    protected void specialActionsLblsRowBgClrCh() {
        for (int i = 1; i < 11; i++) {
            this.specialActionsIndexes.get(i-1).setForeground(numbersColor);
            MainlyGUIWin.specialActionsLblsArrayList.get(i).setBackground(this.selectedRowColor);
        }
    }
    // change the color of the selected special action to red and start the resetAllJlabelsColorTimer that will rest this row labels
    // background adjustment to the lock row option value
    protected void specialActionsSelectedLblBgClrCh(int keyNum, ArrayList <Integer> keyCodes) {
        int lblInd = keyCodes.indexOf(keyNum) + 1;
        MainlyGUIWin.specialActionsLblsArrayList.get(lblInd).setBackground(Color.blue);
        this.resetAllJlabelsColorTimer.start();
    }
    // show special actions row (labels which in special actions row) by slide out effect
    public static void showSpecialActionsLbls() {
        int j = Integer.valueOf((int) ((screenSize.getWidth() * 0.36603221083455344070278184480234) / 100)) + 5;
        for (JLabel lbl : specialActionsLblsArrayList.subList(1,11)) {
            specialActionsPan.add(lbl);
            lbl.setVisible(true);
            new ShowSpecialActionsLbls(lbl, j).start();
            j = j + specialActionsLblsWidth + spaceBetweenSpecialLbls;
            }
            if (specialLastLblsPos == 0) {
                specialLastLblsPos = specialActionsLblsArrayList.get(specialActionsLblsArrayList.size() - 1).getX();
            }
    }
    // hide special actions row (labels which in special actions row) by slide in effect
    public static void hideSpecialActionsLbls() {
        int j = specialLastLblsPos;
        HideSpecialActionsLbls hideSpLbls;
        for (int i = 10; i > 0; i--) {
            hideSpLbls = new HideSpecialActionsLbls(specialActionsLblsArrayList.get(i));
            hideSpLbls.start();
            j = j - specialActionsLblsWidth - spaceBetweenSpecialLbls;
        }
    }
}