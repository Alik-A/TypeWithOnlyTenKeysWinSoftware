import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.im.InputContext;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

public class CatchClickedKeys  extends ClickDo implements NativeKeyListener {

    private int ind;
    private Robot robot;

    public CatchClickedKeys() {
        super();
        this.ind = -1;
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
    }
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == 82 && this.ind == -1){
            Process p = null;
            try {
                p = Runtime.getRuntime().exec("cscript ew.vbs");
                Thread.sleep(200);
                robot.keyPress(KeyEvent.VK_WINDOWS);
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_WINDOWS);
                Thread.sleep(1000);
                SetInputLang.changeLblsLetters();
                Thread.sleep(2000);
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_TAB);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }

        if (this.chooseRow.contains(e.getKeyCode()) == true && ind == -1 && e.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_NUMPAD) {

            switch (e.getKeyCode()) {
                case 79:
                    this.ind = 1;
                    super.choosedRowBgClrCh(this.ind);

                    break;
                case 80:
                    this.ind = 2;
                    super.choosedRowBgClrCh(this.ind);

                    break;
                case 81:
                    this.ind = 3;
                    super.choosedRowBgClrCh(this.ind);

                    break;
            }
        } else if (this.rowKeys.contains(e.getKeyCode()) && this.ind == 1 && e.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_NUMPAD) {
            super.chooseKeyBgClrch(this.ind, e.getKeyCode(), this.rowKeys);
            firstRowClick(e);
            this.ind = -1;
        } else if (this.rowKeys.contains(e.getKeyCode()) && this.ind == 2 && e.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_NUMPAD) {
            super.chooseKeyBgClrch(this.ind, e.getKeyCode(), this.rowKeys);
            secondRowClick(e);
            this.ind = -1;
        }
        else if (this.rowKeys.contains(e.getKeyCode()) && this.ind == 3 && e.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_NUMPAD) {
            super.chooseKeyBgClrch(this.ind, e.getKeyCode(), this.rowKeys);
            thriedRowClick(e);
            this.ind = -1;
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    public void firstRowClick(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case 82:
                    robot.keyPress(KeyEvent.VK_Q);
                    robot.keyRelease(KeyEvent.VK_Q);
                break;

            case 79:
                    robot.keyPress(KeyEvent.VK_W);
                    robot.keyRelease(KeyEvent.VK_W);
                break;

            case 80:
                    robot.keyPress(KeyEvent.VK_E);
                    robot.keyRelease(KeyEvent.VK_E);
                break;

            case 81:
                    robot.keyPress(KeyEvent.VK_R);
                    robot.keyRelease(KeyEvent.VK_R);
                break;

            case 75:
                    robot.keyPress(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_T);
                break;

            case 76:
                    robot.keyPress(KeyEvent.VK_Y);
                    robot.keyRelease(KeyEvent.VK_Y);
                break;

            case 77:
                    robot.keyPress(KeyEvent.VK_U);
                    robot.keyRelease(KeyEvent.VK_U);
                break;
            case 71:
                    robot.keyPress(KeyEvent.VK_I);
                    robot.keyRelease(KeyEvent.VK_I);
                break;
            case 72:
                    robot.keyPress(KeyEvent.VK_O);
                    robot.keyRelease(KeyEvent.VK_O);
                break;

            case 73:
                    robot.keyPress(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_P);
                break;
        }
    }

    public void secondRowClick(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case 82:
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_A);
                break;

            case 79:
                    robot.keyPress(KeyEvent.VK_S);
                    robot.keyRelease(KeyEvent.VK_S);
                break;

            case 80:
                    robot.keyPress(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_D);
                break;

            case 81:
                    robot.keyPress(KeyEvent.VK_F);
                    robot.keyRelease(KeyEvent.VK_F);
                break;

            case 75:
                    robot.keyPress(KeyEvent.VK_G);
                    robot.keyRelease(KeyEvent.VK_G);
                break;

            case 76:
                    robot.keyPress(KeyEvent.VK_H);
                    robot.keyRelease(KeyEvent.VK_H);
                break;

            case 77:
                    robot.keyPress(KeyEvent.VK_J);
                    robot.keyRelease(KeyEvent.VK_J);
                break;

            case 71:
                    robot.keyPress(KeyEvent.VK_K);
                    robot.keyRelease(KeyEvent.VK_K);
                break;
            case 72:
                    robot.keyPress(KeyEvent.VK_L);
                    robot.keyRelease(KeyEvent.VK_L);
                break;

            case 73:
                    robot.keyPress(KeyEvent.VK_SEMICOLON);
                    robot.keyRelease(KeyEvent.VK_SEMICOLON);
                break;
        }
    }
    public void thriedRowClick(NativeKeyEvent e) {

        switch (e.getKeyCode()) {
            case 82:
                robot.keyPress(KeyEvent.VK_Z);
                robot.keyRelease(KeyEvent.VK_Z);
                break;

            case 79:
                robot.keyPress(KeyEvent.VK_X);
                robot.keyRelease(KeyEvent.VK_X);
                break;

            case 80:
                robot.keyPress(KeyEvent.VK_C);
                robot.keyRelease(KeyEvent.VK_C);
                break;

            case 81:
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                break;

            case 75:
                robot.keyPress(KeyEvent.VK_B);
                robot.keyRelease(KeyEvent.VK_B);
                break;

            case 76:
                robot.keyPress(KeyEvent.VK_N);
                robot.keyRelease(KeyEvent.VK_N);
                break;

            case 77:
                robot.keyPress(KeyEvent.VK_M);
                robot.keyRelease(KeyEvent.VK_M);
                break;

            case 71:
                robot.keyPress(KeyEvent.VK_COMMA);
                robot.keyRelease(KeyEvent.VK_COMMA);
                break;
            case 72:
                robot.keyPress(KeyEvent.VK_COMMA);
                robot.keyRelease(KeyEvent.VK_COMMA);
                break;

            case 73:
                robot.keyPress(KeyEvent.VK_PERIOD);
                robot.keyRelease(KeyEvent.VK_PERIOD);
                break;
        }
    }
}