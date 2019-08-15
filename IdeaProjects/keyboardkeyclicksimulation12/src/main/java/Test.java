import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import javax.swing.*;
import java.awt.*;


public class Test extends JFrame{
    private JFrame win;
    private JPanel panel;
    private JLabel lbl1;
    private JLabel lbl2;
    private GridLayout gridLayout;
    public Test(){
        this.win = new JFrame();
        this.panel = new JPanel();
        this.lbl1 = new JLabel("2");
        this.lbl2 = new JLabel("2");

        this.panel.setLayout(this.gridLayout);
        this.panel.add(this.lbl1);
        this.panel.add(this.lbl2);
        this.win.add(this.panel);
        this.gridLayout = new GridLayout();
        this.win.setVisible(true);

    }

    public static void main(String[] args) {
        Test test = new Test();
    }
//    public void setFocusToWindowsApp(String applicationTitle, int... windowState) {
//        int state = User32.SW_SHOWNORMAL; //default window state (Normal)
//        if (windowState.length > 0) {
//            state = windowState[0];
//            switch (state) {
//                default:
//                case 0:
//                    state = User32.SW_SHOWNORMAL;
//                    break;
//                case 1:
//                    state = User32.SW_SHOWMAXIMIZED;
//                    break;
//                case 2:
//                    state = User32.SW_SHOWMINIMIZED;
//                    break;
//            }
//        }
//
//        User32 user32 = User32.INSTANCE;
//        WinDef.HWND hWnd = user32.FindWindow(null, applicationTitle);
//        if (user32.IsWindowVisible(hWnd)) {
//            user32.ShowWindow(hWnd, state); //.SW_SHOW);
//            user32.SetForegroundWindow(hWnd);
//            user32.SetFocus(hWnd);
//        }
//    }
}