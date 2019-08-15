package ManageKeyboardTapAndPerformClicks;

import LanguageManagePackage.SetInputLang;
import OptionsAndSystemTray.OptionsWin;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import ManageKeyboardTapAndPerformClicks.mainWinGUIManage.MainlyGUIWin;
import com.sun.jna.platform.win32.WinUser;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;


public class KeyboardTypePerform {
    private Robot robot;
    private Clip clip;
    private Boolean capsLkState;
    private SetInputLang manageInputLang;
    public static List<Integer> vKCodesOfCurrLang = new ArrayList<Integer>();
    // the KeyboardTypePerform constructor: initialize the components which needs for perform letters or signs type
    // and special actions and for play the typing sound
    public KeyboardTypePerform(Robot robot, SetInputLang manageInputLang) {
        this.robot = robot;

        URL audioSrc = getClass().getResource("/ManageKeyboardTapAndPerformClicks/keyboard.wav");
        this.manageInputLang = manageInputLang;
        // initialize the needed objects to play the tapping sound when some key be typing by this program
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
            this.clip = AudioSystem.getClip() ;
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        this.capsLkState = false;
    }
    // this method performs the spaceLbl keyboard key
    protected void spaceKeyClick(){
        // call to method which playing the type sound, only if the typeSound property is turned on
        if (OptionsWin.typeSound) this.clickSoundPlay();
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
    }

    protected void backSpaceKeyClick() {
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    }
    // this method performs the capsLock key, we need to switch the focus to the program win, than to tap on capsLock
    // key and return the focus to the win that was focused by the user before the capsLock turning on / off
    // we should to do this in this way because else we have a problems with the synchronisation of state of
    // capsLock state in windows and in our program

    protected void capsLockClick() {
        try {
            // find Easy Type window and set focus on him
            WinDef.HWND currentFocusedWinHWND = User32.INSTANCE.GetForegroundWindow();
            WinDef.HWND easyTypeHWND = User32.INSTANCE.FindWindow("SunAwtFrame", "Easy type");
            User32.INSTANCE.SetForegroundWindow(easyTypeHWND);
            // check caps lock state and swap him: if the caps lock is turned on turn him off, else turn him on
            Toolkit.getDefaultToolkit().setLockingKeyState(
                    KeyEvent.VK_CAPS_LOCK, capsLkState = !Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
            Thread.sleep(150);
            // return the focus to last focused window, before tapping on '*' key in numeric keyboard
            User32.INSTANCE.SetForegroundWindow(currentFocusedWinHWND);
            this.manageInputLang.switchLblsCapitalLetters(capsLkState);
        } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

    protected void performTypeByPressedKeyCode(int pressedVKCode) {
System.out.println(CatchClickedKeys.special);
        int indexOfVKCodeToType = vKCodesOfCurrLang.subList(CatchClickedKeys.ind * 10 - 10, CatchClickedKeys.ind * 10).get(NumericKeyCodes.indexesVirtualKeyCodes.indexOf(pressedVKCode));

        WinUser.INPUT input = new WinUser.INPUT();
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        input.input.setType("ki"); // Because setting INPUT_INPUT_KEYBOARD is not enough: https://groups.google.com/d/msg/jna-users/NDBGwC1VZbU/cjYCQ1CjBwAJ
        input.input.ki.wScan = new WinDef.WORD(0);
        input.input.ki.time = new WinDef.DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);

        switch (CatchClickedKeys.special) {
            case 1:
                this.specialSignsTypePerform(pressedVKCode);
                break;
            case 0:
                // Press "a"
                input.input.ki.wVk = new WinDef.WORD(indexOfVKCodeToType); // 0x41
                input.input.ki.dwFlags = new WinDef.DWORD(0);  // keydown
                User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());

                // Release "a"
                input.input.ki.wVk = new WinDef.WORD(indexOfVKCodeToType); // 0x41
                input.input.ki.dwFlags = new WinDef.DWORD(2);  // keyup
                User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
        }
    }

    protected void specialSignsTypePerform(int vKCodesForPressing) {
        CatchClickedKeys.easyTypePause = true;

        boolean zeroFlag = vKCodesForPressing % 10 == 0;
        CatchClickedKeys.easyTypePause = false;
        robot.keyPress(KeyEvent.VK_ALT);
        while (vKCodesForPressing != 0) {
            int vKToPress = vKCodesForPressing > 99 ? vKCodesForPressing / 100 : vKCodesForPressing < 10 ? vKCodesForPressing : vKCodesForPressing / 10;
            vKCodesForPressing -= vKToPress * (vKCodesForPressing > 100 ? 100 : vKCodesForPressing > 10 ? 10 : 1);
            System.out.println("sp" + vKToPress);

            switch (vKToPress) {
                case 0:
                    robot.keyPress(KeyEvent.VK_NUMPAD0);
                    robot.keyRelease(KeyEvent.VK_NUMPAD0);
                    break;
                case 1:
                    robot.keyPress(KeyEvent.VK_NUMPAD1);
                    robot.keyRelease(KeyEvent.VK_NUMPAD1);
                    break;
                case 2:
                    robot.keyPress(KeyEvent.VK_NUMPAD2);
                    robot.keyRelease(KeyEvent.VK_NUMPAD2);
                    break;
                case 3:
                    robot.keyPress(KeyEvent.VK_NUMPAD3);
                    robot.keyRelease(KeyEvent.VK_NUMPAD3);
                    break;
                case 4:
                    robot.keyPress(KeyEvent.VK_NUMPAD4);
                    robot.keyRelease(KeyEvent.VK_NUMPAD4);
                    break;
                case 5:
                    robot.keyPress(KeyEvent.VK_NUMPAD5);
                    robot.keyRelease(KeyEvent.VK_NUMPAD5);
                    break;
                case 6:
                    robot.keyPress(KeyEvent.VK_NUMPAD6);
                    robot.keyRelease(KeyEvent.VK_NUMPAD6);
                    break;
                case 7:
                    robot.keyPress(KeyEvent.VK_NUMPAD7);
                    robot.keyRelease(KeyEvent.VK_NUMPAD7);
                    break;
                case 8:
                    robot.keyPress(KeyEvent.VK_NUMPAD8);
                    robot.keyRelease(KeyEvent.VK_NUMPAD8);
                    break;
                case 9:
                    robot.keyPress(KeyEvent.VK_NUMPAD9);
                    robot.keyRelease(KeyEvent.VK_NUMPAD9);
                    break;

            }
            if (zeroFlag && vKToPress == 0) break;
        }
        robot.keyRelease(KeyEvent.VK_ALT);
        CatchClickedKeys.easyTypePause = false;
    }
    // if the selected row by the user is special actions row, perform the the selected action By switch to the
    // appropriate case by the received key code of the tapped key by key combinations
    protected void specialActionsPerform(int keyCode) {
        switch (keyCode) {
            case NumericKeyCodes.ZERO_VK_CODE:
                this.robot.keyPress(KeyEvent.VK_CONTROL);
                this.robot.keyPress(KeyEvent.VK_C);
                this.robot.keyRelease(KeyEvent.VK_C);
                this.robot.keyRelease(KeyEvent.VK_CONTROL);
                break;
            case NumericKeyCodes.ONE_VK_CODE:
                this.robot.keyPress(KeyEvent.VK_CONTROL);
                this.robot.keyPress(KeyEvent.VK_X);
                this.robot.keyRelease(KeyEvent.VK_X);
                this.robot.keyRelease(KeyEvent.VK_CONTROL);
                break;
            case NumericKeyCodes.TWO_VK_CODE:
                this.robot.keyPress(KeyEvent.VK_CONTROL);
                this.robot.keyPress(KeyEvent.VK_V);
                this.robot.keyRelease(KeyEvent.VK_V);
                this.robot.keyRelease(KeyEvent.VK_CONTROL);
                break;
            case NumericKeyCodes.THREE_VK_CODE:
                this.robot.keyPress(KeyEvent.VK_CONTEXT_MENU);
                this.robot.keyPress(KeyEvent.VK_CONTEXT_MENU);
                break;
            case NumericKeyCodes.FOUR_VK_CODE:
                this.robot.keyPress(KeyEvent.VK_CONTROL);
                this.robot.keyPress(KeyEvent.VK_Z);
                this.robot.keyRelease(KeyEvent.VK_Z);
                this.robot.keyRelease(KeyEvent.VK_CONTROL);
                break;
            case NumericKeyCodes.FIVE_VK_CODE:
                this.robot.keyPress(KeyEvent.VK_CONTROL);
                this.robot.keyPress(KeyEvent.VK_SHIFT);
                this.robot.keyPress(KeyEvent.VK_Z);
                this.robot.keyRelease(KeyEvent.VK_Z);
                this.robot.keyRelease(KeyEvent.VK_SHIFT);
                this.robot.keyRelease(KeyEvent.VK_CONTROL);
                break;
            case NumericKeyCodes.SIX_VK_CODE:
                this.robot.keyPress(KeyEvent.VK_PRINTSCREEN);
                this.robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
                break;
            case NumericKeyCodes.SEVEN_VK_CODE:
                if (MainlyGUIWin.mainlyKeysJFrame.getWidth() == 500) {
                    MainlyGUIWin.mainlyKeysJFrame.setSize(400, 240);
                    MainlyGUIWin.spaceLbl.setPreferredSize(new Dimension(80, 32));
                    for (JLabel lbl : MainlyGUIWin.specialActionsLblsArrayList) {
                        lbl.setPreferredSize(new Dimension(30, 28));
                    }
                    MainlyGUIWin.mainlyKeysJFrame.setShape(new RoundRectangle2D.Double(0, 0,
                            MainlyGUIWin.mainlyKeysJFrame.getWidth(), MainlyGUIWin.mainlyKeysJFrame.getHeight(),
                            20, 20));
                }
                else if (MainlyGUIWin.mainlyKeysJFrame.getWidth() == 400) {
                    MainlyGUIWin.mainlyKeysJFrame.setSize(500, 300);
                    MainlyGUIWin.spaceLbl.setPreferredSize(new Dimension(100, 40));
                    for (JLabel lbl: MainlyGUIWin.specialActionsLblsArrayList) {
                        lbl.setPreferredSize(new Dimension(40, 35));
                    }
                    MainlyGUIWin.mainlyKeysJFrame.setShape(new RoundRectangle2D.Double(0,0,
                            MainlyGUIWin.mainlyKeysJFrame.getWidth(), MainlyGUIWin.mainlyKeysJFrame.getHeight(),
                            20, 20));
                }
                break;
            case NumericKeyCodes.EIGHT_VK_CODE:
                MainlyGUIWin.mainlyKeysJFrame.setLocation(MainlyGUIWin.mainlyKeysJFrame.getX(), 5);
                break;
            case NumericKeyCodes.NINE_VK_CODE:
                MainlyGUIWin.mainlyKeysJFrame.setLocation(MainlyGUIWin.mainlyKeysJFrame.getX(), 465);
                break;
        }
    }

    // play the typing sound when the program performing type of some letter or sign, only if the type sound option is
    // turned on8
    private void clickSoundPlay() {
        clip.setFramePosition(0);
        clip.start();
    }
}
