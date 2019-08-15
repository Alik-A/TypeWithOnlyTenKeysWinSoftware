package ManageKeyboardTapAndPerformClicks;

import LanguageManagePackage.SetInputLang;
import OptionsAndSystemTray.OptionsWin;
import ManageKeyboardTapAndPerformClicks.mainWinGUIManage.MainlyGUIWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatchClickedKeys extends MainlyGUIWin {
    public static int ind;
    public static int indTemp;
    private KeyboardTypePerform typePerform;
    public static int special;
    private Robot robot;
    private Timer auxiliaryTimerForUnicodeType;
    public static Timer scanKeysTimer;
    public static Thread scanKeysThread;
    public static int scannedInd;
    private static int maxRows = 3;
    private SetInputLang manageInputLang;
    public static Boolean easyTypePause = false;
    // initialize components thhat the program needs to work and the graphic user interface of the program
    public CatchClickedKeys(SetInputLang manageInputLang) {
        super();
        this.manageInputLang = manageInputLang;
        // set the letters of current input lang to letters JLabels
        this.manageInputLang.getLettersOfCurrentInputLang();
        maxRows = MainlyGUIWin.gridLayoutWin.getRows();
        ind = -1;
        special = 0;
        scannedInd = 1;
        // initialize the robot object, which will be used to perform a lot of keystrokes simulation of letter keys
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        // initialize the auxiliaryTimerForUnicodeType which will be used to perform successfully unicode
        // letters of signs type by pressUnicode method
        this.auxiliaryTimerForUnicodeType = new Timer(300, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ind = OptionsWin.lockSelectedRow && indTemp != 0 ? indTemp : -1;
                auxiliaryTimerForUnicodeType.stop();
            }
        });
        // initialize the typePerform object and send to his constructor the important parameters
        // this object will do simulate keystrokes
        this.typePerform = new KeyboardTypePerform(this.robot, this.manageInputLang);
        // extra function: for typing using only one numeric keyboard key via scanning keys
        scanKeysTimer = new Timer(OptionsWin.scanSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ind == -1) {
                    scannedInd = scannedInd + 1 > maxRows ? 1 : scannedInd + 1;
                    selectedRowBgClrCh(scannedInd);
                    resetAllJlabelsColorTimer.start();
                }
                else {
                    MainlyGUIWin.threeRowsJLabelsArrayList.subList(11 * (ind - 1) + 1, 11* (ind - 1) + 11).get(scannedInd).setBackground(selectedRowColor);
                    scannedInd = scannedInd == 9 ? 0 : scannedInd + 1;
                    scanRowKeysBgClrCh(ind, scannedInd);
//                    if (MainlyGUIWin.threeRowsJLabelsArrayList.subList(ind - 10 + (11 * ind) - ind, 11 * ind).
//                            get(scannedInd != 0 ? scannedInd - 1 : 9).getBackground() == Color.green) {
//                        MainlyGUIWin.threeRowsJLabelsArrayList.subList(ind - 10 + (11 * ind) - ind, 11 * ind).
//                                get(scannedInd != 0 ? scannedInd - 1 : 9).setBackground(selectedRowColor);
//                        }
                    }
            }
        });
        CatchClickedKeys.scannedInd = -1;
        // check if the scan keys (type with one numeric keyboard key) is turned on -  if it is start scanning rows
        // this option can be turned on / off in program options window
        if (OptionsWin.scanKeysOnOff) {
            CatchClickedKeys.scannedInd++;
            CatchClickedKeys.scanKeysThread = new Thread(new ScanKeysThread());
            CatchClickedKeys.scanKeysThread.start();
        }
    }
    // this method called by jNativeHook library when user tapping on some key in keyboard, Actually,
    // this method managing the program.
        // if user tapping on zero key in numeric keyboard, change the current input lang
        // and set the letters of new language to the letters labels in GuiKeysWin(the main window of the program)
    // perform type of selected letter / sign or action and perform the color change of selected key and of selected
    // row for user experience
    private void selectedKey(int indexesKeyCode) {

        if (special == 0) {
            super.selectedKeyBgClrCh(ind, indexesKeyCode, NumericKeyCodes.indexesVirtualKeyCodes);
            this.typePerform.performTypeByPressedKeyCode(indexesKeyCode);
        } else if (special == 1) {
            super.selectedKeyBgClrCh(ind, indexesKeyCode, NumericKeyCodes.indexesVirtualKeyCodes);
            this.typePerform.specialSignsTypePerform(KeyboardTypePerform.vKCodesOfCurrLang.subList(CatchClickedKeys.ind * 10 - 10, CatchClickedKeys.ind * 10).get(NumericKeyCodes.indexesVirtualKeyCodes.indexOf(indexesKeyCode)));
                special = 0;
            try {
                Thread.sleep(500);
                this.manageInputLang.getLettersOfCurrentInputLang();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
         else {
             this.typePerform.specialActionsPerform(indexesKeyCode);
             special = 0;
             super.specialActionsSelectedLblBgClrCh(indexesKeyCode, NumericKeyCodes.indexesVirtualKeyCodes);
         }
        if (!OptionsWin.lockSelectedRow) {
            ind = -1;
        }
    }
    public void callToPerformTypeMethod(int keyCode) {
        if (keyCode == NumericKeyCodes.ZERO_VK_CODE && ind == -1) {
            System.out.println("lang_ch_perf");
            this.manageInputLang.setChangeInputLangThread();
            maxRows = MainlyGUIWin.gridLayoutWin.getRows();
            special = 0;
            return;
        }

        // simulate spaceLbl key keystroke, when user tapping on five key in numeric keyboard
        if (keyCode == NumericKeyCodes.FIVE_VK_CODE && ind == -1 && special == 0) {
            changeSpaceLblColor();
            typePerform.spaceKeyClick();
            return;
        }
        // logic of key scanning, when the user typing on the selected by him in the program settings key
        // the selected row or selected key will be selected / typed
        if (OptionsWin.scanKeysOnOff) {
            if (keyCode == OptionsWin.selectedVK_CODE && ind == -1) {
                resetAllJlabelsColorTimer.stop();
                scanKeysTimer.stop();
                ind = scannedInd;
                selectedRowBgClrCh(ind);
                if (!scanKeysThread.isAlive()) {
                    scanKeysThread = new ScanKeysThread();
                    scanKeysThread.start();
                }
//                return;
            }
            // key scanning mode: perform type of selected letter / special sign to be typed after choosing row the
            // variable ind will be equals to the spaceIndexLbl of row
            else if (ind != -1 && keyCode == OptionsWin.selectedVK_CODE) {
                if (scannedInd > 9) scannedInd--;
                keyCode = NumericKeyCodes.indexesVirtualKeyCodes.get(scannedInd);
                scannedInd = 0;
                selectedKeyBgClrCh(ind, keyCode, NumericKeyCodes.indexesVirtualKeyCodes);
                typePerform.performTypeByPressedKeyCode(keyCode);
                if (!OptionsWin.lockSelectedRow) {
                    ind = -1;
                }
                if (!scanKeysThread.isAlive()) {
                    scanKeysThread = new ScanKeysThread();
                    scanKeysThread.start();
                }
                return;
            }
        }
        // typing by 10 numeric keyboard keys (0-9)
        else {
            // if user typed on rows keys and if no one row is selected get in
            if ((NumericKeyCodes.rowsVirtualKeyCodes.subList
                    (0, MainlyGUIWin.gridLayoutWin.getRows()).contains(keyCode) || keyCode == NumericKeyCodes.SEVEN_VK_CODE || keyCode == NumericKeyCodes.EIGHT_VK_CODE) && ind == -1) {
                switch (keyCode) {
                    // choose the first letters row
                    case NumericKeyCodes.ONE_VK_CODE:
                        ind = 1;
                        selectedRowBgClrCh(ind);
                        break;
                    // choose the second letters row
                    case NumericKeyCodes.TWO_VK_CODE:
                        ind = 2;
                        selectedRowBgClrCh(ind);
                        break;
                    // choose the third letters row
                    case NumericKeyCodes.THREE_VK_CODE:
                        ind = 3;
                        selectedRowBgClrCh(ind);
                        break;
                    // choose the four letters row
                    case NumericKeyCodes.FOUR_VK_CODE:
                        // only if the current typing language is russian
                            ind = 4;
                            selectedRowBgClrCh(ind);
                        break;
                    // change mode to special signs typing mode, it mean that all letters on all labels
                    // which in three rows to special signs
                    case NumericKeyCodes.SEVEN_VK_CODE:
                        if (special == 0) {
                            manageInputLang.setSpecialChars();
                            special = 1;
                        }
                        break;
                    // switch to special signs mode

                    // show and select special actions row
                    case NumericKeyCodes.EIGHT_VK_CODE:
                        specialActionsLblsRowBgClrCh();
                        special = 2;
                        ind = 6;
                        // if the option "show always special actions labels (row)" turned off call to method
                        // which show them in the program window
                        if (!OptionsWin.extraLblsOnOff) {
                            showSpecialActionsLbls();
                        }
                        break;
                    // if user clicked on the * key on numeric keyboard turn on \ off the capsLock and
                    // change the letters that shown in the main win consistently
                    case 55:
                        typePerform.capsLockClick();
                        break;
                }
            }
            // when some row is selected and when the user clicked on some 0-9 key perform type of the selected
            // letter / special sign or special actions
            else if (NumericKeyCodes.indexesVirtualKeyCodes.contains(keyCode) && ind != -1) {
                selectedKey(keyCode);
            }
            else if (keyCode == 109) {
                typePerform.backSpaceKeyClick();
            }
            // if the lock row option is turned on and some row is selected, reset the selected row, means: no one
            // row is selected
            else if (keyCode == 111 && ind != -1) {
                ind = -1;
                resetAllJlabelsColorTimer.start();
            }

            // if user clicked on the * key and some row is selected on numeric keyboard turn on \ off the capsLock and
            // change the letters that shown in the main win consistently
            else if (keyCode == 106) {
                typePerform.capsLockClick();
            }
        }
    }

}