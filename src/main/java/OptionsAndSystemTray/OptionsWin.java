package OptionsAndSystemTray;

import ManageKeyboardTapAndPerformClicks.CatchClickedKeys;
import ManageKeyboardTapAndPerformClicks.NumericKeyCodes;
import ManageKeyboardTapAndPerformClicks.ScanKeysThread;
import ManageKeyboardTapAndPerformClicks.mainWinGUIManage.MainlyGUIWin;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;

public class OptionsWin extends JFrame {
    private Border border;
    private Border typingTypeOptBorder;
    private JPanel mainPanel;
    private JPanel panel;
    private JPanel typingMethodPan;
    private BoxLayout layout;
    private BoxLayout tMPLayout;
    private BorderLayout mainLayout;
    private JCheckBox keySoundCB;
    private JCheckBox showAlwaysExtraLblsCB;
    private JCheckBox lockChoosedRowCB;
    private JCheckBox showHelpBalloonTipsChkB;

    private JRadioButton nineKeysTypeRB;
    private JRadioButton oneKeyTypeRB;

    private ButtonGroup typeMethodBtnGrp;

    private JLabel chooseScanSpeedLbl;
    private JSlider scanSpeedSlider;

    private JComboBox scanKeysComboB;

    private JPanel btnsPan;
    private FlowLayout btnsLayout;
    private JButton okBtn;
    private JButton cancelBtn;

    public static Boolean extraLblsOnOff = false;
    public static Boolean lockSelectedRow = false;
    public static Boolean scanKeysOnOff = true;
    public static int scanSpeed = 1000;
    public static int selectedVK_CODE = 76;
    public static int selectedVKCodeInd = 0;
    public static Boolean typeSound = true;
    public static Boolean helpBalloonTips = true;

    // initialize and show the options window, get the last saved settings parameters from the settings file which
    // in the program folder and set them to the static options variables
    public OptionsWin() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 600);
        this.mainLayout = new BorderLayout();
        this.mainPanel = new JPanel(this.mainLayout);
        this.panel = new JPanel();
        this.mainPanel.add(this.panel, BorderLayout.PAGE_START);
        add(this.mainPanel);
        this.layout = new BoxLayout(this.panel, BoxLayout.Y_AXIS);
        this.panel.setLayout(this.layout);
        this.border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.mainPanel.setBorder(this.border);
        this.typingMethodPan = new JPanel();
        this.tMPLayout = new BoxLayout(this.typingMethodPan, BoxLayout.Y_AXIS);
        this.typingTypeOptBorder = BorderFactory.createTitledBorder("Typing method");
        this.typingMethodPan.setBorder(this.typingTypeOptBorder);
        this.typingMethodPan.setLayout(this.tMPLayout);
        this.mainPanel.add(this.typingMethodPan);
        this.btnsPan = new JPanel();
        this.btnsLayout = new FlowLayout(FlowLayout.RIGHT);
        this.btnsPan.setLayout(this.btnsLayout);
        this.mainPanel.add(this.btnsPan, BorderLayout.PAGE_END);
        setTitle("Options");
        setResizable(false);
        this.keySoundCB = new JCheckBox("Enable key type sound");
        this.keySoundCB.setSelected(OptionsWin.typeSound);
        this.showAlwaysExtraLblsCB = new JCheckBox("Show always extra actions");
        this.showAlwaysExtraLblsCB.setSelected(OptionsWin.extraLblsOnOff);
        this.lockChoosedRowCB = new JCheckBox("Lock selected row (to unlock tap \"/\" key)");
        this.lockChoosedRowCB.setSelected(OptionsWin.lockSelectedRow);
        this.showHelpBalloonTipsChkB = new JCheckBox("Show help balloon tips by mouse hover");
        this.showHelpBalloonTipsChkB.setSelected(OptionsWin.helpBalloonTips);
        this.panel.add(this.keySoundCB);
        this.panel.add(this.showAlwaysExtraLblsCB);
        this.panel.add(this.lockChoosedRowCB);
        this.panel.add(this.showHelpBalloonTipsChkB);
        this.nineKeysTypeRB = new JRadioButton("Using ten keys (0-9)");
        this.oneKeyTypeRB = new JRadioButton("Using keys scan:");
        this.typeMethodBtnGrp = new ButtonGroup();
        this.typeMethodBtnGrp.add(this.nineKeysTypeRB);
        this.typeMethodBtnGrp.add(this.oneKeyTypeRB);
        this.chooseScanSpeedLbl = new JLabel("Scanning speed:");
        this.typingMethodPan.add(this.nineKeysTypeRB);
        this.typingMethodPan.add(this.oneKeyTypeRB);
        // enable typing using ten keys method (0-9)
        this.nineKeysTypeRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scanSpeedSlider.setEnabled(false);
                scanKeysComboB.setEnabled(false);
            }
        });
        // enable typing using scanning keys method (typing with only one key)
        this.oneKeyTypeRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scanSpeedSlider.setEnabled(true);
                scanKeysComboB.setEnabled(true);
            }
        });
        // choose the speed of scan when the typing method which selected is type using scanning keys
        this.typingMethodPan.add(this.chooseScanSpeedLbl);
        this.scanSpeedSlider = new JSlider(500, 2000);
        this.scanSpeedSlider.setMinorTickSpacing(250);
        this.scanSpeedSlider.setMajorTickSpacing(500);
        final Hashtable scanSpeedsLblsHashT = new Hashtable();
        scanSpeedsLblsHashT.put(500, new JLabel("shorter"));
        scanSpeedsLblsHashT.put(2000, new JLabel("longer"));
        scanSpeedsLblsHashT.put(1250, new JLabel("1.25"));
        this.scanSpeedSlider.setLabelTable(scanSpeedsLblsHashT);
        this.scanSpeedSlider.setPaintTicks(true);
        this.scanSpeedSlider.setPaintLabels(true);
        this.scanSpeedSlider.setSnapToTicks(true);
        this.scanSpeedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JLabel label = (JLabel) scanSpeedsLblsHashT.get(1250);
                label.setText(String.valueOf(Float.valueOf(scanSpeedSlider.getValue()) / 1000));
            }
        });
        this.typingMethodPan.add(this.scanSpeedSlider);
        // choose the key which tapping on him will choose row / letter in type using scan keys method
        String [] keysStrings = {"numpad 1", "numpad 2", "numpad 3", "numpad 4", "numpad 6", "numpad 7", "numpad 8", "numpad 9"};
        this.scanKeysComboB = new JComboBox(keysStrings);
        this.scanKeysComboB.setMaximumSize(new Dimension(250, 25));
        this.scanKeysComboB.setMaximumSize(new Dimension(250, 50));
        this.scanKeysComboB.setBorder(this.border);
        this.scanKeysComboB.setSelectedIndex(OptionsWin.selectedVKCodeInd);
        this.typingMethodPan.add(this.scanKeysComboB);
        // set the settings values to the options window components
        if (OptionsWin.scanKeysOnOff) {
            this.oneKeyTypeRB.setSelected(true);
        }
        else {
            this.nineKeysTypeRB.setSelected(true);
            this.scanSpeedSlider.setEnabled(false);
            this.scanKeysComboB.setEnabled(false);
        }
        this.okBtn = new JButton("Save");
        this.cancelBtn = new JButton("Cancel");
        this.okBtn.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        this.btnsPan.add(this.okBtn);
        this.btnsPan.add(this.cancelBtn);
        // change the values of static options variables to the new values like shown in the options win and
        // save the new values to the options file which in the program folder
        this.okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsWin.extraLblsOnOff = showAlwaysExtraLblsCB.isSelected();
                if (OptionsWin.extraLblsOnOff) {
                    CatchClickedKeys.showSpecialActionsLbls();
                } else {
                    CatchClickedKeys.hideSpecialActionsLbls();
                }
                OptionsWin.lockSelectedRow = lockChoosedRowCB.isSelected();
                OptionsWin.scanKeysOnOff = oneKeyTypeRB.isSelected();
                switch (scanKeysComboB.getSelectedIndex()) {
                    case 0:
                        OptionsWin.selectedVK_CODE = NumericKeyCodes.ONE_VK_CODE;
                        break;
                    case 1:
                        OptionsWin.selectedVK_CODE = NumericKeyCodes.TWO_VK_CODE;
                        break;
                    case 2:
                        OptionsWin.selectedVK_CODE = NumericKeyCodes.THREE_VK_CODE;
                        break;
                    case 3:
                        OptionsWin.selectedVK_CODE = NumericKeyCodes.FOUR_VK_CODE;
                        break;
                    case 4:
                        OptionsWin.selectedVK_CODE = NumericKeyCodes.SIX_VK_CODE;
                        break;
                    case 5:
                        OptionsWin.selectedVK_CODE = NumericKeyCodes.SEVEN_VK_CODE;
                        break;
                    case 6:
                        OptionsWin.selectedVK_CODE = NumericKeyCodes.EIGHT_VK_CODE;
                        break;
                    case 7:
                        OptionsWin.selectedVK_CODE = NumericKeyCodes.NINE_VK_CODE;
                        break;
                }
                OptionsWin.selectedVKCodeInd = scanKeysComboB.getSelectedIndex();
                OptionsWin.scanSpeed = scanSpeedSlider.getValue();
                CatchClickedKeys.scanKeysTimer.setDelay(OptionsWin.scanSpeed);
                if (OptionsWin.scanKeysOnOff) {
                    CatchClickedKeys.scanKeysThread = new ScanKeysThread();
                    CatchClickedKeys.scanKeysThread.start();
                    CatchClickedKeys.scannedInd++;
                }
                else {
                    CatchClickedKeys.scanKeysTimer.stop();
                    CatchClickedKeys.ind = -1;
                }

                OptionsWin.typeSound = keySoundCB.isSelected();
                OptionsWin.helpBalloonTips = showHelpBalloonTipsChkB.isSelected();
                if (OptionsWin.helpBalloonTips) {

                }
                else {
                    MainlyGUIWin.disableHelpBalloonTips();
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("type_preferences.txt"));
                    String modifiedText = "extraLblsOnOff: \n" + OptionsWin.extraLblsOnOff +
                            "\nlockSelectedRow: \n" + OptionsWin.lockSelectedRow +
                            "\nscanKeysOnOff: \n" + OptionsWin.scanKeysOnOff +
                            "\nscanSpeed: \n" + OptionsWin.scanSpeed + "\nselectedVK_CODE: \n" + OptionsWin.selectedVK_CODE +
                            "\nselectedVKCodeInd: \n" + OptionsWin.selectedVKCodeInd +
                            "\ntypeSound: \n" + OptionsWin.typeSound +
                            "\nshowHelpBalloonTips: \n" + OptionsWin.helpBalloonTips;
                    writer.write(modifiedText);
                    writer.flush();
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });
        // set on click listener to cancel button, close the options window
        this.cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    // read the saved settings from the settings file which in the program folder and initialize the options static
    // variables
    public static void readAndSetPref() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("type_preferences.txt"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("extraLblsOnOff")) {
                    OptionsWin.extraLblsOnOff = Boolean.valueOf(bufferedReader.readLine());
                }
                else
                    if (line.contains("lockSelectedRow")) {
                        OptionsWin.lockSelectedRow = Boolean.valueOf(bufferedReader.readLine());
                    }
                    else
                        if (line.contains("scanKeysOnOff")) {
                            OptionsWin.scanKeysOnOff = Boolean.valueOf(bufferedReader.readLine());
                        }
                        else
                            if (line.contains("scanSpeed")) {
                                OptionsWin.scanSpeed = Integer.valueOf(bufferedReader.readLine());
                            }
                            else
                                if (line.contains("selectedVK_CODE")) {
                                    OptionsWin.selectedVK_CODE = Integer.valueOf(bufferedReader.readLine());
                                }
                                else
                                    if (line.contains("selectedVKCodeInd")) {
                                        OptionsWin.selectedVKCodeInd = Integer.valueOf(bufferedReader.readLine());
                                    }
                                else
                                    if (line.contains("typeSound")) {
                                        OptionsWin.typeSound = Boolean.valueOf(bufferedReader.readLine());
                                    }
                                    else
                                        if (line.contains("showHelpBalloonTips")) {
                                            OptionsWin.helpBalloonTips = Boolean.valueOf(bufferedReader.readLine());
                                        }
            }
            bufferedReader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}