package ManageKeyboardTapAndPerformClicks;

import ManageKeyboardTapAndPerformClicks.mainWinGUIManage.MainlyGUIWin;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.im.InputContext;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class ManageKeybdLayoutsAndLangsWin {
    public static Boolean learningMode = false;
    private static String currSelectedLayout;
    private List<LayoutsEditDataModel> textFieldsLettersVKCodesPairList;
    private int winWidth;
    private int winHeight;
    private int lblFontSize;
    private JFrame jFrame;
    private JPanel mainPanel;
    private JPanel explainPan;
    private JPanel actionsPan;
    private JPanel lettersForLearningPan;
    private JPanel savedLangsActionsPan;
    private BorderLayout generalLayout;
    private GridLayout gridLayout;
    private JLabel label;
    private JButton saveLangBtn;
    private JButton resetInsertedDataBtn;
    private FlowLayout btnsLayout;
    public static int currVKCode = 0;
    private JButton addRowBtn;
    private JButton removeRowBtn;
    private Color btnsBgClr;
    private Font jTextFieldsFont;
    private JPanel menuPan;
    private JToolBar jToolBar;
    private JButton addKeybdLayoutItem;
    private Map<String, List<LayoutsEditDataModel.MutablePair<String, Integer>>> sevedLangsMap;
    public ManageKeybdLayoutsAndLangsWin() {
        this.winWidth = (47 * MainlyGUIWin.screenSize.width) / 100;
        this.winHeight = (52 * MainlyGUIWin.screenSize.height) / 100;
        this.lblFontSize = (3 * MainlyGUIWin.screenSize.height) / 100;
        this.jFrame = new JFrame("Easy Type - Manage layouts");
        this.jFrame.setSize(this.winWidth, this.winHeight);
        this.jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.mainPanel = new JPanel();

        this.generalLayout = new BorderLayout();
        this.mainPanel.setLayout(this.generalLayout);
        this.jFrame.add(this.mainPanel);
        this.explainPan = new JPanel();
        this.lettersForLearningPan = new JPanel();
        this.lettersForLearningPan.setBackground(new Color(2, 73, 123));
//        this.lettersForLearningPan.setPreferredSize(new Dimension(650, 350));
        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.lettersForLearningPan.setBorder(border);
        this.label = new JLabel("<html>Create new layout, Manage (delete / modify) Saved keyboard layouts</html>");
        this.label.setFont(new Font("Serif", Font.BOLD, this.lblFontSize));
        this.label.setForeground(Color.white);
        this.label.setPreferredSize(new Dimension(600, 90));
        this.explainPan.add(this.label);
        this.explainPan.setPreferredSize(new Dimension(winWidth, (14 * MainlyGUIWin.screenSize.height) / 100));
        this.explainPan.setBackground(new Color(2, 73, 123));
        this.jTextFieldsFont = new Font("Serif", Font.BOLD, (3 * MainlyGUIWin.screenSize.height) / 100);
        this.textFieldsLettersVKCodesPairList = new ArrayList<>();
        this.actionsPan = new JPanel();
        this.btnsLayout = new FlowLayout(FlowLayout.CENTER);
        this.actionsPan.setLayout(this.btnsLayout);
        this.saveLangBtn = new JButton("Learn & Save");
        this.saveLangBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        this.resetInsertedDataBtn = new JButton("Reset");
        this.actionsPan.add(this.saveLangBtn);
        this.actionsPan.add(this.resetInsertedDataBtn);
        this.saveLangBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkDuplicationsInInsertedLetters();
            }
        });
        this.resetInsertedDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetWindowAndInsertedData();
            }
        });
        this.addRowBtn = new JButton("Add one row");
        this.removeRowBtn = new JButton("Remove one row");
        this.actionsPan.add(this.removeRowBtn);
        this.actionsPan.add(this.addRowBtn);
        this.addRowBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOrRemoveJTextFieldsRows(gridLayout.getRows() + 1);
            }
        });
        this.removeRowBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOrRemoveJTextFieldsRows(gridLayout.getRows() - 1);
            }
        });

        this.btnsBgClr = new Color(255, 255, 230);
        JButton removeCurrLangBtn = new JButton("Remove selected language");
        removeCurrLangBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedKeybdLayout(currSelectedLayout);
            }
        });
        Color panelsBgClr = new Color(2, 73, 123);
        removeCurrLangBtn.setBackground(this.btnsBgClr);
        JButton saveModifiedKeybdLayoutBtn = new JButton("Save modified language");
        saveModifiedKeybdLayoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveModifiedLayout();
            }
        });
        saveModifiedKeybdLayoutBtn.setBackground(this.btnsBgClr);
        this.savedLangsActionsPan = new JPanel();
        this.savedLangsActionsPan.setBackground(panelsBgClr);
        this.savedLangsActionsPan.setLayout(this.btnsLayout);
        this.savedLangsActionsPan.add(removeCurrLangBtn);
        this.savedLangsActionsPan.add(saveModifiedKeybdLayoutBtn);
        this.actionsPan.setBackground(panelsBgClr);
        this.menuPan = new JPanel();
        this.menuPan.setBackground(panelsBgClr);
        this.jToolBar = new JToolBar();
        Border menuPanBorder = BorderFactory.createEmptyBorder(5, 10, 10, 0);
        this.menuPan.setBorder(menuPanBorder);
        this.jToolBar.setFloatable(false);
        this.jToolBar.setLayout(new BoxLayout(this.jToolBar, BoxLayout.Y_AXIS));
        this.jToolBar.setBorder(border);
        this.createAndSetJToolBarItem();
        this.menuPan.add(this.jToolBar);
        this.mainPanel.add(this.menuPan, BorderLayout.LINE_START);
        this.mainPanel.add(this.explainPan, BorderLayout.PAGE_START);
        this.mainPanel.add(this.lettersForLearningPan, BorderLayout.CENTER);
        this.mainPanel.add(this.actionsPan, BorderLayout.PAGE_END);
        this.gridLayout = new GridLayout(3, 10, 5, 5);
        this.lettersForLearningPan.setLayout(this.gridLayout);
        this.setJTextFields();

        this.addRowBtn.setBackground(this.btnsBgClr);
        this.removeRowBtn.setBackground(this.btnsBgClr);
        this.saveLangBtn.setBackground(this.btnsBgClr);
        this.resetInsertedDataBtn.setBackground(this.btnsBgClr);
        this.jFrame.setVisible(true);
        learningMode = true;
    }

    private void setJTextFields() {
        for (int i = 0; i < 30; i++) {
            JTextField jTextField = new JTextField();
            jTextField.setFont(this.jTextFieldsFont);
            jTextField.setHorizontalAlignment(JTextField.CENTER);
            addKeyListener(jTextField, i);
            addDoubleClickListener(jTextField);
            if (i == 0) {
                jTextField.setEditable(true);
                jTextField.grabFocus();
            }
            else {
                jTextField.setEditable(false);
            }
            this.textFieldsLettersVKCodesPairList.add(new LayoutsEditDataModel(jTextField, null, 0));
            this.lettersForLearningPan.add(jTextField);
            jFrame.repaint();
            jFrame.validate();
        }
    }
    private void addKeyListener(JTextField textField, int finalI) {
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }


            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                JTextField jTextField = textFieldsLettersVKCodesPairList.get(finalI).getjTextField();
                if ((jTextField.getText().length() == 1) && finalI + 1 <= textFieldsLettersVKCodesPairList.size() && currVKCode != 0) {
                    jTextField.setEditable(false);
                    if (finalI + 1 <= textFieldsLettersVKCodesPairList.size() - 1) {
                        textFieldsLettersVKCodesPairList.get(finalI + 1).getjTextField().grabFocus();
                        textFieldsLettersVKCodesPairList.get(finalI + 1).getjTextField().setEditable(true);
                    } else {
                        jTextField.transferFocus();
                    }
                    textFieldsLettersVKCodesPairList.get(finalI).setLetterKeyCodePair(jTextField.getText(), currVKCode);
                }
            }
        });
    }

    private void addDoubleClickListener(JTextField jTextField) {
        jTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    for (LayoutsEditDataModel objN : textFieldsLettersVKCodesPairList) {
                        objN.getjTextField().setEditable(false);
                    }
                    JTextField jTextField1 = (JTextField) e.getSource();
                    jTextField1.setEditable(true);
                    jTextField1.grabFocus();
                }
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
        });
    }
    private void removeMouseListener(JTextField jTextField) {
        MouseListener[] mouseListeners = jTextField.getMouseListeners();
        jTextField.removeMouseListener(mouseListeners[0]);
    }
    private void checkDuplicationsInInsertedLetters() {
        for (LayoutsEditDataModel objN1 : this.textFieldsLettersVKCodesPairList) {
            String letterForCheck = objN1.getjTextField().getText();
            for (LayoutsEditDataModel objN2 : this.textFieldsLettersVKCodesPairList) {
                if (objN1 != objN2)
                    if (letterForCheck.equals(objN2.getjTextField().getText())) {
                        this.showMsgBoxToUser("Alert",
                                "you have entered some letter / sign / number as twice different " +
                                        "actions in your custom layout, please fill again details for creation new " +
                                        "language / custom layout", true);
                        return;
                    }
            }
        }
        saveNewLangLettersAndVKCodes();
        this.showMsgBoxToUser("Message", "The new language / layout saved successfully", false);
    }

    private void createAndSetJToolBarItem() {
        addKeybdLayoutItem = new JButton("Add new Layout");
        addKeybdLayoutItem.setName("addNewLayout");
        addKeybdLayoutItem.setSize(60, 25);
        addKeybdLayoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetWindowAndInsertedData();
                if (savedLangsActionsPan.getParent() == mainPanel) {
                    mainPanel.remove(savedLangsActionsPan);
                    mainPanel.add(actionsPan, BorderLayout.PAGE_END);
                    jFrame.repaint();
                    jFrame.validate();
                }
            }
        });
        jToolBar.add(addKeybdLayoutItem);
        this.sevedLangsMap = getAllSavedLayouts();
    }

    private void AddOrRemoveJTextFieldsRows(int rowsNum) {
        if (this.gridLayout.getRows() > rowsNum) {
            int jTextFieldsToRemove = (this.gridLayout.getRows() - rowsNum) * 10;
            for (int i = 0; i < jTextFieldsToRemove; i++) {
                this.lettersForLearningPan.remove(this.textFieldsLettersVKCodesPairList.
                        get(this.textFieldsLettersVKCodesPairList.size() - 1).getjTextField());
                this.textFieldsLettersVKCodesPairList.remove(this.textFieldsLettersVKCodesPairList.size() - 1);
            }

        } else if (this.gridLayout.getRows() < rowsNum) {
            int jTextFieldsToAdd = (rowsNum - this.gridLayout.getRows()) * 10;
            for (int i = 0; i < jTextFieldsToAdd; i++) {
                JTextField textField = new JTextField();
                textField.setFont(this.jTextFieldsFont);
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setEditable(false);
                this.textFieldsLettersVKCodesPairList.add(new LayoutsEditDataModel(textField, null, 0));
                this.lettersForLearningPan.add(textField);
                this.addKeyListener(textField, textFieldsLettersVKCodesPairList.size() - 1);
                this.addDoubleClickListener(textField);
            }
        }
        for (LayoutsEditDataModel objN : textFieldsLettersVKCodesPairList) {
            JTextField jTextField = objN.getjTextField();
            if (jTextField.getText().equals("")) {
                jTextField.setEditable(true);
                jTextField.grabFocus();
                break;
            }
        }
        this.gridLayout.setRows(rowsNum);
        this.jFrame.repaint();
        this.jFrame.validate();

    }

    private Map<String, List<LayoutsEditDataModel.MutablePair<String, Integer>>> getAllSavedLayouts() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("langsList.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine = null;
            Map<String, List<LayoutsEditDataModel.MutablePair<String, Integer>>> savedLayoutsMap = new HashMap<>();

            while ((readLine = bufferedReader.readLine()) != null) {
                if (readLine.equals("----")) {
                    readLine = bufferedReader.readLine();
                    JButton item = new JButton(readLine);
                    item.setName(readLine);
                    item.setMaximumSize(new Dimension(110, 25));
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            showLettersOfCurrentLayout(currSelectedLayout = e.getActionCommand());
                            if (actionsPan.getParent() == mainPanel) {
                                mainPanel.remove(actionsPan);
                                mainPanel.add(savedLangsActionsPan, BorderLayout.PAGE_END);
                                jFrame.repaint();
                                jFrame.validate();
                            }
                            textFieldsLettersVKCodesPairList.get(0).getjTextField().setEditable(true);
                            textFieldsLettersVKCodesPairList.get(0).getjTextField().grabFocus();
                        }
                    });
                    jToolBar.add(item);
                    bufferedReader.readLine();
                    String[] langLetters = bufferedReader.readLine().split(" ");
                    String[] vKeyCodes = bufferedReader.readLine().split(" ");
                    List<LayoutsEditDataModel.MutablePair<String, Integer>> currLangLettersAndVKeyCode = new ArrayList<>();
                    for (int i = 0; i < langLetters.length; i++) {
                        currLangLettersAndVKeyCode.add(new LayoutsEditDataModel.MutablePair<String, Integer>
                                (langLetters[i], Integer.valueOf(vKeyCodes[i])));
                    }
                    savedLayoutsMap.put(readLine, currLangLettersAndVKeyCode);
                }
            }
            return savedLayoutsMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showLettersOfCurrentLayout(String langName) {
        List<LayoutsEditDataModel.MutablePair<String, Integer>> langForDisplayArr = this.sevedLangsMap.get(langName);
        this.AddOrRemoveJTextFieldsRows(langForDisplayArr.size() / 10);
        int i = 0;
        for (LayoutsEditDataModel objN : this.textFieldsLettersVKCodesPairList) {
            LayoutsEditDataModel.MutablePair<String, Integer> pair = langForDisplayArr.get(i++);
            objN.setLetterKeyCodePair(pair.getFst(), pair.getSnd());
            JTextField jTextField = objN.getjTextField();
            jTextField.setText(pair.getFst());

            if (i == 0) {
                jTextField.setEditable(true);
                jTextField.grabFocus();
            }
            else {
                jTextField.setEditable(false);
            }

        }
    }

    private ArrayList<String> getAllInstalledInputLangs() {
        ArrayList<String> inputLangsList = new ArrayList<>();
        inputLangsList.add(InputContext.getInstance().getLocale().toString());
        String inputLang = inputLangsList.get(0);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_WINDOWS);
            Thread.sleep(1000);
            while (!inputLangsList.get(0).equals(InputContext.getInstance().getLocale().toString())) {
                robot.keyPress(KeyEvent.VK_WINDOWS);
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_WINDOWS);
                Thread.sleep(1000);
                inputLangsList.add(InputContext.getInstance().getLocale().toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return inputLangsList;
    }

    // show message to user and do action consistently to received parameters
    private void showMsgBoxToUser(String title, String messageContent, Boolean resetWin) {
        // if user clicked on ok button, get in to the block
        JOptionPane.showMessageDialog(null, messageContent, title,
                JOptionPane.INFORMATION_MESSAGE);
        if (resetWin) {
            resetWindowAndInsertedData();
        } else {
            learningMode = false;
//                this.jFrame.dispatchEvent(new WindowEvent(this.jFrame, JFrame.DISPOSE_ON_CLOSE));
            this.jFrame.dispose();
        }
    }


    // if some letter / sign / number entered twice or more in layout builer, reset the JTextFields inserted data
    // and set the first JTextField Editable and set focus on there
    private void resetWindowAndInsertedData() {
        for (LayoutsEditDataModel objN : this.textFieldsLettersVKCodesPairList) {
            objN.getjTextField().setText("");
            objN.getjTextField().setEditable(false);
        }

        this.textFieldsLettersVKCodesPairList.get(0).getjTextField().setEditable(true);
        this.textFieldsLettersVKCodesPairList.get(0).getjTextField().grabFocus();
    }

    // save the letters / signs / numbers entered by the user in layout builder (JTextFields)
    private void saveNewLangLettersAndVKCodes() {
        try {
            FileWriter writer = new FileWriter("langsList.txt", true);
            writer.append("----").append(System.lineSeparator());
            writer.append(InputContext.getInstance().getLocale().toString()).append(System.lineSeparator());
            writer.append(String.valueOf(textFieldsLettersVKCodesPairList.size())).append(System.lineSeparator());
            StringBuilder letters = new StringBuilder();
            StringBuilder vKeyCodes = new StringBuilder();
            for (LayoutsEditDataModel objN : textFieldsLettersVKCodesPairList) {
                letters.append((objN.getLetterKeyCodePair().getFst())).append(" ");
                vKeyCodes.append(objN.getLetterKeyCodePair().getSnd().toString()).append(" ");
            }
            writer.append(letters);
            System.out.println(letters);

            writer.append(System.lineSeparator());
            writer.append(vKeyCodes.toString()).append(System.lineSeparator());
            writer.close();
        }
        catch(IOException e){
                e.printStackTrace();
            }
    }

    // remove selected keyboard layout ~ called when removeSelectedLayoutBtn clicked
    private void removeSelectedKeybdLayout(String layoutName) {
        if (sevedLangsMap.size() == 1) {
            showMsgBoxToUser("Alert", "You could not delete this layout, because this is the only availble layout", true);
        }
        else {
            try {
                URI fileReader = new URI("langsList.txt");
                List<String> langsListContent = Files.readAllLines(Paths.get(String.valueOf(fileReader)));
                for (String line : langsListContent) {
                    if (line.equals(layoutName)) {
                        int indexOfFirstLineToDel = langsListContent.indexOf(line) - 1;
                        langsListContent.removeAll(langsListContent.subList(indexOfFirstLineToDel, indexOfFirstLineToDel + 5));
                        Files.write(Paths.get("langsList.txt"), langsListContent, Charset.forName("UTF-8"));
                        List<Component> toolBarCompList = Arrays.asList(this.jToolBar.getComponents());
                        for (Component jBtn : toolBarCompList) {
                            if (jBtn.getName().equals(layoutName)) {
                                this.jToolBar.remove(jBtn);
                            }
                        }
                        mainPanel.remove(savedLangsActionsPan);
                        mainPanel.add(actionsPan, BorderLayout.PAGE_END);
                        resetWindowAndInsertedData();
                        jFrame.repaint();
                        jFrame.validate();
                        sevedLangsMap.remove(layoutName);
                        break;
                    }
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveModifiedLayout() {
        try {
            URI uri = new URI("langsList.txt");
            List<String> langsListContent = Files.readAllLines(Paths.get(String.valueOf(uri)));
            StringBuilder letters = new StringBuilder();
            StringBuilder vKeyCodes = new StringBuilder();
            for (String line : langsListContent) {
                if (line.equals(currSelectedLayout)) {
                    for (LayoutsEditDataModel objN : textFieldsLettersVKCodesPairList) {
                        letters.append(objN.getLetterKeyCodePair().getFst()).append(" ");
                        vKeyCodes.append(objN.getLetterKeyCodePair().getSnd()).append(" ");
                    }
                    int startLineChNum = langsListContent.indexOf(line);
                    langsListContent.set(startLineChNum + 1, String.valueOf(letters.toString().split(" ").length));
                    langsListContent.set(startLineChNum + 2, letters.toString());
                    langsListContent.set(startLineChNum + 3, vKeyCodes.toString());
                    FileWriter writer = new FileWriter("langsList.txt", false);
                    for (String s : langsListContent) {
                        try {
                            writer.append(s).append(System.lineSeparator());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    writer.close();
                    break;
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}